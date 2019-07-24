package lits.jp.hotel.management;

import lits.jp.hotel.management.models.Guests;
import lits.jp.hotel.management.models.Rooms;
import lits.jp.hotel.management.models.StaffMember;
import lits.jp.hotel.management.repository.GuestsRepository;
import lits.jp.hotel.management.repository.RoomsRepository;
import lits.jp.hotel.management.repository.StaffMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@SpringBootApplication
public class Application implements ApplicationRunner {

  @Autowired private GuestsRepository guestsRepository;

  @Autowired private RoomsRepository roomsRepository;

  @Autowired private StaffMemberRepository staffMemberRepository;

  @Autowired PasswordEncoder passwordEncoder;

  @Bean
  PasswordEncoder getEncoder() {
    return new BCryptPasswordEncoder();
  } // when I inserted this bean - the test runs ok!

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @Override
  public void run(ApplicationArguments logger) throws Exception {

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

    StaffMember staffMember = new StaffMember();
    staffMember.setFirstName("adminfn");
    staffMember.setLastName("admin");
    staffMember.setPassword(passwordEncoder.encode("123"));
    staffMember.setRole("Admin");
    staffMemberRepository.save(staffMember);
  }
}
