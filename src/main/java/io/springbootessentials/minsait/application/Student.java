package io.springbootessentials.minsait.application;

import java.time.LocalDateTime;

public record Student(Long id, String name, LocalDateTime createdAt) {
    public io.springbootessentials.minsait.domain.Student toEntity() {
        return new io.springbootessentials.minsait.domain.Student(id, name, createdAt);
    }
}