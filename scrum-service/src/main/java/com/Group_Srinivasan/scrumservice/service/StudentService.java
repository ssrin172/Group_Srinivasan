package com.Group_Srinivasan.scrumservice.service;

import com.Group_Srinivasan.scrumservice.model.Student;

import java.util.List;

public interface StudentService {
    public Student saveStudent(Student student);
    public List<Student> getAllStudents();

    public void deleteAllStudents();
}
