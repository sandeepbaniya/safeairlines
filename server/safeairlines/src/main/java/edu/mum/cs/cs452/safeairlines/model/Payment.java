package edu.mum.cs.cs452.safeairlines.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private double payment;
    private LocalDate payDate;

    @ManyToOne
    private Passenger passenger;
}
