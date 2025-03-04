package bjit.academy.jpa_demo.controller;

import bjit.academy.jpa_demo.model.Student;
import bjit.academy.jpa_demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/student/save")
    public Student createStudent(@RequestBody Student student) {
        studentRepository.save(student);
        return student;
    }

    @GetMapping("/student/findByEmail")
    public Student findStudent(@RequestParam("email") String email) {
        return studentRepository.findByEmail(email);
    }
}
