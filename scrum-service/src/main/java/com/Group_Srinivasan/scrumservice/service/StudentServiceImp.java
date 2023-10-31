package com.Group_Srinivasan.scrumservice.service;

import com.Group_Srinivasan.scrumservice.database.StudentRepository;
import com.Group_Srinivasan.scrumservice.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImp implements StudentService{
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }
}
