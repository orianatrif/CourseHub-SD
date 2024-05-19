package com.example.demo.controllers.dtos.CourseDtos;

import com.example.demo.entities.Level;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {
    private String title;
    private String description;
    private int rating;
    private String assignments;
    private Level level;
}
