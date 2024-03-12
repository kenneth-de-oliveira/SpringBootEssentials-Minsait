package io.springbootessentials.minsait.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private Integer credits;
    @OneToMany
    private List<Enrollment> enrollmentList;

    public Course(String title, Integer credits) {
        this.title = title;
        this.credits = credits;
    }

    public Course(Long id, String title, Integer credits) {
        this.id = id;
        this.title = title;
        this.credits = credits;
    }

    public io.springbootessentials.minsait.application.Course toDTO() {
        return new io.springbootessentials.minsait.application.Course(id, title, credits);
    }

}