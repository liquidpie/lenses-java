package com.vivek.pattern;

import com.vivek.pattern.models.Booking;
import com.vivek.pattern.models.Movie;
import com.vivek.pattern.models.Show;
import com.vivek.pattern.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class LensTest {

    @Test
    void testLenses() {
        // Basic Lenses
        Lens<Movie, String> movieTitleLens = Lens.of(s -> s.title, Movie::withTitle);

        Lens<Show, Movie> showMovieLens = Lens.of(s -> s.movie, Show::withMovie);
        Lens<Show, LocalDateTime> showDateTimeLens = Lens.of(s -> s.dateTime, Show::withDateTime);

        Lens<Booking, Show> bookingShowLens = Lens.of(s -> s.show, Booking::withShow);
        Lens<Booking, Integer> bookingSeatsLens = Lens.of(s -> s.numSeats, Booking::withNumSeats);

        Lens<User, String> userNameLens = Lens.of(s -> s.username, User::withUsername);
        Lens<User, String> userEmailLens = Lens.of(s -> s.emailId, User::withEmailId);
        Lens<User, Booking> userBookingLens = Lens.of(s -> s.booking, User::withBooking);

        // Lens composition
        Lens<User, String> changeMovieName = userBookingLens.andThen(bookingShowLens).andThen(showMovieLens).andThen(movieTitleLens);
        Lens<User, LocalDateTime> changeShowDateTime = userBookingLens.andThen(bookingShowLens).andThen(showDateTimeLens);
        Lens<User, Integer> changeBookingSeats = userBookingLens.andThen(bookingSeatsLens);

        // Immutable Structure
        User user = new User("johndoe", "jdoe@example.com", new Booking(new Show(new Movie("foo bar"), LocalDateTime.now()), 2));

        // Mutations through lenses
        String username = userNameLens.get(user);
        Assertions.assertEquals(username, "johndoe");

        user = userNameLens.set(user, "janedoe");
        Assertions.assertEquals(user.username, "janedoe");

        user = changeMovieName.mod(user, s -> "street race");
        Assertions.assertEquals("street race", user.booking.show.movie.title);

        user = changeShowDateTime.mod(user, s -> LocalDateTime.of(2021, 10, 14, 5, 30));
        Assertions.assertEquals("2021-10-14T05:30", user.booking.show.dateTime.toString());

        user = changeBookingSeats.mod(user, s -> 3);
        Assertions.assertEquals(3, user.booking.numSeats);

    }

}
