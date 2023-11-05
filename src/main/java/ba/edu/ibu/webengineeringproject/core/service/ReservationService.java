package ba.edu.ibu.webengineeringproject.core.service;

import ba.edu.ibu.webengineeringproject.core.exceptions.general.BadRequestException;
import ba.edu.ibu.webengineeringproject.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.webengineeringproject.core.model.Reservation;
import ba.edu.ibu.webengineeringproject.core.model.Room;
import ba.edu.ibu.webengineeringproject.core.model.User;
import ba.edu.ibu.webengineeringproject.core.model.enums.UserType;
import ba.edu.ibu.webengineeringproject.core.repository.ReservationRepository;
import ba.edu.ibu.webengineeringproject.core.repository.RoomRepository;
import ba.edu.ibu.webengineeringproject.core.repository.UserRepository;
import ba.edu.ibu.webengineeringproject.rest.dto.ReservationDTO;
import ba.edu.ibu.webengineeringproject.rest.dto.RoomDTO;
import ba.edu.ibu.webengineeringproject.rest.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomService roomService;
    private final RoomRepository roomRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public ReservationService(ReservationRepository reservationRepository, RoomService roomService, RoomRepository roomRepository, UserService userService, UserRepository userRepository){
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
        this.roomService = roomService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public List<ReservationDTO> findAll(){

        List<Reservation> reservations= reservationRepository.findAll();
        List<ReservationDTO> reservationDTOS = new ArrayList<>();
        for (Reservation reservation: reservations) {
            RoomDTO roomDTO = roomService.getRoomById(reservation.getRoomId());
            UserDTO userDTO = userService.getUserById(reservation.getUserId());
            String userName = userDTO.getFirstName() + " " + userDTO.getLastName();
            ReservationDTO reservationDTO = new ReservationDTO(reservation, roomDTO, userName);
            reservationDTOS.add(reservationDTO);

        }
        return reservationDTOS;
    }

    public  ReservationDTO findById(String id){
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if(reservation.isEmpty()){
            throw new ResourceNotFoundException("The reservation with the given ID does not exist.");
        }
        RoomDTO roomDTO = roomService.getRoomById(reservation.get().getRoomId());
        UserDTO userDTO = userService.getUserById(reservation.get().getUserId());
        String userName = userDTO.getFirstName() + " " + userDTO.getLastName();
        return new ReservationDTO(reservation.get(),roomDTO,userName);
    }

    public ReservationDTO addReservation(Reservation payload){
        Optional<User> user = userRepository.findById(payload.getUserId());
        if(user.isEmpty()){
            throw new ResourceNotFoundException("The user with the given ID does not exist.");
        }
        Optional<Room> room = roomRepository.findById(payload.getRoomId());
        if(room.isEmpty()){
            throw new ResourceNotFoundException("The room with the given ID does not exist.");
        }
        if(!user.get().getUserType().equals(UserType.CLIENT)){
            throw new BadRequestException("Only client users can make a reservation.");
        }
        Reservation reservation = reservationRepository.save(payload);
        String userName = user.get().getFirstName() + " " + user.get().getLastName();
        RoomDTO roomDTO = roomService.getRoomById(reservation.getRoomId());
        return new ReservationDTO(reservation, roomDTO, userName);
    }

    public ReservationDTO updateReservation(String id, Reservation payload){
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if(reservation.isEmpty()){
            throw new ResourceNotFoundException("The reservation with the given ID does not exist.");
        }
        Optional<User> user = userRepository.findById(payload.getUserId());
        if(user.isEmpty()){
            throw new ResourceNotFoundException("The user with the given ID does not exist.");
        }
        if(!user.get().getUserType().equals(UserType.CLIENT)){
            throw new BadRequestException("Only client users can make a reservation.");
        }
        Optional<Room> room = roomRepository.findById(payload.getRoomId());
        if(room.isEmpty()){
            throw new ResourceNotFoundException("The room with the given ID does not exist.");
        }
        payload.setId(reservation.get().getId());
        Reservation updatedReservation = reservationRepository.save(payload);
        String userName = user.get().getFirstName() + " " + user.get().getLastName();
        RoomDTO roomDTO = roomService.getRoomById(updatedReservation.getRoomId());
        return new ReservationDTO(updatedReservation, roomDTO, userName);
    }

    public void deleteReservation(String id){
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if(reservation.isEmpty()){
            throw new ResourceNotFoundException("The reservation with the given ID does not exist.");
        }
        reservationRepository.deleteById(id);
    }
}
