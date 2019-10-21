package edu.mum.cs.cs452.safeairlines.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "username")
    @NotBlank
    @Size(min=5,max = 15, message = "{Size.username}")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="email")
    @NotBlank
    @Email
    private String email;

    @Column(name = "active")
    private int active;

    @Column(name = "password")
    @NotBlank
    @Size(min=5,  message = "{Size.password}")
    private String password;

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
//    private List<Address> adds;
//
    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Role role;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Flight> flights;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
//    private List<FeedBack> feedBacks;
//
//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<PaymentRecord> paymentRecords;


}
