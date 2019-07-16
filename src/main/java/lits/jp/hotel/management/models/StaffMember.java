package lits.jp.hotel.management.models;

import lombok.Data;


import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "staff")
public class StaffMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int staffMemberId;

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
}
