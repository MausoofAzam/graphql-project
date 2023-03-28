package com.example.mutation;

import com.example.entity.Student;
import com.example.request.StudentInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.request.CreateStudentRequest;
import com.example.response.StudentResponse;
import com.example.service.StudentService;

import java.util.List;

@Service
@Slf4j
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    StudentService studentService;

    public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {
        return new StudentResponse(studentService.createStudent(createStudentRequest));
    }
	/*public List<StudentResponse> addStudents(List<CreateStudentRequest> students) {
		return (List<StudentResponse>) new StudentResponse(studentService.addStudents(students));
	}*/

    public List<Student> addStudents(List<StudentInput> students) {
        List<Student> studentList = studentService.addStudents(students);
        log.info(" Calling studentList : " +studentList);
        return studentList;
    }

}
