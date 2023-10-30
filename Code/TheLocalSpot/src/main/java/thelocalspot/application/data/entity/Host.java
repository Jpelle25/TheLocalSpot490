package thelocalspot.application.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "HOSTS")
public class Host extends AbstractEntity{
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String address;
    private Integer zipCode;
    private String phoneNumber;

    public Host() {
    }

    public Host(String firstName, String lastName, String email, String role, String address, int zipCode, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    public String getHostName() {
        return firstName + " " + lastName;
    }

    public void setFirstNameName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastNameName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
