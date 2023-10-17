package ba.edu.ibu.webengineeringproject.core.repository;

import ba.edu.ibu.webengineeringproject.core.model.Reservation;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class ReservationRepository {

    private List<Reservation> reservations;

    public ReservationRepository(){
        this.reservations = Arrays.asList(
                new Reservation(1, 1,1, new Date(123, 10, 10), new Date(123,10,13)),
                new Reservation(2,2,1,new Date(123, 10, 10), new Date(123,10,13))
        );
    }

    public List<Reservation> findAll(){
        return reservations;
    }

    public Reservation findById(int id){
        return reservations.stream().filter(reservation -> reservation.getId() ==  id).findFirst().orElse(null);
    }
}
