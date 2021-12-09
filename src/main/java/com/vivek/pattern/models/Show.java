package com.vivek.pattern.models;

import java.time.LocalDateTime;

public class Show {

    public final Movie movie;
    public final LocalDateTime dateTime;

    public Show(Movie movie, LocalDateTime dateTime) {
        this.movie = movie;
        this.dateTime = dateTime;
    }

    public Show withMovie(Movie m) {
        return new Show(m, dateTime);
    }

    public Show withDateTime(LocalDateTime dt) {
        return new Show(movie, dt);
    }

    @Override
    public String toString() {
        return "Show{" +
                "movie=" + movie +
                ", dateTime=" + dateTime +
                '}';
    }

}
