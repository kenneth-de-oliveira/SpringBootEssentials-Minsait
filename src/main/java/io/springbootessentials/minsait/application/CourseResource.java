package io.springbootessentials.minsait.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseResource {

    private final CourseService service;

    @GetMapping
    public List<Course> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Course> save(@RequestBody Course course) {
        var entity = course.toEntity();
        service.save(entity);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri()).body(entity.toDTO());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Course> findByid(@PathVariable("id") Long id) {
        var entity = service.findById(id);
        return entity.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(entity.get().toDTO());
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Course course){
        try {
            var entity = course.toEntity();
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