package service;

import model.Customer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class CustomerService {
//    HashMap<String, String[]> customerTable = new HashMap<>();
    private List<Customer> customerList;
//provide a static reference
    private static CustomerService instance = new CustomerService();

    private CustomerService() {
        this.customerList = new ArrayList<>();
    }

    public CustomerService getInstance() {
        return instance;
    }

    public void addCustomer(String email, String firstName, String lastName) {

    }
    // do I use Collection<Customer> as in description?
    public List<Customer> getAllCustomers() {
        return this.customerList;

    }

    public Customer getCustomer(String customerEmail) {
        for (Customer customer : customerList) {
            if (customer.getEmail().equals(customerEmail)) {
                return customer;
            }
        }
        return null;
    }


}
