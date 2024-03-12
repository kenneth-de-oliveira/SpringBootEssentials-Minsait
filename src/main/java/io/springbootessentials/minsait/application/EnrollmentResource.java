package io.springbootessentials.minsait.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/enrollees")
@RequiredArgsConstructor
public class EnrollmentResource {

    private final EnrollmentService service;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody Enrollment enrollment) {
        var entity = enrollment.toEntity();
        service.save(entity);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri()).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Enrolled> findByid(@PathVariable("id") Long id) {
        var entity = service.findById(id);
        return entity.getName() != null ? ResponseEntity.ok(entity) : ResponseEntity.notFound().build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Enrollment enrollment){
        try {
            var entity = enrollment.toEntity();
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