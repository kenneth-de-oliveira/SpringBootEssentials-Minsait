package io.springbootessentials.minsait;

import io.springbootessentials.minsait.domain.Course;
import io.springbootessentials.minsait.domain.Enrollment;
import io.springbootessentials.minsait.domain.Student;
import io.springbootessentials.minsait.framework.CourseRepository;
import io.springbootessentials.minsait.framework.EnrollmentRepository;
import io.springbootessentials.minsait.framework.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@Slf4j
public class Bootstrap {

    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }

    @Bean
    CommandLineRunner loadDatabase(CourseRepository courseRepository,
                                StudentRepository studentRepository,
                                EnrollmentRepository enrollmentRepository) {

        log.info("Loading data...");

        if (courseRepository.count() > 0 && studentRepository.count() > 0 && enrollmentRepository.count() > 0) {
            return args -> log.info("Database is not empty");
        }

        return args -> {
            studentRepository.saveAll(this.createStudents());
            courseRepository.saveAll(this.createCourses());
            enrollmentRepository.saveAll(this.createEnrollment());
            log.info("Data loaded !!");
        };

    }

    private List<Student> createStudents() {
        return List.of(
                new Student("John", LocalDateTime.now()),
                new Student("Mary", LocalDateTime.now()),
                new Student("Michael", LocalDateTime.now())
        );
    }

    private List<Course> createCourses() {
        return List.of(
                new Course("Algorithms and Data Structures", 4),
                new Course("Cybersecurity", 4),
                new Course("Theory of Computation", 4),
                new Course("Artificial Intelligence", 4),
                new Course("Web Development", 4),
                new Course("Distributed Systems", 4)
        );
    }

    private List<Enrollment> createEnrollment() {
        return List.of(
                new Enrollment(1L, 1L),
                new Enrollment(2L, 1L),
                new Enrollment(3L, 1L),
                new Enrollment(4L, 1L),
                new Enrollment(5L, 1L),
                new Enrollment(6L, 1L),
                new Enrollment(1L, 2L),
                new Enrollment(2L, 2L),
                new Enrollment(3L, 2L),
                new Enrollment(4L, 2L),
                new Enrollment(5L, 2L),
                new Enrollment(6L, 2L),
                new Enrollment(1L, 3L),
                new Enrollment(2L, 3L),
                new Enrollment(3L, 3L),
                new Enrollment(4L, 3L),
                new Enrollment(5L, 3L),
                new Enrollment(6L, 3L)
        );
    }

}
