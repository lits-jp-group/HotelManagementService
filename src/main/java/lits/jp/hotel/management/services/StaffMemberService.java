package lits.jp.hotel.management.services;

import lits.jp.hotel.management.models.StaffMember;

import java.util.List;

public interface StaffMemberService {

    StaffMember findOne(Long id);

    List<StaffMember> findAll();

    StaffMember save(StaffMember staffMember);

    void delete(Long id);
}
