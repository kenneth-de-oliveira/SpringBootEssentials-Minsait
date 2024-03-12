package io.springbootessentials.minsait.framework;

import io.springbootessentials.minsait.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
