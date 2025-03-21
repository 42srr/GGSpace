package com.ggs.ggspace.repository.announce;

import com.ggs.ggspace.domain.announce.Announce;
import com.ggs.ggspace.repository.exception.common.FindByNullException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 * @TODO
 * 공지사항은 수정, 삭제, 등록, 조회 할 수 있어야 한다.
 * 동아리 별로 조회 가능해야 한다.
 */
@Repository
public class AnnounceRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Announce announce) { em.persist(announce); }

    public Announce findById(Long id) {
        if (id == null)
            throw new FindByNullException("공지 : id 조회시 null 입력 불가");
        return em.find(Announce.class, id);
    }
}
