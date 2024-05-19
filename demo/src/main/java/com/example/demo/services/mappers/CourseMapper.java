package com.example.demo.services.mappers;

import com.example.demo.controllers.dtos.CourseDtos.CourseDto;
import com.example.demo.entities.CourseEntity;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public CourseDto toDto(CourseEntity courseEntity) {
        return CourseDto.builder()
                .title(courseEntity.getTitle())
                .description(courseEntity.getDescription())
                .rating(courseEntity.getRating())
                .assignments(courseEntity.getAssignments())
                .level(courseEntity.getLevel())
                .build();
    }
}
