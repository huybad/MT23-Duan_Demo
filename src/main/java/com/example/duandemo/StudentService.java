package com.example.duandemo;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Optional<Student> getStudentById(Integer id) {
        return repository.findById(id);
    }

    public Student addStudent(Student student) {
        return repository.save(student);
    }

    public Student updateStudent(Integer id, Student studentDetails) {
        Optional<Student> existing = repository.findById(id);
        if (existing.isPresent()) {
            Student student = existing.get();
            if (studentDetails.getName() != null) {
                student.setName(studentDetails.getName());
            }
            if (studentDetails.getEmail() != null) {
                student.setEmail(studentDetails.getEmail());
            }
            if (studentDetails.getAge() != null) {
                student.setAge(studentDetails.getAge());
            }
            return repository.save(student);
        }
        return null;
    }

    public boolean deleteStudent(Integer id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Student> searchStudents(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }
}