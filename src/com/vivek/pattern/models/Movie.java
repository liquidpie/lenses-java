package com.vivek.pattern.models;

public class Movie {

    public final String title;

    public Movie(String title) {
        this.title = title;
    }

    public Movie withTitle(String t) {
        return new Movie(t);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                '}';
    }

}
