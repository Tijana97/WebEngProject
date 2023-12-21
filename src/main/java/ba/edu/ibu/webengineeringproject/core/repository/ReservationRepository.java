package ba.edu.ibu.webengineeringproject.core.repository;

import ba.edu.ibu.webengineeringproject.core.model.Reservation;
import ba.edu.ibu.webengineeringproject.core.model.Room;
import io.swagger.v3.core.util.Json;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {

    @Query(value = "{$and:[{ 'dateFrom': {$lt: ?1}}, { 'dateTo': {$gt: ?0}}]}")
    List<Reservation> findOccupiedRoomIdsInPeriod(LocalDateTime fromDate, LocalDateTime toDate);

    List<Reservation> findAllByUserId(String userId);

    @Query(value = "{$and:[{ 'dateTo': {$lt: ?1}}, {'userId' : '?0'}]}")
    List<Reservation> findPastReservationsByUser(String userId, LocalDateTime dateTo);

    @Query(value = "{$and:[{ 'dateFrom': {$gt: ?1}}, {'userId' : '?0'}]}")
    List<Reservation> findFutureReservationsByUser(String userId, LocalDateTime dateTo);

   List<Reservation> findAllByRoomId(String roomId);


}
