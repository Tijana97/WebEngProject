package ba.edu.ibu.webengineeringproject.core.service;

import ba.edu.ibu.webengineeringproject.core.model.Room;
import ba.edu.ibu.webengineeringproject.core.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    public List<Room> findAll(){
        return roomRepository.findAll();
    }

    public Room findById(int id){
        return  roomRepository.findById(id);
    }
}
