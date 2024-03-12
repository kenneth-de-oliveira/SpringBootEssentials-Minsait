package io.springbootessentials.minsait.application;

import io.springbootessentials.minsait.domain.Course;
import io.springbootessentials.minsait.framework.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;

    @Transactional
    public void save(Course course) {
        repository.save(course);
    }

    public Optional<Course> findById(Long id) {
        return this.repository.findById(id);
    }

    public void update(Long id, Course course) {
        findById(id).ifPresentOrElse(entity -> {
            this.setIfNotNull(entity::setTitle, course.getTitle());
            this.setIfNotNull(entity::setCredits, course.getCredits());
            repository.save(entity);
        }, () -> {
            throw new CourseNotFoundException("entity not found");
        });
    }

    public List<io.springbootessentials.minsait.application.Course> findAll() {
        return repository.findAll().stream()
                .map(Course::toDTO)
                .toList();
    }

    public void delete(Long id) {
        findById(id).ifPresentOrElse(entity -> repository.deleteById(entity.getId()), () -> {
            throw new StudentNotFoundException("entity not found");
        });
    }

    private <T> void setIfNotNull(final Consumer<T> setter, final T value) {
        if (value != null) {
            setter.accept(value);
        }
    }

}
