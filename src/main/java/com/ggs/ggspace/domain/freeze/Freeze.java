package com.ggs.ggspace.domain.freeze;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Getter
@Table(name = "freeze")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Freeze {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Timestamp start;
    private Timestamp end;
    private Timestamp createdAt;

    @Builder
    private Freeze(Timestamp start, Timestamp end, Timestamp createdAt) {
        this.start = start;
        this.end = end;
        this.createdAt = createdAt;
    }
}
