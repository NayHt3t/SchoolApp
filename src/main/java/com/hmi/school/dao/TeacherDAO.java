package com.hmi.school.dao;

import org.springframework.data.repository.CrudRepository;

import com.hmi.school.entity.Teacher;

public interface TeacherDAO  extends CrudRepository<Teacher, Long>{

}
