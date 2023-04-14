import ui.AdminMenu;
import ui.MainMenu;

public class HotelApplication {

    public static void main(String[] args) {

        boolean exit = false;
        while(!exit) {
            int selection = MainMenu.printMenuAndGetSelection();

            switch (selection) {
                case 1 -> MainMenu.findAndReserveARoom();
                case 2 -> MainMenu.seeMyReservations();
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






