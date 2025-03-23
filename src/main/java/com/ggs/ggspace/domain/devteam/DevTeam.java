package com.ggs.ggspace.domain.devteam;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DevTeam {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    @Column(length = 3000)
    private String description;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @Builder
    private DevTeam(String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
