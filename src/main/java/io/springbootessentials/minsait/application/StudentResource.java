package io.springbootessentials.minsait.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentResource {

    private final StudentService service;

    @GetMapping
    public List<Student> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody Student student) {
        var entity = student.toEntity();
        entity.setCreatedAt(LocalDateTime.now());
        service.save(entity);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri()).body(entity.toDTO());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Student> findByid(@PathVariable("id") Long id) {
        var entity = service.findById(id);
        return entity.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity.get().toDTO());
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Student student){
        try {
            var entity = student.toEntity();
            service.update(id, entity);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (StudentNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteByid(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (StudentNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}