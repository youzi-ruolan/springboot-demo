package com.example.springbootdemo.service;
import com.example.springbootdemo.dto.StudentCreateDTO;
import com.example.springbootdemo.dto.StudentUpdateDTO;
import com.example.springbootdemo.entity.Student;
import com.example.springbootdemo.exception.BusinessException;
import com.example.springbootdemo.mapper.StudentMapper;
import com.example.springbootdemo.vo.StudentVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final StudentMapper studentMapper;

    public StudentService(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    /**
     * 返回全部的学生信息
     */
    public List<StudentVO> getStudents (){
        List<Student> students = studentMapper.findAll();

        List<StudentVO> result = new ArrayList<>();

        for (Student student : students) {
            result.add(toVo(student));
        }

        return result;
    }

    /**
     * 根据ID查询学生
     */

    public StudentVO getStudentById(Long id){

        Student student = studentMapper.findById(id);
        if(student == null){
            throw new BusinessException(404,"学生不存在");
        }
        return  toVo(student);
    }

    /**
     * 新增学生
     */
    public StudentVO createStudent(StudentCreateDTO dto){
        Student student = new Student();

        student.setGender(dto.getGender());
        student.setAge(dto.getAge());
        student.setName(dto.getName());
        student.setStudentNo(dto.getStudentNo());

        studentMapper.insert(student);

        return toVo(student);
    }

    /**
     * 修改学生
     */
    public StudentVO updateStudent(Long id, StudentUpdateDTO dto) {

        Student student = studentMapper.findById(id);

        if (student == null) {
            throw new BusinessException(404, "学生不存在");
        }

        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setGender(dto.getGender());

        int rows = studentMapper.update(student);

        if (rows != 1) {
            throw new BusinessException(500, "修改学生失败");
        }

        return toVo(student);
    }

    /**
     * 删除学生
     */

    public void deleteStudent(Long id){
        Student student = studentMapper.findById(id);
        if (student == null) {
            throw new BusinessException(404, "学生不存在");
        }

        int rows = studentMapper.deleteById(id);

        if (rows != 1) {
            throw new BusinessException(500, "删除学生失败");
        }
    }

    /**
     * 复制一份
     */
    public StudentVO toVo (Student student){
        StudentVO result = new StudentVO();
        result.setAge(student.getAge());
        result.setGender(student.getGender());
        result.setName(student.getName());
        result.setStudentNo(student.getStudentNo());
        result.setId(student.getId());
        return  result;
    }

    /**
     * 根据ID查询entity
     */
    public Student findStudentById(Long id){
        List<Student> students = studentMapper.findAll();
        for (Student student : students) {

            if (student.getId().equals(id)) {
                return student;
            }
        }

        throw new BusinessException(
                404,
                "学生不存在"
        );
    }

}
