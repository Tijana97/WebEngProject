package ba.edu.ibu.webengineeringproject.core.service;

import ba.edu.ibu.webengineeringproject.core.model.Reservation;
import ba.edu.ibu.webengineeringproject.core.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> findAll(){
        return reservationRepository.findAll();
    }

    public  Reservation findById(int id){
        return reservationRepository.findById(id);
    }
}
