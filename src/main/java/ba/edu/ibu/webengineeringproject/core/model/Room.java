package ba.edu.ibu.webengineeringproject.core.model;

public class Room {
    private final int id;

    private final int hotelId;

    private double price;

    private int capacity;
    private String description;

    public Room(int id, int hotelId, double price, int capacity, String description) {
        this.id = id;
        this.hotelId = hotelId;
        this.price = price;
        this.capacity = capacity;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public double getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
