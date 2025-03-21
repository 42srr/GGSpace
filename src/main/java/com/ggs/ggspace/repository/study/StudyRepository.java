package com.ggs.ggspace.repository.study;

import com.ggs.ggspace.domain.study.Study;
import com.ggs.ggspace.repository.exception.common.FindByNullException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class StudyRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Study study) { em.persist(study); }

    public Study findById(Long id) {
        if (id == null)
            throw new FindByNullException("스터디 : id조회시 null 입력 불가");
        return em.find(Study.class, id);
    }
}
