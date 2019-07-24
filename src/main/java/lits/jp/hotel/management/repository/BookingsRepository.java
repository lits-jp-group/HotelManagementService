package lits.jp.hotel.management.repository;

import java.util.Date;
import java.util.List;
import lits.jp.hotel.management.models.Bookings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingsRepository extends CrudRepository<Bookings, Integer> {

  Bookings findByBookingId(Integer id); // removed Iterable<Bookings> for Bookings

  List<Bookings> getBookingsByDateInEquals(Date dateIn);

  @Query(
      value =
          "SELECT * from hotel.bookings as b where ?1 between b.date_in and adddate(b.date_out, 1)",
      nativeQuery = true) // redo into jpql request
  List<Bookings> showAllBookingsOnDate(Date start);
}
