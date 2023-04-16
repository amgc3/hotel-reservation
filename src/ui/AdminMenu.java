package ui;

import api.AdminResource;
import model.IRoom;
import model.Room;
import model.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
        while(true) {

            System.out.println("Enter Room Number: ");
            String roomNumber = input.nextLine();
            if (!isNumber(roomNumber)) {
                System.out.println("Input error. That is not a valid number");
                System.out.println();
                return;
            }
            System.out.println("Enter Price per Night: ");
            Double price = Double.parseDouble(input.nextLine());
            System.out.println("Enter Room Type: 1 for Single, 2 for Double");
            RoomType type = Integer.parseInt(input.nextLine()) == 1 ? RoomType.SINGLE : RoomType.DOUBLE;
            Room room = new Room(roomNumber, price, type);
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
