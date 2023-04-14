package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {

    private final static ReservationService instance = new ReservationService();
    private Map<String, IRoom> hotelRooms;
    private Map<String, List<Reservation>> reservations;

    private ReservationService() {
        hotelRooms = new HashMap<>();
        reservations = new HashMap<>();
    }

    public static ReservationService getInstance() {
        return instance;
    }

    public Map<String, IRoom> getHotelRooms() {
        return hotelRooms;
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
        List<Reservation> customerReservations = reservations.get(customer.getEmail());
        if (customerReservations == null) {
            customerReservations = new ArrayList<>();
        }
        customerReservations.add(reservation);
        reservations.put(customer.getEmail(), customerReservations);
        return reservation;

    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {

        // get all reservations
        List<Reservation> allReservations = new ArrayList<>();
        for (List<Reservation> customerReservations : reservations.values() ) {
            allReservations.addAll(customerReservations);
        }

        if (allReservations.isEmpty()) {
            return hotelRooms.values();
        } else {

            List<IRoom> collect1 = allReservations
                    .stream()
                    .filter(res -> (res.getCheckOutDate().before(checkOutDate) && !res.getCheckOutDate().after(checkInDate)))
                    .filter(res -> (res.getCheckInDate().after(checkInDate) && !res.getCheckInDate().before(checkOutDate)) )
                    .map(reservation -> reservation.getRoom())
                    .collect(Collectors.toList());


            Set<IRoom> reservedRooms = allReservations
                    .stream()
                    .map(reservation -> reservation.getRoom())
                    .collect(Collectors.toSet());

            List<IRoom> roomsWithoutReservation = hotelRooms.values()
                    .stream()
                    .filter(room -> !reservedRooms.contains(room))
                    .collect(Collectors.toList());

            collect1.addAll(roomsWithoutReservation);
            return new HashSet<>(collect1); // I don't think I need this now, probably can return collect1
        }
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return reservations.get(customer.getEmail());
    }

    public void printAllReservation() {
        List<Reservation> allReservations = new ArrayList<>();
        for (List<Reservation> customerReservations : reservations.values() ) {
            allReservations.addAll(customerReservations);
        }

        if (allReservations.isEmpty()) {
            System.out.println("There are no reservations");
        } else {
            allReservations.forEach(reservation -> System.out.println(reservation));
        }

    }
}
