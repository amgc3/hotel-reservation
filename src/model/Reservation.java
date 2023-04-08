package model;

import java.time.LocalDate;
import java.util.Date;

public class Reservation {
    Customer customer;
    IRoom room;
    Date checkInDate; // is this Java 15? should I change the below?
    Date checkOutDate;

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customer=" + customer +
                ", room=" + room +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}
