package com.ggs.ggspace.repository.announce;

import com.ggs.ggspace.domain.announce.Announce;
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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class AnnounceRepositoryTest {
    @Autowired
    AnnounceRepository announceRepository;

    @PersistenceContext
    EntityManager em;

    @DisplayName("공지사항을 저장 및 조회할 수 있다.")
    @Test
    void saveAndFind() {
        //given
        Announce announce = Announce.builder()
                .contents("test 공지사항입니다")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        announceRepository.save(announce);
        em.clear();

        //when
        Announce findAnnounce = announceRepository.findById(announce.getId());

        //then
        assertThat(findAnnounce.getContents()).isEqualTo(announce.getContents());
        assertThat(findAnnounce.getCreatedAt()).isEqualTo(announce.getCreatedAt());
        assertThat(findAnnounce.getUpdatedAt()).isEqualTo(announce.getUpdatedAt());
    }

    @DisplayName("공지사항 조회시 null을 조회할 수 없다.")
    @Test
    void findByNull() {
        assertThatThrownBy(() -> announceRepository.findById(null))
                .isInstanceOf(FindByNullException.class);
    }
}
