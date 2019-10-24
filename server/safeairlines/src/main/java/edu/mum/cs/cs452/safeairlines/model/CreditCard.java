package edu.mum.cs.cs452.safeairlines.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private  String credit;
    private double balance;
    private String cvv;
    private LocalDate payDate;

    @ManyToOne
    private User user;


}
