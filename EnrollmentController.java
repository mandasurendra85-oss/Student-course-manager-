package com.example.edu.controller;

import com.example.edu.model.Course;
import com.example.edu.model.Enrollment;
import com.example.edu.model.Student;
import com.example.edu.repository.CourseRepository;
import com.example.edu.repository.EnrollmentRepository;
import com.example.edu.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/enrollments")
@CrossOrigin(origins = "*")
public class EnrollmentController {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentController(EnrollmentRepository enrollmentRepository,
                                 StudentRepository studentRepository,
                                 CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public List<Enrollment> getAll() {
        return enrollmentRepository.findAll();
    }

    // body: { "studentId": 1, "courseId": 2 }
    @PostMapping
    public ResponseEntity<?> enroll(@RequestBody Map<String, Long> body) {
        Long studentId = body.get("studentId");
        Long courseId = body.get("courseId");

        Student student = studentRepository.findById(studentId).orElse(null);
        Course course = courseRepository.findById(courseId).orElse(null);

        if (student == null || course == null) {
            return ResponseEntity.badRequest().body("Invalid studentId or courseId");
        }

        Enrollment enrollment = new Enrollment(student, course);
        return ResponseEntity.ok(enrollmentRepository.save(enrollment));
    }

    @PutMapping("/{id}/grade")
    public ResponseEntity<Enrollment> setGrade(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return enrollmentRepository.findById(id)
                .map(e -> {
                    e.setGrade(body.get("grade"));
                    return ResponseEntity.ok(enrollmentRepository.save(e));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!enrollmentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        enrollmentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
