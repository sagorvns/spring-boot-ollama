package System_Design;

import java.util.*;

class TicketBooking {
    private Set<Integer> bookedSeats = new HashSet<>();

    public boolean bookSeat(int seatNumber) {
        if (bookedSeats.contains(seatNumber)) return false;
        bookedSeats.add(seatNumber);
        return true;
    }
}

public class BookingSystem {
    public static void main(String[] args) {
        TicketBooking booking = new TicketBooking();
        System.out.println(booking.bookSeat(10)); // true
        System.out.println(booking.bookSeat(10)); // false
    }
}
