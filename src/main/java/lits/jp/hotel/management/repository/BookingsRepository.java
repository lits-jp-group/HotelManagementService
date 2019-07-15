package lits.jp.hotel.management.repository;

import lits.jp.hotel.management.models.Bookings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface BookingsRepository extends CrudRepository<Bookings, Integer> {

    Iterable<Bookings> findByBookingId(Integer id);

    List<Bookings> getBookingsByDateInEquals(Date dateIn);

    @Query(value = "SELECT * from hotel.bookings as b where ?1 between b.date_in and adddate(b.date_out, 1)", nativeQuery = true)
    List<Bookings> showAllBookingsOnDate(Date start);


}

