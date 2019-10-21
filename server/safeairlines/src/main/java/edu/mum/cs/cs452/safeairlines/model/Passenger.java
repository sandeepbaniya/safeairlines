package edu.mum.cs.cs452.safeairlines.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Passenger  extends User implements Serializable{



    private String phoneNumber;


    private Integer passportNumber;


    @OneToMany(cascade =  CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "passenger")
    private List<Payment> payments;

    @OneToMany
    @JoinTable(name = "feedback")
    private List<Feedback> feedBacks;


    public Passenger(String firstName, String lastName, String email,String password, String phoneNumber, String passportNumber, List<Role> roles){
        super(firstName,lastName,email,password,roles);
        this.phoneNumber = phoneNumber;
    }
}
