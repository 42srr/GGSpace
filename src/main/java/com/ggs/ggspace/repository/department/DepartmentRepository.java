package com.ggs.ggspace.repository.department;

import com.ggs.ggspace.domain.department.Department;
import com.ggs.ggspace.repository.exception.common.FindByNullException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Department department) {
        em.persist(department);
    }

    public Department findById(Long id) {
        if (id == null) {
            throw new FindByNullException("부서: id로 조회시 null을 입력할 수 없습니다.");
        }
        return em.find(Department.class, id);
    }
}
