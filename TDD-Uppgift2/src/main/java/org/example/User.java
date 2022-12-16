package org.example;

public class User {

    String firstName;
    String lastName;

    public String toString() {
        return "User [firstName: " + firstName + "lastName: " + lastName + "]";
    }

    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
