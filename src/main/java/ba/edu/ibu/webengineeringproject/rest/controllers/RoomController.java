package ba.edu.ibu.webengineeringproject.rest.controllers;

import ba.edu.ibu.webengineeringproject.core.model.Room;
import ba.edu.ibu.webengineeringproject.core.service.RoomService;
import ba.edu.ibu.webengineeringproject.rest.dto.RoomDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<RoomDTO>> getRooms() {
        return ResponseEntity.ok(roomService.getRooms());
    }

    @SecurityRequirement(name = "JWT Security")
    @PreAuthorize("hasAuthority('OWNER')")
    @RequestMapping(method = RequestMethod.POST, path = "/new")
    public ResponseEntity<RoomDTO> registerRoom(@RequestBody Room room) {
        return ResponseEntity.ok(roomService.addRoom(room));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/room/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable String id) {
        return ResponseEntity.ok(roomService.getRoomById(id));
    }
    @RequestMapping(method = RequestMethod.GET, path = "capacity/{capacity}")
    public ResponseEntity<List<RoomDTO>> getRoomsByCapacity(@PathVariable int capacity) {
        return ResponseEntity.ok(roomService.findAllByCapacity(capacity));
    }

    @RequestMapping(method = RequestMethod.GET, path = "hotelId/{hotelId}")
    public ResponseEntity<List<RoomDTO>> getRoomsByHotel(@PathVariable String hotelId) {
        return ResponseEntity.ok(roomService.findAllByHotelId(hotelId));
    }

    @RequestMapping(method = RequestMethod.GET, path = "available/{dateFrom}/{dateTo}")
    public ResponseEntity<List<RoomDTO>> getAvailableRooms(@PathVariable String dateFrom, @PathVariable String dateTo) {
        LocalDateTime localDateFrom = LocalDateTime.parse(dateFrom, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime localDateTo = LocalDateTime.parse(dateTo, DateTimeFormatter.ISO_DATE_TIME);
        return ResponseEntity.ok(roomService.findAvailableRooms(localDateFrom, localDateTo));
    }

    @RequestMapping(method = RequestMethod.GET, path = "availableCapacity/{dateFrom}/{dateTo}/{capacity}")
    public ResponseEntity<List<RoomDTO>> getAvailableRoomsWithCapacity(@PathVariable String dateFrom, @PathVariable String dateTo, @PathVariable int capacity) {
        LocalDateTime localDateFrom = LocalDateTime.parse(dateFrom, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime localDateTo = LocalDateTime.parse(dateTo, DateTimeFormatter.ISO_DATE_TIME);
        return ResponseEntity.ok(roomService.findAvailableRoomsWithCapacity(localDateFrom, localDateTo, capacity));
    }

    @RequestMapping(method = RequestMethod.GET, path = "availableHotel/{dateFrom}/{dateTo}/{hotelId}")
    public ResponseEntity<List<RoomDTO>> getAvailableRoomsWithHotel(@PathVariable String dateFrom, @PathVariable String dateTo, @PathVariable String hotelId) {
        LocalDateTime localDateFrom = LocalDateTime.parse(dateFrom, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime localDateTo = LocalDateTime.parse(dateTo, DateTimeFormatter.ISO_DATE_TIME);
        return ResponseEntity.ok(roomService.findAvailableRoomsWithHotelId(localDateFrom, localDateTo, hotelId));
    }

    @RequestMapping(method = RequestMethod.GET, path = "availableHotelCapacity/{dateFrom}/{dateTo}/{hotelId}/{capacity}")
    public ResponseEntity<List<RoomDTO>> getAvailableRoomsWithHotel(@PathVariable String dateFrom, @PathVariable String dateTo, @PathVariable String hotelId, @PathVariable int capacity) {
        LocalDateTime localDateFrom = LocalDateTime.parse(dateFrom, DateTimeFormatter.ISO_DATE_TIME);
        LocalDateTime localDateTo = LocalDateTime.parse(dateTo, DateTimeFormatter.ISO_DATE_TIME);
        return ResponseEntity.ok(roomService.findAvailableRoomsWithCapacityAndHotelId(localDateFrom, localDateTo, capacity, hotelId));
    }


    @SecurityRequirement(name = "JWT Security")
    @PreAuthorize("hasAuthority('OWNER')")
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<RoomDTO> updateRoom(@PathVariable String id, @RequestBody Room room) {
        return ResponseEntity.ok(roomService.updateRoom(id, room));
    }

    @SecurityRequirement(name = "JWT Security")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'OWNER')")
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable String id) {
        roomService.deleteRoom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
