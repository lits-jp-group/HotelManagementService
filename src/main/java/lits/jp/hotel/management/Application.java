package lits.jp.hotel.management;

import lits.jp.hotel.management.models.Guests;
import lits.jp.hotel.management.models.Rooms;
import lits.jp.hotel.management.repository.GuestsRepository;
import lits.jp.hotel.management.repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private GuestsRepository guestsRepository;

	@Autowired
	private RoomsRepository roomsRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Guests initialGuest1 = new Guests();
		initialGuest1.setFirstName("Jim");
		initialGuest1.setLastName("Beam");
		guestsRepository.save(initialGuest1);

		Guests initialGuest2 = new Guests();
		initialGuest2.setFirstName("Dead");
		initialGuest2.setLastName("Pool");
		guestsRepository.save(initialGuest2);

		Rooms roomLux = new Rooms();
		roomLux.setType("Lux");
		roomLux.setNumber(13);
		roomsRepository.save(roomLux);

		Rooms roomStandard = new Rooms();
		roomStandard.setType("Standard");
		roomStandard.setNumber(15);
		roomsRepository.save(roomStandard);

	}
}
