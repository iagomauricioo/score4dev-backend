package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityNotFoundException;
import org.acme.entity.CourseEntity;

import java.util.List;

@ApplicationScoped
public class CourseService {

    public CourseEntity createCourse(CourseEntity course) {
        CourseEntity.persist(course);
        return course;
    }

    public List<CourseEntity> getCourses() {
        return CourseEntity.listAll();
    }

    public CourseEntity editCourse(Long courseId, CourseEntity newCourse) {
        CourseEntity course = CourseEntity.findById(courseId);
        if (course == null) {
            throw new EntityNotFoundException("Course not found (id: " + courseId + ")");
        }
        if (newCourse.name != null) {
            course.name = newCourse.name;
        }
        if (newCourse.description != null) {
            course.description = newCourse.description;
        }
        if (newCourse.instructor != null) {
            course.instructor = newCourse.instructor;
        }
        if (newCourse.platform != null) {
            course.platform = newCourse.platform;
        }
        if (newCourse.category != null) {
            course.category = newCourse.category;
        }
        if (newCourse.url != null) {
            course.url = newCourse.url;
        }
        return course;
    }
}
