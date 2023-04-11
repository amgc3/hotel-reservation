package ui;

import service.CustomerService;

import java.util.Scanner;

public class MainMenu {

       static CustomerService customerService = CustomerService.getInstance();


    public static int printMenuAndGetSelection() {
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
            try{

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
        String email = input.nextLine();
        try {
            customerService.addCustomer(email, name, surname);

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid email address");
            System.out.println("Please enter your email in name@domain.com format: ");
            email = input.nextLine();
            customerService.addCustomer(email, name, surname);
        } finally {

            System.out.println("Account Created!");
        }


        System.out.println(customerService.getAllCustomers());
    }


}
