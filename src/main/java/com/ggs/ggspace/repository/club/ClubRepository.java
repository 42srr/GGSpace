package com.ggs.ggspace.repository.club;

import com.ggs.ggspace.domain.club.Club;
import com.ggs.ggspace.repository.exception.common.FindByNullException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ClubRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Club club) {
        em.persist(club);
    }

    public Club findById(Long id) {
        if (id == null)
            throw new FindByNullException("동아리: id로 조회시 null을 입력할 수 없습니다.");

        return em.find(Club.class, id);
    }
}
