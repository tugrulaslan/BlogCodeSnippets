package com.tugrulaslan.controller;

import com.tugrulaslan.dto.Student;
import com.tugrulaslan.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping("/students/{name}")
    @ResponseBody
    public ResponseEntity<Student> create(@PathVariable String name) {
        Student student = Student.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .build();
        studentRepository.save(student);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/students/{id}")
    @ResponseBody
    public ResponseEntity<Student> retrieve(@PathVariable String id) {
        Student student = studentRepository.find(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/students/")
    @ResponseBody
    public ResponseEntity<List<Student>> retrieveAll() {
        List<Student> student = studentRepository.findAll();
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/students/{id}")
    @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable String id) {
        studentRepository.delete(id);
        return ResponseEntity.noContent()
                .build();
    }
}
