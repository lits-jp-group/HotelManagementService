package lits.jp.hotel.management.repo;

import lits.jp.hotel.management.models.Rooms;
import lits.jp.hotel.management.repository.RoomsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class RoomsRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RoomsRepository roomsRepository;

    @Test
    public void whenFindByName_thenReturnRooms() {
        // given
       Rooms roomToBeFound = new Rooms();
       roomToBeFound.setNumber(13);
       roomToBeFound.setType("Lux");

        entityManager.persist(roomToBeFound);
        entityManager.flush();

        // when
       Rooms roomFound = roomsRepository.findOneByType(roomToBeFound.getType());

        // then
        assertThat(roomFound.getNumber())
                .isEqualTo(roomToBeFound.getNumber());
    }
}
