package ba.edu.ibu.webengineeringproject.rest.controllers;

import ba.edu.ibu.webengineeringproject.core.model.Reservation;
import ba.edu.ibu.webengineeringproject.core.service.ReservationService;
import ba.edu.ibu.webengineeringproject.rest.dto.ReservationDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@SecurityRequirement(name = "JWT Security")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<ReservationDTO>> getReservations() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @PreAuthorize("hasAuthority('CLIENT')")
    @RequestMapping(method = RequestMethod.POST, path = "/new")
    public ResponseEntity<ReservationDTO> makeAReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.addReservation(reservation));
    }

    @PreAuthorize("hasAnyAuthority('CLIENT', 'OWNER')")
    @RequestMapping(method = RequestMethod.GET, path = "/reservation/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable String id) {
        return ResponseEntity.ok(reservationService.findById(id));
    }

    @PreAuthorize("hasAuthority('CLIENT')")
    @RequestMapping(method = RequestMethod.GET, path = "/user/{userId}")
    public ResponseEntity<List<ReservationDTO>> getReservationByUserId(@PathVariable String userId){
        return  ResponseEntity.ok(reservationService.findByUserId(userId));
    }

    @PreAuthorize("hasAuthority('OWNER')")
    @RequestMapping(method = RequestMethod.GET, path = "/room/{roomId}")
    public ResponseEntity<List<ReservationDTO>> getReservationByRoomId(@PathVariable String roomId){
        return  ResponseEntity.ok(reservationService.findByRoomId(roomId));
    }
    @PreAuthorize("hasAuthority('CLIENT')")
    @RequestMapping(method = RequestMethod.GET, path = "/past/{userId}")
    public ResponseEntity<List<ReservationDTO>> getPastReservationsByUserId(@PathVariable String userId){
        return  ResponseEntity.ok(reservationService.findPastReservationsByUserId(userId));
    }

    @PreAuthorize("hasAuthority('CLIENT')")
    @RequestMapping(method = RequestMethod.GET, path = "/future/{userId}")
    public ResponseEntity<List<ReservationDTO>> getFutureReservationsByUserId(@PathVariable String userId){
        return  ResponseEntity.ok(reservationService.findFutureReservationsByUserId(userId));
    }



    @PreAuthorize("hasAuthority('CLIENT')")
    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable String id, @RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.updateReservation(id, reservation));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CLIENT')")
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable String id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
