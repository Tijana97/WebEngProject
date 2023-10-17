package ba.edu.ibu.webengineeringproject.rest.controllers;

import ba.edu.ibu.webengineeringproject.core.model.Hotel;
import ba.edu.ibu.webengineeringproject.core.service.HotelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    private HotelService hotelService;

    public HotelController(HotelService hotelService){
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<Hotel> findAll(){
        return hotelService.findAll();
    }

    @GetMapping("/{id}")
    public Hotel findById(@PathVariable int id){
        return hotelService.findById(id);
    }
}
