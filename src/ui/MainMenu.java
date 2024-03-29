package ui;

import api.AdminResource;
import api.HotelResource;
import model.IRoom;
import model.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {

    static HotelResource hotelResource = HotelResource.getInstance();
    static AdminResource adminResource = AdminResource.getInstance();


    public static int printMenuAndGetSelection() {
        System.out.println();
        System.out.println("Main Menu");
        System.out.println("-------------------------------");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an Account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");
        System.out.println("-------------------------------");

        return readInt("Please select a number from the above choices");
    }

    public static int readInt(String prompt) {
        final Scanner input = new Scanner(System.in);
        int num;

        System.out.println(prompt);


        while (true) {
            try {

                num = Integer.parseInt(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Input error. Please select a number from the above menu options");
            }
        }
        return num;
    }

    public static void createAccount() {
        final Scanner input = new Scanner(System.in);
        System.out.println("Please enter your first name");
        String name = input.nextLine();
        System.out.println("Please enter your last name");
        String surname = input.nextLine();
        System.out.println("Please enter your email in name@domain.com format: ");
        String email;
        boolean invalidEmail = true;
        while (invalidEmail) {
            try {
                email = input.nextLine();
                hotelResource.createACustomer(email, name, surname);
                System.out.println("Account Created!");
                System.out.println(hotelResource.getCustomer(email));
                invalidEmail = false;

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid email address");
                System.out.println("Please enter your email in name@domain.com format: ");
            }
        }

    }

    public static void findAndReserveARoom() {
        final Scanner input = new Scanner(System.in);
        System.out.println("Please enter your email");
        String email = input.nextLine();

        if (adminResource.getCustomer(email) == null) {
            System.out.println("Invalid email address");
            return;
        }

/*
 if I could not use the getCustomer above
        final List<String> emailList = adminResource
                .getAllCustomers()
                .stream()
                .map(customer -> customer.getEmail())
                .collect(Collectors.toList());
        if (!emailList.contains(email)) {
            System.out.println("Invalid email address");
            return;
        }
*/

        System.out.println("Please enter check-in date as dd-mm-yyyy");

        Date date1;
        while (true) {
            String begin = input.nextLine().strip();
            try {
                date1 = new SimpleDateFormat("dd-MM-yyyy").parse(begin);
                break;
            } catch (ParseException e) {
                System.out.println("Invalid date please enter date as dd-mm-yyyy");
            }

        }

        System.out.println("Please enter check-out date");
        Date date2;

        while (true) {
            String end = input.nextLine().strip();
            try {
                date2 = new SimpleDateFormat("dd-MM-yyyy").parse(end);
                break;
            } catch (ParseException e) {
                System.out.println("Invalid date please enter date as dd-mm-yyyy");
            }

        }


        System.out.println("-------------------------------------------------");

        Collection<IRoom> roomsFound = hotelResource.findARoom(date1, date2);
        // search for alternatives
        if (roomsFound.isEmpty()) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date1);
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            date1 = calendar.getTime();
            calendar.setTime(date2);
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            date2 = calendar.getTime();

            roomsFound = getRoomsWithAlternativeDates(date1, date2);

        }
        // no alternatives were found
        if (roomsFound.isEmpty()) {
            System.out.println("No alternatives were found");
            return;
        }
        roomsFound.forEach(System.out::println);

        System.out.println("-------------------------------------------------");
        System.out.println("Please select a room number or 0 to exit");
        String roomNumber = input.nextLine();
        if (roomNumber.equals("0")) return;

        IRoom room;
        try {
            room = hotelResource.getRoom(roomNumber);
        } catch (IllegalArgumentException e) {
            System.out.println("You selected an invalid room number");
            return;
        }
        if (roomsFound.contains(room)) {
            hotelResource.bookARoom(email, room, date1, date2);
            System.out.println("Room " + roomNumber + " has been booked");
            System.out.println();
        } else {
            System.out.println("This room is not available at this time");
            System.out.println();
        }

    }

    private static Collection<IRoom> getRoomsWithAlternativeDates(Date newCheckIn, Date newCheckOut) {
        Collection<IRoom> roomsFound;
        roomsFound = hotelResource.findARoom(newCheckIn, newCheckOut);
        System.out.println("There were no rooms available for your dates");
        System.out.println("We have searched for alternative check-in on " + newCheckIn + " and check-out on " + newCheckOut );
        return roomsFound;
    }

    public static void seeMyReservations() {
        final Scanner input = new Scanner(System.in);
        System.out.println("Please enter your email:");
        Collection<Reservation> reservations;
        try{
            String email = input.nextLine();
            reservations = hotelResource.getCustomersReservations(email);

            } catch (NullPointerException e) {
                System.out.println("Email address given does not exist");
                return;
            }
        if (reservations != null) {

            reservations.forEach(reservation -> System.out.println(reservation));

        } else {
            System.out.println("You have no reservations");
        }

        }


}

