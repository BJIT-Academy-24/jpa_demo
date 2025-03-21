package bjit.academy.jpa_demo.controller;

import bjit.academy.jpa_demo.model.Address;
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

    @PutMapping("/student/updateEmail")
    public Student updateStudent(@RequestParam("id") Long id, @RequestParam("new_email") String newEmail){
        Student student = studentRepository.findById(id).get();
        student.setEmail(newEmail);

        studentRepository.save(student);
        return student;
    }

    @PutMapping("/student/updateAddress")
    public Student updateStudentAddress(@RequestParam("id") Long id, @RequestBody Address newAddress){
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student ID is not available!"));
        student.setAddress(newAddress);
        studentRepository.save(student);
        return student;
    }


    @DeleteMapping("/student/delete")
    public String deleteStudent(@RequestParam("id") Long id){
        studentRepository.deleteById(id);
        return "This student has been deleted!";
    }
}
