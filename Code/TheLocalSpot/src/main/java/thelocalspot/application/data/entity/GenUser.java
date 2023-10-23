package thelocalspot.application.data.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class GenUser {

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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String printDetailsTest(Set<String> preferences) {
        return String.format(
                "General User[id=%d, firstName='%s', lastName='%s', email='%s', role='%s', address='%s', zipCode='%d', phoneNumber='%s', preferences='%s']",
                id, firstName, lastName, email, role, address, zipCode, phoneNumber, preferencesToString(preferences));
    }

    private String preferencesToString(Set<String> preferences){
        return String.join(",",preferences);
    }
}
