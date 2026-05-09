package com.vugar.movieapi.service.impl;

import com.vugar.movieapi.dto.request.MovieRequest;
import com.vugar.movieapi.dto.response.MovieResponse;
import com.vugar.movieapi.entity.Movie;
import com.vugar.movieapi.exception.ResourceNotFoundException;
import com.vugar.movieapi.mapper.MovieMapper;
import com.vugar.movieapi.repository.MovieRepository;
import com.vugar.movieapi.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public MovieResponse createMovie(MovieRequest request) {

        if (movieRepository.existsByTitle(request.getTitle())) {
            throw new ResourceNotFoundException("Movie already exist");
        }
        Movie movie = MovieMapper.toEntity(request);

        Movie savedMovie = movieRepository.save(movie);

        return MovieMapper.toResponse(savedMovie);
    }

    @Override
    public Page<MovieResponse> getAllMovies(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(sortBy)
        );

        return movieRepository.findAll(pageable)
                .map(MovieMapper::toResponse);
    }


    @Override
    public MovieResponse getMovieById(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));

        return MovieMapper.toResponse(movie);
    }

    @Override
    public MovieResponse updateMovie(Long id, MovieRequest request) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));

        movie.setTitle(request.getTitle());
        movie.setDirector(request.getDirector());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setGenre(request.getGenre());
        movie.setImdbRating(request.getImdbRating());

        Movie updatedMovie = movieRepository.save(movie);

        return MovieMapper.toResponse(updatedMovie);
    }

    @Override
    public void deleteMovie(Long id) {

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));

        movieRepository.delete(movie);
    }
    @Override
    public List<MovieResponse> searchMovies(String title) {

        return movieRepository
                .findByTitleContainingIgnoreCase(title)
                .stream()
                .map(MovieMapper::toResponse)
                .toList();
    }
}
