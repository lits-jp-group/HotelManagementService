package lits.jp.hotel.management.controller;

import io.swagger.annotations.ApiOperation;
import lits.jp.hotel.management.models.StaffMember;
import lits.jp.hotel.management.repository.StaffMemberRepository;
import lits.jp.hotel.management.services.StaffMemberAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class StaffMemberAuthController {

    private StaffMemberAuthService staffMemberAuthService;

    @Autowired
    public StaffMemberAuthController(StaffMemberAuthService staffMemberAuthService){
        this.staffMemberAuthService = staffMemberAuthService;
    }

    @Autowired
    StaffMemberRepository staffMemberRepository;

    @PostMapping(value = "/login")
    @ApiOperation("auth")
    public ResponseEntity<?> auth(@RequestBody StaffMember staffMember){
        System.out.println(staffMember.toString());
        System.out.println("staffMember.getUsername() from StaffMemberAuthController (the same as in POST command): "+staffMember.getLastName()); // here works. userName = is the same as in POST request
        System.out.println(staffMemberRepository.findOneByLastName(staffMember.getLastName()).toString());
        return ResponseEntity.ok(staffMemberAuthService.auth(staffMember.getLastName(), staffMember.getPassword()));
    }
}
