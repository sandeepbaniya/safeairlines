package edu.mum.cs.cs452.safeairlines.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
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
    @JoinTable
    private List<Role> roles;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Payment> payments;

    @OneToMany
    @JoinTable(name = "feedback")
    private List<Feedback> feedBacks;



}
