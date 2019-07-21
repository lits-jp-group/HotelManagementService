package lits.jp.hotel.management.repository;

import lits.jp.hotel.management.models.Rooms;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomsRepository extends CrudRepository<Rooms, Integer> {

    void deleteByNumber(int number);

    Rooms findByRoomId(int id);

    Rooms findOneByType (String type); // p.s. added this for repo test.

    @Query(value ="SELECT * FROM hotel.rooms" , nativeQuery = true) // replace by jpql request - MAYBE here is better to use findAll?

    List<Rooms> showAllRooms();
}
