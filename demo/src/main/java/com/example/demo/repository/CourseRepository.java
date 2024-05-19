package com.example.demo.repository;

import com.example.demo.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
    CourseEntity findByTitle(String title);

    Optional<CourseEntity> findCourseByTitle(String name);

}
