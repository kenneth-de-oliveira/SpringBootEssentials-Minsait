package io.springbootessentials.minsait.framework;

import io.springbootessentials.minsait.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
