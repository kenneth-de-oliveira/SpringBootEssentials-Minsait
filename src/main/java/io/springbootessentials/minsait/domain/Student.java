package io.springbootessentials.minsait.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private LocalDateTime createdAt;
    @OneToMany
    private List<Enrollment> enrollmentList;

    public Student( String name, LocalDateTime createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }

    public Student(Long id, String name, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    public io.springbootessentials.minsait.application.Student toDTO() {
        return new io.springbootessentials.minsait.application.Student(id, name, createdAt);
    }

}
