package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
     CustomerService customerService = CustomerService.getInstance();
     ReservationService reservationService = ReservationService.getInstance();

    private static final HotelResource instance = new HotelResource();

    public static HotelResource getInstance() {
        return instance;
    }


    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        customerService.addCustomer(email, firstName, lastName);

    }
    public IRoom getRoom(String roomNumber) {
        return reservationService.getARoom(roomNumber);

    }
    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = getCustomer(customerEmail);
        return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);

    }
    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        Customer customer = customerService.getCustomer(customerEmail);
        return reservationService.getCustomersReservation(customer);

    }
    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        return  reservationService.findRooms(checkIn, checkOut);

    }

}
