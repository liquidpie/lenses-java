package com.vivek.pattern.models;

public class Booking {

    public final Show show;
    public final Integer numSeats;

    public Booking(Show show, Integer numSeats) {
        this.show = show;
        this.numSeats = numSeats;
    }

    public Booking withShow(Show s) {
        return new Booking(s, numSeats);
    }

    public Booking withNumSeats(Integer n) {
        return new Booking(show, n);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "show=" + show +
                ", numSeats=" + numSeats +
                '}';
    }

}
