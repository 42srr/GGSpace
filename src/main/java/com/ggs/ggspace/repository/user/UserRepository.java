package com.ggs.ggspace.repository.user;

import com.ggs.ggspace.domain.user.User;
import com.ggs.ggspace.repository.exception.common.FindByNullException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(User user) {
        em.persist(user);
    }

    public User findById(Long id) {
        if (id == null) {
            throw new FindByNullException("id 로 조회시 null 을 입력할 수 없습니다.");
        }

        return em.find(User.class, id);
    }
}
