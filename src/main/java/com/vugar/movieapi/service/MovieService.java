package com.vugar.movieapi.service;

import com.vugar.movieapi.dto.request.MovieRequest;
import com.vugar.movieapi.dto.response.MovieResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieService {

    MovieResponse createMovie(MovieRequest request);

    Page<MovieResponse> getAllMovies(int page, int size, String sortBy);

    MovieResponse getMovieById(Long id);

    MovieResponse updateMovie(Long id, MovieRequest request);

    void deleteMovie(Long id);

    List<MovieResponse> searchMovies(String title);
}
