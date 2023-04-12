import ui.AdminMenu;
import ui.MainMenu;

public class HotelApplication {

    public static void main(String[] args) {

        boolean exit = false;
        while(!exit) {
            int selection = MainMenu.printMenuAndGetSelection();

            switch (selection) {
                case 1 -> System.out.println("1. Find and reserve a room");
                case 2 -> System.out.println("2. See my reservations");
                case 3 -> MainMenu.createAccount();
                case 4 -> AdminMenu.adminFunctions();
                case 5 -> {
                    exit = true;
                    System.out.println("Goodbye");
                }
            }
        }
    }
}






