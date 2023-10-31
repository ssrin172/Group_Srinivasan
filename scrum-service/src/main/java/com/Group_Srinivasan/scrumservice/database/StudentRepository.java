package com.Group_Srinivasan.scrumservice.database;

import com.Group_Srinivasan.scrumservice.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

}
