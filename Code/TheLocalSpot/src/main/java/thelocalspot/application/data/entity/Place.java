package thelocalspot.application.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PLACES")
public class Place extends AbstractEntity{
    private Long hostId;
    private String placeName;
    private String placeAddress;

    private Integer zipCode;
    private String placeCapacity;
    private String placeInfo;

    public Place(){

    }

    public Place(Long hostId, String placeName, String placeAddress, String placeCapacity, String placeInfo){
        this.hostId = hostId;
        this.placeName = placeName;
        this.placeAddress = placeAddress;
        this.placeCapacity = placeCapacity;
        this.placeInfo = placeInfo;
    }

    public Long getHostId() {
        return hostId;
    }

    public void setHostId(Long hostId) {
        this.hostId = hostId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getPlaceCapacity() {
        return placeCapacity;
    }

    public void setPlaceCapacity(String placeCapacity) {
        this.placeCapacity = placeCapacity;
    }

    public String getPlaceInfo() {
        return placeInfo;
    }

    public void setPlaceInfo(String placeInfo) {
        this.placeInfo = placeInfo;
    }
}