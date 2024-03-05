package com.Group_Srinivasan.scrumservice.controller;


import com.Group_Srinivasan.scrumservice.model.Student;
import com.Group_Srinivasan.scrumservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("/add")
    public String add(@RequestBody Student student){
        studentService.saveStudent(student);
        return "New Student is added";
    }

    @GetMapping("/getAll")
    public List<Student> getAllStudents()  {
        return studentService.getAllStudents();
    }

    @DeleteMapping("/deleteAll")
    public void deleteAllStudents(){
        studentService.deleteAllStudents();
    }


}
