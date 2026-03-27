package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentService {

    private final List<Enrollment> enrollments = new ArrayList<>();

    private final StudentService studentService;
    private final CourseService  courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService  = courseService;
    }

    public Enrollment enrollStudent(int studentId, int courseId) {
        Student student = studentService.getStudentById(studentId);
        Course  course  = courseService.getCourseById(courseId);

        if (!student.isActive()) {
            throw new InvalidInputException("Student ID " + studentId + " is inactive and cannot be enrolled.");
        }
        if (!course.isActive()) {
            throw new InvalidInputException("Course ID " + courseId + " is inactive. Enrollment not allowed.");
        }

        for (Enrollment e : enrollments) {
            if (e.getStudentId() == studentId
                    && e.getCourseId() == courseId
                    && e.getStatus() == EnrollmentStatus.ACTIVE) {
                throw new InvalidInputException("Student is already actively enrolled in this course.");
            }
        }

        int id = IdGenerator.getNextEnrollmentId();
        Enrollment enrollment = new Enrollment(id, studentId, courseId);
        enrollments.add(enrollment);
        return enrollment;
    }

    public List<Enrollment> getAllEnrollments() {
        return new ArrayList<>(enrollments);
    }

    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
        studentService.getStudentById(studentId);
        List<Enrollment> result = new ArrayList<>();
        for (Enrollment e : enrollments) {
            if (e.getStudentId() == studentId) result.add(e);
        }
        return result;
    }

    public Enrollment getEnrollmentById(int id) {
        for (Enrollment e : enrollments) {
            if (e.getId() == id) return e;
        }
        throw new EntityNotFoundException("Enrollment", id);
    }

    public Enrollment markCompleted(int enrollmentId) {
        Enrollment enrollment = getEnrollmentById(enrollmentId);
        if (enrollment.getStatus() != EnrollmentStatus.ACTIVE) {
            throw new InvalidInputException("Only ACTIVE enrollments can be marked completed.");
        }
        enrollment.setStatus(EnrollmentStatus.COMPLETED);
        return enrollment;
    }

    public Enrollment cancelEnrollment(int enrollmentId) {
        Enrollment enrollment = getEnrollmentById(enrollmentId);
        if (enrollment.getStatus() != EnrollmentStatus.ACTIVE) {
            throw new InvalidInputException("Only ACTIVE enrollments can be cancelled.");
        }
        enrollment.setStatus(EnrollmentStatus.CANCELLED);
        return enrollment;
    }

    public int count() { return enrollments.size(); }
}
