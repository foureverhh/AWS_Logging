package model;

import java.util.Objects;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String security;
    private String password;

    public Person() {
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSecurity() {
        return security;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                firstName.equals(person.firstName) &&
                lastName.equals(person.lastName) &&
                security.equals(person.security) &&
                password.equals(person.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, security, password);
    }
}
