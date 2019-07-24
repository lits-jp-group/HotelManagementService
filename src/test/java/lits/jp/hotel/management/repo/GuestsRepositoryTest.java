package lits.jp.hotel.management.repo;

import static org.assertj.core.api.Java6Assertions.assertThat;

import lits.jp.hotel.management.models.Guests;
import lits.jp.hotel.management.repository.GuestsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class GuestsRepositoryTest {

  @Autowired private TestEntityManager entityManager;

  @Autowired private GuestsRepository guestsRepository;

  @Test
  public void whenFindByName_thenReturnGuests() {
    // given
    Guests guestToBeFound = new Guests();
    guestToBeFound.setFirstName("Jim");
    guestToBeFound.setLastName("Beam");

    entityManager.persist(guestToBeFound);
    entityManager.flush();

    // when
    Iterable<Guests> iterableGuestsByName =
        guestsRepository.findByFirstName(guestToBeFound.getFirstName());
    Guests guestFound = new Guests();
    for (Guests iterable : iterableGuestsByName) {
      guestFound = iterable;
    }

    // then
    assertThat(guestFound.getFirstName()).isEqualTo(guestToBeFound.getFirstName());
  }
}
