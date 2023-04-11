package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private Map<String, Customer> customerMap;

    private static final CustomerService instance = new CustomerService();

    private CustomerService() {
        this.customerMap = new HashMap<>();
    }

    public static CustomerService getInstance() {
        return instance;
    }

    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        customerMap.put(customer.getEmail(), customer);

    }

    public Customer getCustomer(String customerEmail) {
        return customerMap.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers() {
        return customerMap.values();

    }


}
