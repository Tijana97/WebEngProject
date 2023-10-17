package ba.edu.ibu.webengineeringproject.core.model;

public class Hotel {
    private final int id;

    private final int ownerId;

    private String name;
    private String city;
    private String country;
    private String emailAddress;
    private String phoneNumber;

    public Hotel(int id, int ownerId, String name, String city, String country, String emailAddress, String phoneNumber) {
        this.id = id;
        this.ownerId = ownerId;
        this.name = name;
        this.city = city;
        this.country = country;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
