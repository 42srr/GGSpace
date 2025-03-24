package com.ggs.ggspace.repository.devteam;

import com.ggs.ggspace.domain.devteam.DevTeam;
import com.ggs.ggspace.repository.exception.common.FindByNullException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class DevTeamRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(DevTeam devTeam) {
        em.persist(devTeam);
    }

    public DevTeam findById(Long id) {
        if (id == null)
            throw new FindByNullException("개발팀: id로 조회시 null을 입력할 수 없습니다.");

        return em.find(DevTeam.class, id);
    }

}
