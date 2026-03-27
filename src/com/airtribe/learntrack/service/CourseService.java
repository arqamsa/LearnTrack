package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class CourseService {

    private final List<Course> courses = new ArrayList<>();

    public Course addCourse(String courseName, String description, int durationInWeeks) {
        InputValidator.requireNonBlank(courseName, "Course name");
        InputValidator.requirePositive(durationInWeeks, "Duration");

        int id = IdGenerator.getNextCourseId();
        Course course = new Course(id, courseName, description, durationInWeeks);
        courses.add(course);
        return course;
    }

    public Course addCourse(String courseName, int durationInWeeks) {
        InputValidator.requireNonBlank(courseName, "Course name");
        InputValidator.requirePositive(durationInWeeks, "Duration");

        int id = IdGenerator.getNextCourseId();
        Course course = new Course(id, courseName, durationInWeeks);
        courses.add(course);
        return course;
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    public Course getCourseById(int id) {
        for (Course c : courses) {
            if (c.getId() == id) return c;
        }
        throw new EntityNotFoundException("Course", id);
    }

    public Course toggleCourseStatus(int id) {
        Course course = getCourseById(id);
        course.setActive(!course.isActive());
        return course;
    }

    public int count() { return courses.size(); }
}
