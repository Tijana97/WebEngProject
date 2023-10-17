package ba.edu.ibu.webengineeringproject.core.repository;

import ba.edu.ibu.webengineeringproject.core.model.Hotel;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class HotelRepository {
    private List<Hotel> hotels;

    public HotelRepository(){
        this.hotels = Arrays.asList(
                new Hotel(1, 3,"Holiday Inn", "Sarajevo", "BiH", "support@holidayInn.com", "0038733333333"),
                new Hotel(2, 3, "Ibis Styles", "Zagreb", "Croatia", "support@ibis.com", "0038535555555")
        );
    }

    public List<Hotel> findAll(){
        return hotels;
    }

    public Hotel findById(int id){
        return hotels.stream().filter(hotel -> hotel.getId()==id).findFirst().orElse(null);
    }
}
