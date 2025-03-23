package com.ggs.ggspace.repository.devteam;

import com.ggs.ggspace.domain.devteam.DevTeam;
import com.ggs.ggspace.repository.exception.common.FindByNullException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class DevTeamRepositoryTest {


    @Autowired
    DevTeamRepository devTeamRepository;

    @PersistenceContext
    EntityManager em;

    @DisplayName("개발팀을 생성 및 조회할 수 있다.")
    @Test
    void saveAndFind() {
        //given
        DevTeam devTeam = DevTeam.builder()
                .name("test")
                .description("test dev team")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        devTeamRepository.save(devTeam);
        em.clear();

        //when
        DevTeam findDevTeam = devTeamRepository.findById(devTeam.getId());

        //then
        assertThat(findDevTeam.getName()).isEqualTo(devTeam.getName());
        assertThat(findDevTeam.getDescription()).isEqualTo(devTeam.getDescription());
    }

    @DisplayName("개발팀 조회시 null을 조회할 수 없다.")
    @Test
    void findByNull() {
        assertThatThrownBy(() -> devTeamRepository.findById(null))
                .isInstanceOf(FindByNullException.class);
    }
}