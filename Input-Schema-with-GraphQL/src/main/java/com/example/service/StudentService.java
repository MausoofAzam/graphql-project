package com.example.service;

import java.util.ArrayList;
import java.util.List;

import com.example.request.StudentInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Address;
import com.example.entity.Student;
import com.example.entity.Subject;
import com.example.repository.AddressRepository;
import com.example.repository.StudentRepository;
import com.example.repository.SubjectRepository;
import com.example.request.CreateStudentRequest;
import com.example.request.CreateSubjectRequest;

@Service
@Slf4j
public class StudentService {

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    StudentRepository studentRepository;

    public Student getStudentById(long id) {
        return studentRepository.findById(id).get();
    }

    public Student createStudent(CreateStudentRequest createStudentRequest) {
        Student student = new Student(createStudentRequest);

        Address address = new Address();
        address.setStreet(createStudentRequest.getStreet());
        address.setCity(createStudentRequest.getCity());

        address = addressRepository.save(address);

        student.setAddress(address);
        student = studentRepository.save(student);

        List<Subject> subjectsList = new ArrayList<Subject>();

        if (createStudentRequest.getSubjectsLearning() != null) {
            for (CreateSubjectRequest createSubjectRequest : createStudentRequest.getSubjectsLearning()) {
                Subject subject = new Subject();
                subject.setSubjectName(createSubjectRequest.getSubjectName());
                subject.setMarksObtained(createSubjectRequest.getMarksObtained());
                subject.setStudent(student);

                subjectsList.add(subject);
            }

            subjectRepository.saveAll(subjectsList);
        }
        student.setLearningSubjects(subjectsList);
        return student;
    }

	/*public List<Student> addStudents(List<CreateStudentRequest> students) {
		List<Student> addedStudent = new ArrayList<>();
		for (CreateStudentRequest studentInput : students) {
			// Create and save Address entity
			Address address = new Address();
			address.setStreet(studentInput.getStreet());
			address.setCity(studentInput.getCity());
			addressRepository.save(address);
			// Create and save Student entity
			Student student = new Student();
			student.setFirstName(studentInput.getFirstName());
			student.setLastName(studentInput.getLastName());
			student.setEmail(studentInput.getEmail());
			student.setAddress(address);
			studentRepository.save(student);
			// Create and save Subject entities
			for (CreateSubjectRequest subjectInput : studentInput.getSubjectsLearning() ) {
				Subject subject = new Subject();
				subject.setSubjectName(subjectInput.getSubjectName());
				subject.setMarksObtained(subjectInput.getMarksObtained());
				subject.setStudent(student);
				subjectRepository.save(subject);
			}
			boolean add = addedStudent.add(student);
		}
			return addedStudent;
		}
*/
    //This method is used to create List of Student along with its Resources.
    public List<Student> addStudents(List<StudentInput> studentInputs) {
        List<Student> addedStudents = new ArrayList<>();

        for (StudentInput studentInput : studentInputs) {
            // Creating and save Address entity
            Address address = new Address();
            address.setStreet(studentInput.getAddress().getStreet());
            address.setCity(studentInput.getAddress().getCity());
            addressRepository.save(address);

            // Creating and save Student entity
            Student student = new Student();
            student.setFirstName(studentInput.getFirstName());
            student.setLastName(studentInput.getLastName());
            student.setEmail(studentInput.getEmail());
            student.setAddress(address);
            studentRepository.save(student);

            // Creating and save Subject entities
            for (StudentInput.SubjectInput subjectInput : studentInput.getLearningSubjects()) {
                Subject subject = new Subject();
                subject.setSubjectName(subjectInput.getSubjectName());
                subject.setMarksObtained(subjectInput.getMarksObtained());
                subject.setStudent(student);
                subjectRepository.save(subject);
            }
            addedStudents.add(student);
        }
        return addedStudents;
    }
}



