package com.ggs.ggspace.domain.announce;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Getter
@Table(name = "announce")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Announce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 3000)
    private String contents;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    @Builder
    private Announce(String contents, Timestamp createdAt, Timestamp updatedAt) {
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
