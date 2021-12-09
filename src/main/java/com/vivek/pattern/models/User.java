package com.vivek.pattern.models;

public class User {

    public final String username;
    public final String emailId;
    public final Booking booking;

    public User(String username, String emailId, Booking booking) {
        this.username = username;
        this.emailId = emailId;
        this.booking = booking;
    }

    public User withUsername(String u) {
        return new User(u, emailId, booking);
    }

    public User withEmailId(String e) {
        return new User(username, e, booking);
    }

    public User withBooking(Booking b) {
        return new User(username, emailId, b);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", emailId='" + emailId + '\'' +
                ", booking=" + booking +
                '}';
    }

}
