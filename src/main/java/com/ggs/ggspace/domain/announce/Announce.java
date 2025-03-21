package com.ggs.ggspace.domain.announce;

import jakarta.persistence.*;
import lombok.AccessLevel;
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
}
