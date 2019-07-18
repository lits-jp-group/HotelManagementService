package lits.jp.hotel.management.repo;

import lits.jp.hotel.management.models.Bookings;
import lits.jp.hotel.management.models.Guests;
import lits.jp.hotel.management.models.StaffMember;
import lits.jp.hotel.management.repository.BookingsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class BookingsRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookingsRepository bookingsRepository;

    @Test
    public void whenFindByName_thenReturnBookings() {
        // given
        Bookings bookingToBeFound = new Bookings();

        Guests testGuests = new Guests();
        testGuests.setFirstName("Jim");
        testGuests.setLastName("Beam");
        bookingToBeFound.setGuest(testGuests);

        StaffMember testStaffmember = new StaffMember();
        testStaffmember.setFirstName("Firstname");
        testStaffmember.setLastName("Lastname");
        testStaffmember.setPassword("password");
        bookingToBeFound.setStaffHandling(testStaffmember);

        bookingToBeFound.setDateIn(new Date(5));
        bookingToBeFound.setDateOut(new Date(6));
        bookingToBeFound.setBookingId(99899999);

        entityManager.merge(bookingToBeFound);
        entityManager.flush();

        // when
        List<Bookings> bookingFound = bookingsRepository.getBookingsByDateInEquals(bookingToBeFound.getDateIn());

        // then
        assertThat(bookingFound.get(0).getGuest().getFirstName())
                .isEqualTo(bookingToBeFound.getGuest().getFirstName());
//
//        // when // in this case I get NullPointerException. Why?
//        Bookings bookingFound = bookingsRepository.findByBookingId(bookingToBeFound.getBookingId());
//
//        // then
//        assertThat(bookingFound.getGuest().getFirstName())
//                .isEqualTo(bookingToBeFound.getGuest().getFirstName());


    }
}
