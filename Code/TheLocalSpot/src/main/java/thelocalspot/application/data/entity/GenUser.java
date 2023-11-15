package thelocalspot.application.data.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "GENUSER")
public class GenUser extends AbstractEntity{

    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String address;
    private Integer zipCode;
    private String phoneNumber;
    private String preferences;

    public GenUser() {
    }

    public GenUser(String firstName, String lastName, String email, String role, String address, int zipCode, String phoneNumber, Set<String> preferences) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.preferences = preferencesToString(preferences);
    }

    private String preferencesToString(Set<String> preferences){
        return String.join(", ",preferences);
    }

    public String getFullName(){
        return firstName + " " + lastName;
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

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }
}
