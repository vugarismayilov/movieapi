package com.vugar.movieapi.repository;

import com.vugar.movieapi.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByTitle(String title);
    List<Movie> findByTitleContainingIgnoreCase(String title);
}
