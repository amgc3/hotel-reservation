package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationServiceClass {

    private static ReservationServiceClass instance = new ReservationServiceClass();
    private Map<String, IRoom> hotelRooms;

    private ReservationServiceClass() {
        this.hotelRooms = new HashMap<String, IRoom>();
    }

    public static ReservationServiceClass getInstance() {
        return instance;
    }

    public void addRoom(IRoom room) {
        hotelRooms.put(room.getRoomNumber(), room);
    }

    public IRoom getARoom(String roomId) {
        if (hotelRooms.get(roomId) == null) {
            throw new IllegalArgumentException("Invalid room number ");
        }

        return hotelRooms.get(roomId);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        return null;

    }

    public List<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        return null;

    }
}
