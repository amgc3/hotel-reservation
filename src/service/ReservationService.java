package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {

    private final static ReservationService instance = new ReservationService();
    private Map<String, IRoom> hotelRooms;
    private List<Reservation> reservations;


    private ReservationService() {
        hotelRooms = new HashMap<>();
        reservations = new ArrayList<>();
    }

    public static ReservationService getInstance() {
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
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.add(reservation);
        return reservation;

    }

    public List<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        return null;

    }

    public List<Reservation> getCustomersReservation(Customer customer) {
        List<Reservation> customerReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomer().equals(customer)) {
                customerReservations.add(reservation);
            }
        }
        return customerReservations;
    }

    public void printAllReservation() {
        for (Reservation res : reservations) {
            System.out.println(res);
        }
    }
}
