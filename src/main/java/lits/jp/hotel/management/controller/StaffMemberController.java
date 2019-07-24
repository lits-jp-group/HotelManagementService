package lits.jp.hotel.management.controller;

import lits.jp.hotel.management.models.StaffMember;
import lits.jp.hotel.management.services.impl.StaffMemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class StaffMemberController {

    @Autowired
    StaffMemberServiceImpl staffMemberServiceImpl;

    @PostMapping(value = "save")
    public StaffMember saveStaffMember(@RequestBody StaffMember staffMember) {
        return staffMemberServiceImpl.saveNewStaffMember(staffMember);
    }
}