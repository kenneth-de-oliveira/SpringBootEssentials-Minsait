package io.springbootessentials.minsait.application;

public record Enrollment(Long id, Long idCourse, Long idStudent) {
    public io.springbootessentials.minsait.domain.Enrollment toEntity() {
        return new io.springbootessentials.minsait.domain.Enrollment(id, idCourse, idStudent);
    }
}