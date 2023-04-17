package model;

import java.util.regex.Pattern;

public class Customer {
    private final String firstName;
    private final String lastName;
    private String email;
    private final String emailRegex = "^(.+)@(.+).com";

    public Customer(String firstName, String lastName, String email) {
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Error, invalid email");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }



    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (!getFirstName().equals(customer.getFirstName())) return false;
        if (!getLastName().equals(customer.getLastName())) return false;
        return getEmail().equals(customer.getEmail());
    }

    @Override
    public int hashCode() {
        int result = getFirstName().hashCode();
        result = 31 * result + getLastName().hashCode();
        result = 31 * result + getEmail().hashCode();
        return result;
    }
}
