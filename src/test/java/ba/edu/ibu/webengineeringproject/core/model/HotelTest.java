package ba.edu.ibu.webengineeringproject.core.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HotelTest {

    @Test
    public void shouldCreateNewHotel(){
        Hotel hotel = new Hotel("hotelID","ownerId", "hotel", "Sarajevo", "BiH","hotel@email.com", "+0111111");
        Assertions.assertEquals("hotel", hotel.getName());
        Assertions.assertEquals("hotel@email.com", hotel.getEmailAddress());
    }

    @Test
    public void shouldChangeHotelName(){
        Hotel hotel = new Hotel("hotelID","ownerId", "hotel", "Sarajevo", "BiH","hotel@email.com", "+0111111");
        hotel.setName("new hotel");
        Assertions.assertEquals("new hotel", hotel.getName());
    }
}
