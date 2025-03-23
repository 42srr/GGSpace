package com.ggs.ggspace.repository.club;

import com.ggs.ggspace.domain.club.Club;
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
class ClubRepositoryTest {

    @Autowired
    ClubRepository clubRepository;

    @PersistenceContext
    EntityManager em;

    @DisplayName("동아리를 저장 및 조회할 수 있다.")
    @Test
    void saveAndFind() {
        //given
        Club club = Club.builder()
                .name("test")
                .description("this is test club")
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .build();

        clubRepository.save(club);
        em.clear();

        //when
        Club findClub = clubRepository.findById(club.getId());

        //then
        assertThat(findClub.getName()).isEqualTo(club.getName());
        assertThat(findClub.getDescription()).isEqualTo(club.getDescription());
    }

    @DisplayName("동아리 조회시 null을 조회할 수 없다.")
    @Test
    void findByNull() {
        assertThatThrownBy(() -> clubRepository.findById(null))
                .isInstanceOf(FindByNullException.class);
    }

}