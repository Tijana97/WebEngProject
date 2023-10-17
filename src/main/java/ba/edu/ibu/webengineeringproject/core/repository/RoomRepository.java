package ba.edu.ibu.webengineeringproject.core.repository;

import ba.edu.ibu.webengineeringproject.core.model.Room;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class RoomRepository {
    private List<Room> rooms;

    public RoomRepository(){
        this.rooms = Arrays.asList(
                new Room(1, 1, 129.99, 2, "Room 1"),
                new Room(2, 2, 220.00, 2, "Room 2")
        );
    }
    public List<Room> findAll(){
        return rooms;
    }

    public Room findById(int id){
        return rooms.stream().filter(room -> room.getId() == id).findFirst().orElse(null);
    }
}
