package com.Group_Srinivasan.scrumservice;

//package com.Group_Srinivasan.scrumservice.service;

import com.Group_Srinivasan.scrumservice.database.StudentRepository;
import com.Group_Srinivasan.scrumservice.model.Student;
import com.Group_Srinivasan.scrumservice.service.StudentServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentServiceImp studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveStudent() {
        // Arrange
        Student student = new Student();
        when(studentRepository.save(student)).thenReturn(student);

        // Act
        Student savedStudent = studentService.saveStudent(student);

        // Assert
        assertEquals(student, savedStudent);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testGetAllStudents() {
        // Arrange
        Student student1 = new Student();
        Student student2 = new Student();
        when(studentRepository.findAll()).thenReturn(Arrays.asList(student1, student2));

        // Act
        List<Student> studentList = studentService.getAllStudents();

        // Assert
        assertEquals(2, studentList.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void testDeleteAllStudents() {
        // Act
        studentService.deleteAllStudents();

        // Assert
        verify(studentRepository, times(1)).deleteAll();
    }
}

