package com.ggs.ggspace.repository.freeze;

import com.ggs.ggspace.domain.freeze.Freeze;
import com.ggs.ggspace.repository.exception.common.FindByNullException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;

@Repository
public class FreezeRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Freeze freeze) { em.persist(freeze); }

    public Freeze findById(Long id) {
        if (id == null)
            throw new FindByNullException("프리즈: id로 조회시 null을 입력할 수 없습니다.");

        return em.find(Freeze.class, id);
    }

}
