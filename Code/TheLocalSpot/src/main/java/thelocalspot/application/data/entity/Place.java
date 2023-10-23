package thelocalspot.application.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long hostId;
    private String placeName;
    private String address;
    private Integer capacity;
    private String information;

    public Place(){

    }

    public Place(String placeName, String address, Integer capacity, String information){
        this.placeName = placeName;
        this.address = address;
        this.capacity = capacity;
        this.information = information;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
