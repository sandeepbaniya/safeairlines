package edu.mum.cs.cs452.safeairlines.model;

import java.util.List;

public class User {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Address> adds;
    private Role role;
    private List<Flight> flights;

    private List<FeedBack> feedBacks;


    private List<PaymentRecord> paymentRecords;


}
