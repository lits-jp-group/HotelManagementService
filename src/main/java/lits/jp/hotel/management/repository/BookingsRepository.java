package lits.jp.hotel.management.repository;

import lits.jp.hotel.management.models.Bookings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingsRepository extends CrudRepository<Bookings, Integer> {

    Bookings findByBookingId(Integer id);

    @Query(value = "SELECT * from hotel.bookings as b where ?1 between b.date_in and adddate(b.date_out, 1)", nativeQuery = true)
    List<Bookings> showAllBookingsOnDate(LocalDate date);
}

