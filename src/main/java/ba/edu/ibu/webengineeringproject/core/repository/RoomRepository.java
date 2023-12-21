package ba.edu.ibu.webengineeringproject.core.repository;

import ba.edu.ibu.webengineeringproject.core.model.Reservation;
import ba.edu.ibu.webengineeringproject.core.model.Room;
import ba.edu.ibu.webengineeringproject.rest.dto.RoomDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RoomRepository extends MongoRepository<Room, String> {
    List<Room> findAllByCapacity(int capacity);

    List<Room> findAllByHotelId(String hotelId);

    @Query(value = "{'id': {$nin: ?0}}")
    List<Room> findAllAvailableRooms(List<String> reservationRoomIds);

    @Query(value = "{'capacity': ?1, 'id': {$nin: ?0}}")
    List<Room> findAllAvailableRoomsWithCapacity(List<String> reservationRoomIds, int capacity);


    @Query(value = "{'hotelId': ?0, 'id': {$nin: ?1}}")
    List<Room> findAllRoomsAvailableRoomsWithHotelId(String hotelId, List<String> reservationRoomIds);

    @Query(value = "{'capacity': ?2, 'hotelId': ?0, 'id': {$nin: ?1}}")
    List<Room> findAllRoomsAvailableRoomsWithHotelIdAndCapacity(String hotelId, List<String> reservationRoomIds, int capacity);


}
