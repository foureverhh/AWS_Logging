package model;

import java.util.Objects;

public class Customer extends Person {
    private String email;

    public String getEmail() {
        return email;
    }

    public Customer(int id, String firstName, String lastName, String security, String password, String email) {
        super(id, firstName, lastName, security, password);
        this.email = email;
    }

    public Customer(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Customer customer = (Customer) o;
        return email.equals(customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email);
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " " + email;
    }
}
