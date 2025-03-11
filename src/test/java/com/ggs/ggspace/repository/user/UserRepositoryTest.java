package com.ggs.ggspace.repository.user;

import com.ggs.ggspace.domain.user.Role;
import com.ggs.ggspace.domain.user.User;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @PersistenceContext
    EntityManager em;

    @DisplayName("사용자를 저장 및 조회할 수 있다.")
    @Test
    void saveAndFind() {
        //given
        User user = User.builder()
                .intraId("test")
                .img("www.example.com/images/3")
                .role(Role.USER)
                .build();

        repository.save(user);
        em.clear();

        // when
        User findUser = repository.findById(user.getId());

        //then
        assertThat(findUser.getIntraId()).isEqualTo("test");
        assertThat(findUser.getImg()).isEqualTo("www.example.com/images/3");
        assertThat(findUser.getRole()).isEqualTo(Role.USER);
    }

    @DisplayName("사용자 조회시 null 을 전달할 수 없다.")
    @Test
    void findByNull() {
        //when then
        assertThatThrownBy(() -> repository.findById(null))
                .isInstanceOf(FindByNullException.class);
    }
}