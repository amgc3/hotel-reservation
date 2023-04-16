package ui;

import api.AdminResource;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AdminMenu {
    static AdminResource adminResource = AdminResource.getInstance();

    public static void adminFunctions() {
        boolean changeMenu = false;
        do {

        int selection = printAdminMenuAndGetSelection();

            switch (selection) {
                case 1 -> seeAllCustomers();
                case 2 -> seeAllRooms();
                case 3 -> seeAllReservations();
                case 4 -> addARoom(); // add as many as you want
                case 5 -> changeMenu = true;
            }

        } while (!changeMenu);
    }

    public static int printAdminMenuAndGetSelection() {
        System.out.println("Admin Menu");
        System.out.println("-------------------------------");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms");
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Back to Main Menu");
        System.out.println("-------------------------------");

        return readInt("Please select a number from the above choices");
    }

    public static int readInt(String prompt) {
        final Scanner input = new Scanner(System.in);
        int num;

        System.out.println(prompt);


        while (true) {
            try{

                num = Integer.parseInt(input.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Input error. Please select a number from the above menu options");
            }
        }
        return num;
    }

    private static void addARoom() {
        final Scanner input = new Scanner(System.in);


        List<IRoom>  roomList = new ArrayList<>();
        List<String> listOfNumbers = new ArrayList<>();
        Double price;

        while(true) {

            System.out.println("Enter Room Number: ");
            String roomNumber = input.nextLine();
            if (!isNumber(roomNumber)) {
                System.out.println("Input error. That is not a valid number");
                System.out.println();
                return;
            }
            if (!isUnique(roomNumber) || listOfNumbers.contains(roomNumber)) {
                System.out.println("Invalid number, it is already associated with a room!");
                System.out.println();
                return;
            }
            try {
                System.out.println("Enter Price per Night: ");
                price = Double.parseDouble(input.nextLine());

            } catch (NumberFormatException e) {
                System.out.println("Invalid input for price entered");
                System.out.println();
                return;
            } catch (NullPointerException e) {
                System.out.println("No value for price entered ");
                System.out.println();
                return;
            }

            System.out.println("Enter Room Type: 1 for Single, 2 for Double");
            RoomType type;
            try {
                type = Integer.parseInt(input.nextLine()) == 1 ? RoomType.SINGLE : RoomType.DOUBLE;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number entered for room type");
                System.out.println();
                return;
            }
            Room room = new Room(roomNumber, price, type);
            listOfNumbers.add(roomNumber);
            roomList.add(room);

            System.out.println("Would you like to add another room? y/n");
            String response = input.nextLine().toLowerCase();
            while (true) {
                if (response.equals("y")) {
                    break;
                } else if (response.equals("n")) {
                    adminResource.addRoom(roomList);
                    return;
                } else {
                    System.out.println("Please enter y/n");
                    response = input.nextLine().toLowerCase();

                }
            }

        }

    }

    static boolean isNumber(String s)
    {
        for (int i = 0; i < s.length(); i++)
            if (Character.isDigit(s.charAt(i)) == false)
                return false;

        return true;
    }
    static boolean isUnique(String num) {

        List<IRoom> rooms = adminResource.getAllRooms()
                .stream()
                .filter(room -> room.getRoomNumber().equals(num))
                .collect(Collectors.toList());

        return rooms.isEmpty();
    }

    public static void seeAllRooms() {
        adminResource.getAllRooms().forEach(room -> System.out.println(room));

    }

    private static void seeAllReservations() {
        adminResource.displayAllReservations();
    }

    private static void seeAllCustomers() {
        adminResource.getAllCustomers()
                .forEach(customer -> System.out.println(customer));
    }
}
