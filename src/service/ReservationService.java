package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {

    private final static ReservationService instance = new ReservationService();
    private Set<IRoom> hotelRooms;
    private Map<String, Set<Reservation>> reservations;

    private ReservationService() {
        hotelRooms = new HashSet<>();
        reservations = new HashMap<>();
    }

    public static ReservationService getInstance() {
        return instance;
    }

    public Set<IRoom> getHotelRooms() {
        return hotelRooms;
    }

    public void addRoom(IRoom room) {
        hotelRooms.add(room);
    }

    public IRoom getARoom(String roomId) {
        return hotelRooms.stream()
                .filter(r-> r.getRoomNumber().equals(roomId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid room number"));

    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        Set<Reservation> customerReservations = reservations.get(customer.getEmail());
        if (customerReservations == null) {
            customerReservations = new HashSet<>();
        }
        customerReservations.add(reservation);
        reservations.put(customer.getEmail(), customerReservations);
        return reservation;

    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {

        // get all reservations
        Set<Reservation> allReservations = new HashSet<>();
        for (Set<Reservation> customerReservations : reservations.values() ) {
            allReservations.addAll(customerReservations);
        }

        if (allReservations.isEmpty()) {
            return hotelRooms
                    .stream()
                    .filter(room -> !room.isFree())
                    .collect(Collectors.toSet());
        } else {

            Set<IRoom> collect1 = allReservations
                    .stream()
                    .filter(res -> !areDatesOverlapping(res.getCheckInDate(), res.getCheckOutDate(), checkInDate, checkOutDate))
                    .filter(res -> !areDatesEqual(res.getCheckInDate(), res.getCheckOutDate(), checkInDate, checkOutDate))
                    .map(reservation -> reservation.getRoom())
                    .collect(Collectors.toSet());


            Set<IRoom> reservedRooms = allReservations
                    .stream()
                    .map(reservation -> reservation.getRoom())
                    .collect(Collectors.toSet());

            // at the beginning we have rooms that have never been reserved
            Set<IRoom> roomsWithoutReservation = hotelRooms
                    .stream()
                    .filter(room -> !reservedRooms.contains(room))
                    .collect(Collectors.toSet());

            collect1.addAll(roomsWithoutReservation);
            return collect1;
        }
    }

    // assumption: a customer can check out of a room and another can check in the same day
    public static boolean areDatesOverlapping(Date resCheckIn, Date resCheckOut, Date checkInDate, Date checkOutDate) {
        return resCheckIn.after(checkInDate) && resCheckIn.before(checkOutDate)
                || resCheckOut.after(checkInDate) && resCheckOut.before(checkOutDate);
    }

    public static boolean areDatesEqual(Date resCheckIn, Date resCheckOut, Date checkInDate, Date checkOutDate) {
        return resCheckIn.equals(checkInDate) && resCheckOut.equals(checkOutDate);
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        return reservations.get(customer.getEmail());
    }

    public void printAllReservation() {
        List<Reservation> allReservations = new ArrayList<>();
        for (Set<Reservation> customerReservations : reservations.values() ) {
            allReservations.addAll(customerReservations);
        }

        if (allReservations.isEmpty()) {
            System.out.println("There are no reservations");
            System.out.println();
        } else {
            allReservations.forEach(reservation -> System.out.println(reservation));
        }

    }
}
