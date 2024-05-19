package com.example.demo.services;

import com.example.demo.controllers.dtos.CourseDtos.CourseDto;
import com.example.demo.entities.CourseEntity;
import com.example.demo.repository.CourseRepository;
import com.example.demo.services.mappers.CourseMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public List<CourseDto> findAll() {
        return courseRepository.findAll().stream().map(courseMapper::toDto).toList();
    }

    public CourseDto addCourse(CourseDto courseDto) {
        CourseEntity courseEntity = CourseEntity.builder()
                .title(courseDto.getTitle())
                .description(courseDto.getDescription())
                .rating(courseDto.getRating())
                .assignments(courseDto.getAssignments())
                .level(courseDto.getLevel())
                .build();
        CourseEntity savedCourseEntity = courseRepository.save(courseEntity);
        return courseMapper.toDto(savedCourseEntity);
    }

    public CourseDto findById(Integer courseId) {
        return courseMapper.toDto(courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + courseId + "not found!")));
    }

    public CourseDto foundByTitle(String courseTitle) {
        return courseMapper.toDto(courseRepository.findCourseByTitle(courseTitle)
                .orElseThrow(() -> new EntityNotFoundException("Entity with title " + courseTitle + "not found!")));
    }


    public CourseDto updateCourse(String title, int rating) {
        CourseEntity crs = courseRepository.findByTitle(title);
        if (crs == null)
            return null;

        crs.setRating(rating);
        courseRepository.save(crs);
        return courseMapper.toDto(crs);
    }

    public CourseDto updateCourseTitle(String title, CourseDto courseDto) {
        CourseEntity crs = courseRepository.findByTitle(title);
        if (crs == null)
            return null;

        crs.setRating(courseDto.getRating());
        crs.setDescription(courseDto.getDescription());
        crs.setAssignments(courseDto.getAssignments());
        crs.setLevel(courseDto.getLevel());
        courseRepository.save(crs);
        return courseMapper.toDto(crs);
    }


    public void deleteCourse(String title) {
        CourseEntity crs = courseRepository.findByTitle(title);
        if (crs != null)
            courseRepository.delete(crs);
        else {
            throw new RuntimeException("User with first name " + title + "not found");
        }
    }

}

