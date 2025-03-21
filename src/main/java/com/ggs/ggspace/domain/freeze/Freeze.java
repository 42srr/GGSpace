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
    private Long id;

    private Timestamp startTime;
    private Timestamp endTime;
    private Timestamp createdAt;

    @Builder
    private Freeze(Timestamp startTime, Timestamp endTime, Timestamp createdAt) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.createdAt = createdAt;
    }
}
