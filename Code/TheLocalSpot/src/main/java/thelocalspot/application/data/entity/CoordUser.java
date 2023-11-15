package thelocalspot.application.data.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "COORDINATOR")
public class CoordUser extends AbstractEntity {
    private String firstName;
    private String lastName;
    @Column(name = "GENRE")
    private String coordGenre;
    private String email;
    private String address;
    private Integer zipCode;
    private String role;
    private String phoneNumber;

    public CoordUser() {

    }

    public CoordUser(String firstName, String lastName, Set<String> coordGenre, String email, String role, String address, int zipCode, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.coordGenre = genresToString(coordGenre);
        this.email = email;
        this.role = role;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    private String genresToString(Set<String> preferences) {
        return String.join(", ", preferences);
    }

    public String getCoordName() {
        return firstName + " " + lastName;
    }

    public void setFirstNameName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
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

    public String getCoordGenre() {
        return coordGenre;
    }

    public void setCoordGenre(String coordGenre) {
        this.coordGenre = coordGenre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
