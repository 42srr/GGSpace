package com.ggs.ggspace.domain.user;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String intraId;

    private String img;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Builder
    private User(String intraId, String img, Role role) {
        this.intraId = intraId;
        this.img = img;
        this.role = role;
    }
}
