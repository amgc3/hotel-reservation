package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.*;

public class ReservationServiceClass {

    private static ReservationServiceClass instance = new ReservationServiceClass();
    private Map<Integer, IRoom> hotelRooms;

    private ReservationServiceClass() {
        this.hotelRooms = new HashMap<>();
    }

    public static ReservationServiceClass getInstance() {
        return instance;
    }

    public void addRoom(IRoom room) {
        //hotelRooms.add(room);
    }

    public IRoom getARoom(String roomId) {

        return hotelRooms.
        return null; // should I use optional?
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        return null;

    }

    public List<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        return null;

    }
}
