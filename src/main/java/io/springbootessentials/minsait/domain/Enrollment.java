package io.springbootessentials.minsait.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long idCourse;
    @Column
    private Long idStudent;

    public Enrollment(Long idCourse, Long idStudent) {
        this.idCourse = idCourse;
        this.idStudent = idStudent;
    }

    public Enrollment(Long id, Long idCourse, Long idStudent) {
        this.id = id;
        this.idCourse = idCourse;
        this.idStudent = idStudent;
    }

    public io.springbootessentials.minsait.application.Enrollment toDTO() {
        return new io.springbootessentials.minsait.application.Enrollment(id, idCourse, idStudent);
    }

}