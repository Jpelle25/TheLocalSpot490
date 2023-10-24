package thelocalspot.application.data.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.Set;

@Entity
public class CoordUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long coordId;
    private String firstName;
    private String lastName;
    private String coordGenre;
    private String email;
    private String address;
    private Integer zipCode;
    private String role;
    private String phoneNumber;

    public CoordUser() {

    }

    public CoordUser(String firstName, String lastName, String coordGenre, String email, String role, String address, int zipCode, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.coordGenre = coordGenre;
        this.email = email;
        this.role = role;
        this.address = address;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
    }

    public void setCoordId(Long coordId) {
        this.coordId = coordId;
    }
    public Long getCoordId() {
        return coordId;
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
}
