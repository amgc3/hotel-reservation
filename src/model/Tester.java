package model;

import service.ReservationService;

import java.util.Calendar;
import java.util.Date;

public class Tester {

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
//        calendar.set(2023, 01, 01);
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 01);
        Date checkIn = calendar.getTime();

        calendar.set(Calendar.DAY_OF_MONTH, 14);
        Date checkOut = calendar.getTime();

        Room room = new Room("101", 75.0, RoomType.DOUBLE);
        //FreeRoom freeRoom = new FreeRoom("222", RoomType.SINGLE)
        FreeRoom freeRoom = new FreeRoom("105", RoomType.DOUBLE);


        Customer customer = new Customer("first", "second", "J@domain.com");
        System.out.println(customer);

        Reservation reservation = new Reservation(customer, room, checkIn, checkOut);
        System.out.println(ReservationService.getInstance().getCustomersReservation(customer));


        //Customer customer1 = new Customer("first", "second", "email");
        System.out.println(room);
        System.out.println(freeRoom);
        System.out.println(customer);
        System.out.println(reservation);
    }
}
