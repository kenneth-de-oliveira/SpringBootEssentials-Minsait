package io.springbootessentials.minsait.application;

import io.springbootessentials.minsait.framework.CourseRepository;
import io.springbootessentials.minsait.framework.EnrollmentRepository;
import io.springbootessentials.minsait.domain.Enrollment;
import io.springbootessentials.minsait.framework.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository repository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public void save(Enrollment enrollment) {
        repository.save(enrollment);
    }

    public Enrolled findById(Long id) {
        Enrolled enrolled = new Enrolled();
        repository.findById(id).ifPresent(enrollment -> {
            var course = courseRepository.findById(enrollment.getIdCourse());
            var student = studentRepository.findById(enrollment.getIdStudent());
            if (course.isPresent() && student.isPresent()) {
                enrolled.setCourse(course.get().getTitle());
                enrolled.setName(student.get().getName());
            }
        });
        return enrolled;
    }

    public void update(Long id, Enrollment enrollment) {
        repository.findById(id).ifPresentOrElse(entity -> {
            this.setIfNotNull(entity::setIdCourse, enrollment.getIdCourse());
            this.setIfNotNull(entity::setIdStudent, enrollment.getIdStudent());
            repository.save(entity);
        }, () -> {
            throw new EnrollmentNotFoundException("entity not found");
        });
    }

    public void delete(Long id) {
        repository.findById(id).ifPresentOrElse(entity -> repository.deleteById(entity.getId()), () -> {
            throw new StudentNotFoundException("entity not found");
        });
    }

    private <T> void setIfNotNull(final Consumer<T> setter, final T value) {
        if (value != null) {
            setter.accept(value);
        }
    }

}