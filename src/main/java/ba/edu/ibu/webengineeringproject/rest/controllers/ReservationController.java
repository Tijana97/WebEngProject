package ba.edu.ibu.webengineeringproject.rest.controllers;

import ba.edu.ibu.webengineeringproject.core.model.Reservation;
import ba.edu.ibu.webengineeringproject.core.service.ReservationService;
import ba.edu.ibu.webengineeringproject.rest.dto.ReservationDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@SecurityRequirement(name = "JWT Security")
public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<ReservationDTO>> getReservations() {
        return ResponseEntity.ok(reservationService.findAll());
    }

    @PreAuthorize("hasAuthority('CLIENT')")
    @RequestMapping(method = RequestMethod.POST, path = "/new")
    public ResponseEntity<ReservationDTO> makeAReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.addReservation(reservation));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable String id) {
        return ResponseEntity.ok(reservationService.findById(id));
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
