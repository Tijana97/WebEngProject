package ba.edu.ibu.webengineeringproject.core.model;

public class User {
    private final int id;
    private final String firstName;
    private final String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String password;
    private String address;
    private final boolean isAdmin;
    private final boolean isOwner;
    private boolean isAuthorized;

    public User(int id, String firstName, String lastName, String emailAddress, String phoneNumber, String password, String address, boolean isAdmin, boolean isOwner, boolean isAuthorized) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.address = address;
        this.isAdmin = isAdmin;
        this.isOwner = isOwner;
        this.isAuthorized = isAuthorized;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isOwner() {
        return isOwner;
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }
}
