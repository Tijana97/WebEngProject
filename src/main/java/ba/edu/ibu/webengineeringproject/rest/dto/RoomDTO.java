package ba.edu.ibu.webengineeringproject.rest.dto;

import ba.edu.ibu.webengineeringproject.core.model.Room;

public class RoomDTO {
    private String id;

    private String hotelId;

    private double price;

    private int capacity;
    private String description;

    private HotelDTO hotelDTO;

    public RoomDTO(Room room, HotelDTO hotelDTO){
        this.id = room.getId();
        this.hotelId = room.getHotelId();
        this.price = room.getPrice();
        this.description = room.getDescription();
        this.capacity = room.getCapacity();
        this.hotelDTO = hotelDTO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public HotelDTO getHotelDTO() {
        return hotelDTO;
    }

    public void setHotelDTO(HotelDTO hotelDTO) {
        this.hotelDTO = hotelDTO;
    }
}
