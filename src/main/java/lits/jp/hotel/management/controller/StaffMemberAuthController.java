package lits.jp.hotel.management.controller;

import io.swagger.annotations.ApiOperation;
import lits.jp.hotel.management.models.StaffMember;
import lits.jp.hotel.management.repository.StaffMemberRepository;
import lits.jp.hotel.management.services.StaffMemberAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
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
        log.info("request from insomnia " + staffMember.getLastName(), staffMember.getPassword());
        return ResponseEntity.ok(staffMemberAuthService.auth(staffMember.getLastName(), staffMember.getPassword()));
    }
}
