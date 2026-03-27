package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private final List<Student> students = new ArrayList<>();

    public Student addStudent(String firstName, String lastName, String email, String batch) {
        InputValidator.requireNonBlank(firstName, "First name");
        InputValidator.requireNonBlank(lastName, "Last name");
        InputValidator.requireNonBlank(batch, "Batch");

        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, email, batch);
        students.add(student);
        return student;
    }

    public Student addStudent(String firstName, String lastName, String batch) {
        InputValidator.requireNonBlank(firstName, "First name");
        InputValidator.requireNonBlank(lastName, "Last name");
        InputValidator.requireNonBlank(batch, "Batch");

        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, batch);
        students.add(student);
        return student;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Student getStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        throw new EntityNotFoundException("Student", id);
    }

    public Student updateStudent(int id, String firstName, String lastName, String email, String batch) {
        Student student = getStudentById(id);
        if (firstName != null && !firstName.isBlank()) student.setFirstName(firstName);
        if (lastName  != null && !lastName.isBlank())  student.setLastName(lastName);
        if (email     != null && !email.isBlank())     student.setEmail(email);
        if (batch     != null && !batch.isBlank())     student.setBatch(batch);
        return student;
    }

    public void deactivateStudent(int id) {
        Student student = getStudentById(id);
        if (!student.isActive()) {
            throw new InvalidInputException("Student ID " + id + " is already inactive.");
        }
        student.setActive(false);
    }

    public void activateStudent(int id) {
        Student student = getStudentById(id);
        if (student.isActive()) {
            throw new InvalidInputException("Student ID " + id + " is already active.");
        }
        student.setActive(true);
    }

    public int count() { return students.size(); }
}
