package ba.edu.ibu.webengineeringproject.core.repository;

import ba.edu.ibu.webengineeringproject.core.model.Hotel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class HotelRepositoryTest {
    @Autowired
    private HotelRepository hotelRepository;

    @Test
    public void shouldReturnAllHotels(){
        List<Hotel> hotels = hotelRepository.findAll();
        Assertions.assertEquals(2, hotels.size());
    }

    @Test
    public void shouldReturnHotelById(){
        Optional<Hotel> hotel = hotelRepository.findById("65354359a2b43f7645b5a2a8");
        Assertions.assertEquals("Ibis Styles", hotel.get().getName());
    }
}
