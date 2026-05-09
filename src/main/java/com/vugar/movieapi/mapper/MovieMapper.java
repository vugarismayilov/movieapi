package com.vugar.movieapi.mapper;

import com.vugar.movieapi.dto.request.MovieRequest;
import com.vugar.movieapi.dto.response.MovieResponse;
import com.vugar.movieapi.entity.Movie;

public class MovieMapper {

    public static Movie toEntity(MovieRequest request) {

        return Movie.builder()
                .title(request.getTitle())
                .director(request.getDirector())
                .releaseYear(request.getReleaseYear())
                .genre(request.getGenre())
                .imdbRating(request.getImdbRating())
                .build();
    }

    public static MovieResponse toResponse(Movie movie) {

        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .director(movie.getDirector())
                .releaseYear(movie.getReleaseYear())
                .genre(movie.getGenre())
                .imdbRating(movie.getImdbRating())
                .build();
    }
}
