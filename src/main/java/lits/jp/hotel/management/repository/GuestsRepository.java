package lits.jp.hotel.management.repository;

import lits.jp.hotel.management.models.Guests;
import org.springframework.data.repository.CrudRepository;

public interface GuestsRepository extends CrudRepository<Guests, Integer> {

    Iterable<Guests> findByFirstName(String firstName);

    Iterable<Guests> findByLastName(String lastName);

    Iterable<Guests> findByEmail (String email);

    Iterable<Guests> findByTelephone (String telephone);

}
