package com.example.springbootdemo.mapper;


import com.example.springbootdemo.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> findAll();

    Student findById(Long id);
}