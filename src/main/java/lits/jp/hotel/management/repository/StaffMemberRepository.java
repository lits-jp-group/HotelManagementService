package lits.jp.hotel.management.repository;

import lits.jp.hotel.management.models.StaffMember;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffMemberRepository extends CrudRepository<StaffMember, Long> {

  StaffMember findOneByLastName(String lastName);

  StaffMember findOneByStaffMemberId(Long id);
}
