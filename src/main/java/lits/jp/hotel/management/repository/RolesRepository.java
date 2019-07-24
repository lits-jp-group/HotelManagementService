package lits.jp.hotel.management.repository;

import lits.jp.hotel.management.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends CrudRepository<Role, Long> {
}
