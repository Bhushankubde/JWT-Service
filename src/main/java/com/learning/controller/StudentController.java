package com.learning.controller;

import com.learning.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StudentController {

    private List<Student> students = new ArrayList<>(Arrays.asList(
         new Student(1,"golu",89),
         new Student(2,"wick",90),
         new Student(3,"john",59)
    ));

    @GetMapping("/student")
    public List<Student> getStudent() {
          return students;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getToken(HttpServletRequest request){
        System.out.println(request.getAttribute("_csrf"));
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student){
        students.add(student);
        return student;
    }
}
