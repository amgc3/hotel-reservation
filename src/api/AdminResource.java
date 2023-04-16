package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {

    /*
     * Admin Menu
     * ------------------------
     * 1. See all Customers
     * 2. See all Rooms
     * 3. See ll Reservations
     * 4. Add a Room
     * 5. Add Test Data
     * 6. Back to Main Menu
     *
     *
     * */

    CustomerService customerService = CustomerService.getInstance();
    ReservationService reservationService = ReservationService.getInstance();

    private static final AdminResource instance = new AdminResource();

    public static AdminResource getInstance() {
        return instance;
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);

    }

    public void addRoom(List<IRoom> rooms) {
        rooms.forEach(room -> {
            reservationService.addRoom(room);
        });
    }

    public Collection<IRoom> getAllRooms() {
        // return the collection of rooms
        return reservationService.getHotelRooms();

    }

    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();

    }

    public void displayAllReservations() {
        reservationService.printAllReservation();
    }

}
