package io.springbootessentials.minsait.application;

public record Course(Long id, String title, Integer credits) {
    public io.springbootessentials.minsait.domain.Course toEntity() {
        return new io.springbootessentials.minsait.domain.Course(id, title, credits);
    }
}
