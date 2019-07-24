package lits.jp.hotel.management.controller;

import lits.jp.hotel.management.models.StaffMember;
import lits.jp.hotel.management.services.impl.StaffMemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
public class StaffMemberController {

  @Autowired StaffMemberServiceImpl staffMemberServiceImpl;

  @GetMapping
  public List<StaffMember> findAllStaff() {
    return staffMemberServiceImpl.findAll();
  }

  @GetMapping(value = "{id}")
  public StaffMember findOneById(@RequestParam(value = "id") Long id) {
    return staffMemberServiceImpl.findOne(id);
  }

  @PostMapping(value = "save")
  public StaffMember saveStaffMember(@RequestBody StaffMember staffMember) {
    return staffMemberServiceImpl.save(staffMember);
  }

  @DeleteMapping(value = "{id}")
  public ResponseEntity<Long> deleteStaff(@RequestParam(value = "id") Long id) {
    staffMemberServiceImpl.delete(id);
    return new ResponseEntity<>(id, HttpStatus.OK);
  }
}
