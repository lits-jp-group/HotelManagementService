package lits.jp.hotel.management.repository;

import lits.jp.hotel.management.models.Rooms;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoomsRepository extends CrudRepository<Rooms, Integer> {

    void deleteByNumber(int number);

}
