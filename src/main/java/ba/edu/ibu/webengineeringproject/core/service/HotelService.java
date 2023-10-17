package ba.edu.ibu.webengineeringproject.core.service;

import ba.edu.ibu.webengineeringproject.core.model.Hotel;
import ba.edu.ibu.webengineeringproject.core.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    private HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository){
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> findAll(){
        return hotelRepository.findAll();
    }

    public  Hotel findById(int id){
        return  hotelRepository.findById(id);
    }
}
