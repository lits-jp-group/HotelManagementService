package lits.jp.hotel.management.repository;

import lits.jp.hotel.management.models.Guests;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestsRepository extends CrudRepository<Guests, Integer> {

    Iterable<Guests> findByFirstName(String firstName); // possible to use list or set

    Iterable<Guests> findByLastName(String lastName);

    Iterable<Guests> findByEmail (String email);

    Iterable<Guests> findByTelephone (String telephone);

}
