package ba.edu.ibu.webengineeringproject.core.service;

import ba.edu.ibu.webengineeringproject.core.exceptions.repository.ResourceNotFoundException;
import ba.edu.ibu.webengineeringproject.core.model.Hotel;
import ba.edu.ibu.webengineeringproject.core.model.Room;
import ba.edu.ibu.webengineeringproject.core.repository.HotelRepository;
import ba.edu.ibu.webengineeringproject.core.repository.RoomRepository;
import ba.edu.ibu.webengineeringproject.rest.dto.HotelDTO;
import ba.edu.ibu.webengineeringproject.rest.dto.RoomDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final HotelService hotelService;

    public RoomService(RoomRepository roomRepository, HotelRepository hotelRepository, HotelService hotelService){
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
        this.hotelService = hotelService;
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
