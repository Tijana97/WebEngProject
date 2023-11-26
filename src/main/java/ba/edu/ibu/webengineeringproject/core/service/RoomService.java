package ba.edu.ibu.webengineeringproject.core.service;

import ba.edu.ibu.webengineeringproject.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.webengineeringproject.core.model.Hotel;
import ba.edu.ibu.webengineeringproject.core.model.Reservation;
import ba.edu.ibu.webengineeringproject.core.model.Room;
import ba.edu.ibu.webengineeringproject.core.repository.HotelRepository;
import ba.edu.ibu.webengineeringproject.core.repository.ReservationRepository;
import ba.edu.ibu.webengineeringproject.core.repository.RoomRepository;
import ba.edu.ibu.webengineeringproject.rest.dto.HotelDTO;
import ba.edu.ibu.webengineeringproject.rest.dto.RoomDTO;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final HotelService hotelService;

    private final ReservationRepository reservationRepository;

    public RoomService(RoomRepository roomRepository, HotelRepository hotelRepository, HotelService hotelService, ReservationRepository reservationRepository){
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
        this.hotelService = hotelService;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomDTO> getRooms(){
        List<Room> rooms = roomRepository.findAll();
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room: rooms) {
            HotelDTO hotelDTO = hotelService.getHotelById(room.getHotelId());
            RoomDTO roomDTO = new RoomDTO(room, hotelDTO);
            roomDTOS.add(roomDTO);
        }
        return roomDTOS;
    }

    public List<RoomDTO> findAllByCapacity(int capacity){
        List<Room> rooms = roomRepository.findAllByCapacity(capacity);
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room: rooms) {
            HotelDTO hotelDTO = hotelService.getHotelById(room.getHotelId());
            RoomDTO roomDTO = new RoomDTO(room, hotelDTO);
            roomDTOS.add(roomDTO);
        }
        return roomDTOS;
    }

    public List<RoomDTO> findAllByHotelId(String hotelId){
        List<Room> rooms = roomRepository.findAllByHotelId(hotelId);
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room: rooms) {
            HotelDTO hotelDTO = hotelService.getHotelById(room.getHotelId());
            RoomDTO roomDTO = new RoomDTO(room, hotelDTO);
            roomDTOS.add(roomDTO);
        }
        return roomDTOS;
    }

    public List<RoomDTO> findAvailableRooms(LocalDateTime dateFrom, LocalDateTime dateTo){
        List<Reservation> reservations = reservationRepository.findOccupiedRoomIdsInPeriod(dateFrom, dateTo);
        List<String> roomIds = new ArrayList<>();
        for(Reservation reservation : reservations){
            roomIds.add(reservation.getRoomId());
        }
        List<Room> rooms = roomRepository.findAllAvailableRooms(roomIds);
        List<RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room: rooms) {
            HotelDTO hotelDTO = hotelService.getHotelById(room.getHotelId());
            RoomDTO roomDTO = new RoomDTO(room, hotelDTO);
            roomDTOS.add(roomDTO);
        }
        return roomDTOS;
    }

    public List<RoomDTO> findAvailableRoomsWithCapacity(LocalDateTime dateFrom, LocalDateTime dateTo, int capacity ){
        List<Reservation> reservations = reservationRepository.findOccupiedRoomIdsInPeriod(dateFrom, dateTo);
        List<String> roomIds = new ArrayList<>();
        for(Reservation reservation : reservations){
            roomIds.add(reservation.getRoomId());
        }

        List<Room> rooms = roomRepository.findAllAvailableRoomsWithCapacity(roomIds, capacity);
        List<ba.edu.ibu.webengineeringproject.rest.dto.RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room: rooms) {
            HotelDTO hotelDTO = hotelService.getHotelById(room.getHotelId());
            ba.edu.ibu.webengineeringproject.rest.dto.RoomDTO roomDTO = new ba.edu.ibu.webengineeringproject.rest.dto.RoomDTO(room, hotelDTO);
            roomDTOS.add(roomDTO);
        }
        return roomDTOS;
    }

    public List<RoomDTO> findAvailableRoomsWithHotelId(LocalDateTime dateFrom, LocalDateTime dateTo, String hotelId ){
        List<Reservation> reservations = reservationRepository.findOccupiedRoomIdsInPeriod(dateFrom, dateTo);
        List<String> roomIds = new ArrayList<>();
        for(Reservation reservation : reservations){
            roomIds.add(reservation.getRoomId());
        }
        List<Room> rooms = roomRepository.findAllRoomsAvailableRoomsWithHotelId(hotelId, roomIds);
        List<ba.edu.ibu.webengineeringproject.rest.dto.RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room: rooms) {
            HotelDTO hotelDTO = hotelService.getHotelById(room.getHotelId());
            ba.edu.ibu.webengineeringproject.rest.dto.RoomDTO roomDTO = new ba.edu.ibu.webengineeringproject.rest.dto.RoomDTO(room, hotelDTO);
            roomDTOS.add(roomDTO);
        }
        return roomDTOS;
    }

    public List<RoomDTO> findAvailableRoomsWithCapacityAndHotelId(LocalDateTime dateFrom, LocalDateTime dateTo, int capacity, String hotelId ){
        List<Reservation> reservations = reservationRepository.findOccupiedRoomIdsInPeriod(dateFrom, dateTo);
        List<String> roomIds = new ArrayList<>();
        for(Reservation reservation : reservations){
            roomIds.add(reservation.getRoomId());
        }
        List<Room> rooms = roomRepository.findAllRoomsAvailableRoomsWithHotelIdAndCapacity(hotelId, roomIds, capacity);
        List<ba.edu.ibu.webengineeringproject.rest.dto.RoomDTO> roomDTOS = new ArrayList<>();
        for (Room room: rooms) {
            HotelDTO hotelDTO = hotelService.getHotelById(room.getHotelId());
            ba.edu.ibu.webengineeringproject.rest.dto.RoomDTO roomDTO = new ba.edu.ibu.webengineeringproject.rest.dto.RoomDTO(room, hotelDTO);
            roomDTOS.add(roomDTO);
        }
        return roomDTOS;
    }

    public RoomDTO getRoomById(String id){
        Optional<Room> room = roomRepository.findById(id);
        if(room.isEmpty()){
            throw  new ResourceNotFoundException("The room with the given ID does not exist.");
        }
        HotelDTO hotelDTO = hotelService.getHotelById(room.get().getHotelId());
        return new RoomDTO(room.get(), hotelDTO);
    }

    public RoomDTO addRoom(Room payload){
        Optional<Hotel> hotel = hotelRepository.findById(payload.getHotelId());
        if(hotel.isEmpty()){
            throw new ResourceNotFoundException("The hotel with the given ID does not exist.");
        }
        Room room = roomRepository.save(payload);
        HotelDTO hotelDTO = hotelService.getHotelById(room.getHotelId());
        return  new RoomDTO(room, hotelDTO);
    }

    public RoomDTO updateRoom(String id, Room payload){
        Optional<Room> room = roomRepository.findById(id);
        if(room.isEmpty()){
            throw new ResourceNotFoundException("The room with the given ID does not exist.");
        }
        Optional<Hotel> hotel = hotelRepository.findById(payload.getHotelId());
        if(hotel.isEmpty()){
            throw  new ResourceNotFoundException("The hotel with the given ID does not exist.");
        }
        payload.setId(room.get().getId());
        Room updatedRoom = roomRepository.save(payload);
        HotelDTO hotelDTO = hotelService.getHotelById(updatedRoom.getHotelId());
        return new RoomDTO(updatedRoom, hotelDTO);
    }

    public void deleteRoom(String id){
        Optional<Room> room = roomRepository.findById(id);
        if(room.isEmpty()){
            throw new ResourceNotFoundException("The room with the given ID does not exist.");
        }
        roomRepository.deleteById(id);
    }


}
