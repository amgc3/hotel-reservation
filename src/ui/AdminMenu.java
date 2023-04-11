package ui;

import api.AdminResource;
import model.IRoom;
import model.Room;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    static ReservationService reservationService = ReservationService.getInstance();

    public static void adminFunctions() {
        boolean changeMenu = false;
        do {

        int selection = printAdminMenuAndGetSelection();

            switch (selection) {
                case 1 -> CustomerService.getInstance().getAllCustomers();
                case 2 -> seeAllRooms();
                case 3 -> ReservationService.getInstance().printAllReservation();
                case 4 -> addARoom(); /// add a room
                case 5 -> System.out.println("5. Add Test Data");
                case 6 -> {
                    changeMenu = true;
                    MainMenu.printMenuAndGetSelection();
                }
            }

        } while (!changeMenu);
    }


    public static int printAdminMenuAndGetSelection() {
        System.out.println("Admin Menu");
        System.out.println("-------------------------------");
        System.out.println("1. See all Customers");
        System.out.println("2. See all Rooms"); //reservationService.getAllRooms()
        System.out.println("3. See all Reservations");
        System.out.println("4. Add a Room");
        System.out.println("5. Add Test Data");
        System.out.println("6. Back to Main Menu");
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
            System.out.println("Enter Price per Night: ");
            Double price = Double.parseDouble(input.nextLine());
            System.out.println("Enter Room Type: 1 for Single, 2 for Double");
            RoomType type = Integer.parseInt(input.nextLine()) == 1 ? RoomType.SINGLE : RoomType.DOUBLE;
            Room room = new Room(roomNumber, price, type);
            roomList.add(room);

            System.out.println("Would you like to add another room? y/n");
            String response = input.nextLine().toLowerCase();
            boolean validInput = response.equals("y") || response.equals("n");
            while (!validInput) {
                System.out.println("Please enter y/n");
                response = input.nextLine().toLowerCase();
            }
            if (response.equals("n")) {
                AdminResource.getInstance().addRoom(roomList);
                break;
            }

        }
        //System.out.println(roomList);

    }

    public static Collection<IRoom> seeAllRooms() {
        return reservationService.getHotelRooms().values();

    }
}
