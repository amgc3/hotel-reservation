package service;

import model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService {
    private Map<String, Customer> customerMap;
//provide a static reference
    private static CustomerService instance = new CustomerService();

    private CustomerService() {
        this.customerMap = new HashMap<>();
    }

    public CustomerService getInstance() {
        return instance;
    }

    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        customerMap.put(customer.getEmail(), customer);

    }
    // do I use Collection<Customer> as in description?
    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();
        for (Customer customer : customerMap.values()) {
            customerList.add(customer);
        }
        return customerList;

    }

    public Customer getCustomer(String customerEmail) {
        return customerMap.get(customerEmail);
    }


}
