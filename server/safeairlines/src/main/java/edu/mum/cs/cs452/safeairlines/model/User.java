package edu.mum.cs.cs452.safeairlines.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private String phoneNumber;


    private Integer passportNumber;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "id")})
    private List<Role> roles = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<CreditCard> creditCards;

    @OneToMany
    @JoinTable(name = "feedback")
    private List<Feedback> feedBacks;

    @OneToMany
    @JoinColumn (name = "IdUser")
    private List<BookingRecord> bookingRecords = new ArrayList<>();

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public void  addBookingRecord(BookingRecord bookingRecord){
        this.bookingRecords.add(bookingRecord);
    }

}
