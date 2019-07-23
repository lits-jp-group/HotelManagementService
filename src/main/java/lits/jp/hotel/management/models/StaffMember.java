package lits.jp.hotel.management.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "staff")
public class StaffMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long staffMemberId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role")
    private String role; //hardcoded, check on functions

    @OneToMany(mappedBy = "staffHandling")
    private List<Bookings> bookingsHandled;

    @Override
    public String toString() {
        return "StaffMember{" +
                "staffMemberId=" + staffMemberId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", bookingsHandled=" + bookingsHandled +
                '}';
    }

    public Long getStaffMemberId() {
        return staffMemberId;
    }

    public void setStaffMemberId(Long staffMemberId) {
        this.staffMemberId = staffMemberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
