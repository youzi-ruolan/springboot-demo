package com.example.springbootdemo.controller;


import com.example.springbootdemo.common.Result;
import com.example.springbootdemo.dto.StudentCreateDTO;
import com.example.springbootdemo.dto.StudentUpdateDTO;
import com.example.springbootdemo.service.StudentService;
import com.example.springbootdemo.vo.StudentVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 查询全部学生
     */
    @GetMapping
    public Result<List<StudentVO>> getStudents() {

        return Result.success(
                studentService.getStudents()
        );
    }

    /**
     * 查询学生详情
     */
    @GetMapping("/{id}")
    public Result<StudentVO> getStudentById(
            @PathVariable Long id
    ) {

        return Result.success(
                studentService.getStudentById(id)
        );
    }

    /**
     * 创建学生
     */
    @PostMapping
    public Result<StudentVO> createStudent(
            @RequestBody StudentCreateDTO dto
    ) {

        return Result.success(
                studentService.createStudent(dto)
        );
    }

    /**
     * 修改学生
     */
    @PutMapping("/{id}")
    public Result<StudentVO> updateStudent(
            @PathVariable Long id,
            @RequestBody StudentUpdateDTO dto
    ) {

        return Result.success(
                studentService.updateStudent(id, dto)
        );
    }

    /**
     * 删除学生
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteStudent(
            @PathVariable Long id
    ) {

        studentService.deleteStudent(id);

        return Result.success(null);
    }
}
