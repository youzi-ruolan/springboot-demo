package com.example.springbootdemo.service;


import com.example.springbootdemo.dto.StudentCreateDTO;
import com.example.springbootdemo.dto.StudentUpdateDTO;
import com.example.springbootdemo.entity.Student;
import com.example.springbootdemo.exception.BusinessException;
import com.example.springbootdemo.vo.StudentVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private  final List<Student> students = new ArrayList<>();
    private Long nextId = 1L;

    /**
     * 返回全部的学生信息
     */
    public List<StudentVO> getStudents (){
        List<StudentVO> allList = new ArrayList<>();
        for (Student student : students){
            allList.add(toVo(student));
        }
        return allList;
    }

    /**
     * 根据ID查询学生
     */

    public StudentVO getStudentById(Long id){
        for(Student student : students){
            if(student.getId().equals(id)){
                return  toVo(student);
            }
        }
        return  null;
    }

    /**
     * 新增学生
     */
    public StudentVO createStudent(StudentCreateDTO dto){
        Student student = new Student();

        student.setId(nextId ++);
        student.setGender(dto.getGender());
        student.setAge(dto.getAge());
        student.setName(dto.getName());
        student.setStudentNo(dto.getStudentNo());
        students.add(student);
        return toVo(student) ;
    }

    /**
     * 修改学生
     */
    public StudentVO updateStudent(Long id, StudentUpdateDTO dto){
        Student oldStudent = findStudentById(id);
        if(oldStudent == null){
            return null;
        }
        oldStudent.setStudentNo(dto.getStudentNo());
        oldStudent.setName(dto.getName());
        oldStudent.setAge(dto.getAge());
        oldStudent.setGender(dto.getGender());
        return  toVo(oldStudent);
    }

    /**
     * 删除学生
     */

    public void deleteStudent(Long id){
        Student student = findStudentById(id);
        students.remove(student);
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
