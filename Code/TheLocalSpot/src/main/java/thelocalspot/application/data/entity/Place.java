package thelocalspot.application.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PLACES")
public class Place extends AbstractEntity{
    private Long hostId;
    private String placeName;
    private String placeAddress;
    private Integer placeCapacity;
    private String placeInfo;

    public Place(){

    }

    public Place(String placeName, String placeAddress, Integer placeCapacity, String placeInfo){
        this.placeName = placeName;
        this.placeAddress = placeAddress;
        this.placeCapacity = placeCapacity;
        this.placeInfo = placeInfo;
    }


    public String getPlaceName() {
        return placeName;
    }


    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public String getAddress() {
        return placeAddress;
    }

    public void setAddress(String address) {
        this.placeAddress = placeAddress;
    }

    public Integer getCapacity() {
        return placeCapacity;
    }

    public void setCapacity(Integer capacity) {
        this.placeCapacity = placeCapacity;
    }

    public String getInformation() {
        return placeInfo;
    }

    public void setInformation(String information) {
        this.placeInfo = placeInfo;
    }
}
