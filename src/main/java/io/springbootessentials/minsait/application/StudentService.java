package io.springbootessentials.minsait.application;

import io.springbootessentials.minsait.domain.Student;
import io.springbootessentials.minsait.framework.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    @Transactional
    public void save(Student student) {
        repository.save(student);
    }

    public Optional<Student> findById(Long id) {
        return repository.findById(id);
    }

    public void update(Long id, Student student) {
        findById(id).ifPresentOrElse(entity -> {
            this.setIfNotNull(entity::setName, student.getName());
            repository.save(entity);
        }, () -> {
            throw new StudentNotFoundException("entity not found");
        });
    }

    public List<io.springbootessentials.minsait.application.Student> findAll() {
        return repository.findAll().stream()
                .map(Student::toDTO)
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