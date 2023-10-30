package thelocalspot.application.data.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.Set;

@Entity
@Table(name = "COORDINATOR")
public class CoordUser extends AbstractEntity{
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

    private String genresToString(Set<String> preferences){
        return String.join(",",preferences);
    }

    public void setFirstNameName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
