package com.ggs.ggspace.repository.department;

import com.ggs.ggspace.domain.department.Department;
import com.ggs.ggspace.repository.exception.common.FindByNullException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class DepartmentRepositoryTest {


    @Autowired
    DepartmentRepository departmentRepository;

    @PersistenceContext
    EntityManager em;

    @DisplayName("부서를 저장 및 조회할 수 있다.")
    @Test
    void saveAndFind() {
        //given
        Department department = Department.builder()
                .name("test")
                .description("test department")
                .build();

        departmentRepository.save(department);
        em.clear();

        //when
        Department findDepartment = departmentRepository.findById(department.getId());

        //then
        assertThat(findDepartment.getName()).isEqualTo(department.getName());
        assertThat(findDepartment.getDescription()).isEqualTo(department.getDescription());
    }

    @DisplayName("부서 조회시 null을 조회할 수 없다.")
    @Test
    void findByNull() {
        assertThatThrownBy(() -> departmentRepository.findById(null))
                .isInstanceOf(FindByNullException.class);
    }

}