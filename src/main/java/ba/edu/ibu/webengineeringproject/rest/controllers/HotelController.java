package ba.edu.ibu.webengineeringproject.rest.controllers;

import ba.edu.ibu.webengineeringproject.core.model.Hotel;
import ba.edu.ibu.webengineeringproject.core.service.HotelService;
import ba.edu.ibu.webengineeringproject.rest.dto.HotelDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3001", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS })
@RestController
@RequestMapping("/api/hotels")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService){
        this.hotelService = hotelService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<HotelDTO>> getHotels() {
        return ResponseEntity.ok(hotelService.getHotels());
    }

    @SecurityRequirement(name = "JWT Security")
    @PreAuthorize("hasAuthority('OWNER')")
    @RequestMapping(method = RequestMethod.POST, path = "/new")
    public ResponseEntity<HotelDTO> registerHotel(@RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelService.addHotel(hotel));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable String id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }

    @SecurityRequirement(name = "JWT Security")
    @PreAuthorize("hasAuthority('OWNER')")
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<HotelDTO> updateHotel(@PathVariable String id, @RequestBody Hotel hotel) {
        return ResponseEntity.ok(hotelService.updateHotel(hotel, id));
    }

    @SecurityRequirement(name = "JWT Security")
    @PreAuthorize("hasAnyAuthority('ADMIN','OWNER')")
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable String id) {
        hotelService.deleteHotel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}