package com.ggs.ggspace.repository.freeze;

import com.ggs.ggspace.domain.freeze.Freeze;
import com.ggs.ggspace.domain.user.Role;
import com.ggs.ggspace.domain.user.User;
import com.ggs.ggspace.repository.exception.common.FindByNullException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class FreezeRepositoryTest {

    @Autowired
    FreezeRepository repository;

    @PersistenceContext
    EntityManager em;

    @DisplayName("동아리 프리즈 신청을 저장, 조회 할 수 있다.")
    @Test
    void saveAndFind() {
        //given
        Freeze freeze = Freeze.builder()
                .startTime(Timestamp.valueOf(LocalDateTime.now()))
                .endTime(Timestamp.valueOf(LocalDateTime.now()))
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        repository.save(freeze);
        em.clear();

        // when
        Freeze findFreeze = repository.findById(freeze.getId());

        //then
        assertThat(findFreeze.getStartTime()).isEqualTo(freeze.getStartTime());
        assertThat(findFreeze.getEndTime()).isEqualTo(freeze.getEndTime());
        assertThat(findFreeze.getCreatedAt()).isEqualTo(freeze.getCreatedAt());
    }

    @DisplayName("freeze 조회시 null을 조회할 수 없다.")
    @Test
    void findByNull() {
        assertThatThrownBy(() -> repository.findById(null))
                .isInstanceOf(FindByNullException.class);
    }

}
