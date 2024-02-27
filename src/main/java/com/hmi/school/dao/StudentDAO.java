package com.hmi.school.dao;

import org.springframework.data.repository.CrudRepository;

import com.hmi.school.entity.Student;

public interface StudentDAO  extends CrudRepository<Student, Long>{

}
