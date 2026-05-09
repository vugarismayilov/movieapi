package com.vugar.movieapi.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieRequest {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Director cannot be blank")
    private String director;

    @Min(value = 1888, message = "invalid release year")
    private Integer releaseYear;

    @NotBlank(message = "Genre cannot be blank")
    private String genre;

    @DecimalMin(value = "0.0", message = "imdbRating must be at least 0.0")
    @DecimalMax(value = "10.0", message = "imdb rating cannot be greater than 10.0")
    private Double imdbRating;
}
