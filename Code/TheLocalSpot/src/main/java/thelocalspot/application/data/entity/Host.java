package thelocalspot.application.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHostId() {
        return id;
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
}
