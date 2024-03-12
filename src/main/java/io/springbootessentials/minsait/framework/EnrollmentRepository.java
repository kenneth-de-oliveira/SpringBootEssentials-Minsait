package io.springbootessentials.minsait.framework;

import io.springbootessentials.minsait.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

}
