package ba.edu.ibu.webengineeringproject.rest.dto;

import ba.edu.ibu.webengineeringproject.core.model.Hotel;

public class HotelDTO {
    private String id;

    private String ownerId;

    private String name;
    private String city;
    private String country;
    private String emailAddress;
    private String phoneNumber;
    private String ownerName;

    public HotelDTO(Hotel hotel, String ownerName){
        this.id = hotel.getId();
        this.ownerId = hotel.getOwnerId();
        this.name = hotel.getName();
        this.city = hotel.getCity();
        this.country = hotel.getCountry();
        this.emailAddress = hotel.getEmailAddress();
        this.phoneNumber = hotel.getPhoneNumber();
        this.ownerName = ownerName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
