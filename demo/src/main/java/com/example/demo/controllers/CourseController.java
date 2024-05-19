package com.example.demo.controllers;

import com.example.demo.controllers.dtos.CourseDtos.CourseDto;
import com.example.demo.services.CourseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses")
@CrossOrigin
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/findAll")
    public ResponseEntity<List<CourseDto>> findAll() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @PostMapping("/addCourse")
    public ResponseEntity<CourseDto> addCourse(@RequestBody CourseDto courseDto) {
        try {
            CourseDto addedCourse = courseService.addCourse(courseDto);
            return ResponseEntity.ok(addedCourse);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/courseById")
    public ResponseEntity<CourseDto> findById(@RequestParam("id") Integer courseId) {
        try {
            CourseDto courseDto = courseService.findById(courseId);
            return ResponseEntity.ok(courseDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @GetMapping("/courseByTitle")
    public ResponseEntity<CourseDto> findByTitle(@RequestParam("id") String courseTitle) {
        try {
            CourseDto courseDto = courseService.foundByTitle(courseTitle);
            return ResponseEntity.ok(courseDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }



    @PutMapping("/update-rating")
    public ResponseEntity<CourseDto> updateCourse(@RequestParam("title") String title, @RequestParam("rating") int rating) {
        try {
            CourseDto updatedCourse = courseService.updateCourse(title, rating);
            return ResponseEntity.ok(updatedCourse);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/updateAll")
    public ResponseEntity<CourseDto> updateCourseAll(@RequestParam("title") String title, @RequestBody CourseDto courseDto) {
        try {
            CourseDto updatedCourse = courseService.updateCourseTitle(title, courseDto);
            return ResponseEntity.ok(updatedCourse);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCourse(@RequestParam("id") String id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
