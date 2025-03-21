package com.ggs.ggspace.repository.study;

import com.ggs.ggspace.domain.study.Study;
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
public class StudyRepositoryTest {

    @Autowired
    StudyRepository repository;

    @PersistenceContext
    EntityManager em;

    @DisplayName("공지사항을 저장 및 조회할 수 있다.")
    @Test
    void saveAndFind() {
        //given
        Study study = Study.builder()
                .name("test 스터디 이름입니다.")
                .description("test 스터디 설명입니다.")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .updatedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        repository.save(study);
        em.clear();

        //when
        Study findStudy = repository.findById(study.getId());

        //then
        assertThat(findStudy.getName()).isEqualTo(study.getName());
        assertThat(findStudy.getDescription()).isEqualTo(study.getDescription());
        assertThat(findStudy.getCreatedAt()).isEqualTo(study.getCreatedAt());
        assertThat(findStudy.getUpdatedAt()).isEqualTo(study.getUpdatedAt());
    }

    @DisplayName("공지사항 조회시 null을 조회할 수 없다.")
    @Test
    void findByNull() {
        assertThatThrownBy(() -> repository.findById(null))
                .isInstanceOf(FindByNullException.class);
    }
}
