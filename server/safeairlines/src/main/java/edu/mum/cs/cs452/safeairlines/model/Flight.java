package edu.mum.cs.cs452.safeairlines.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Flight {

    private Integer id;
    private LocalDate deptDate;
    private LocalDateTime deptTime;
    private LocalDate arrivalDate;
    private LocalDateTime arrivalTime;

    private String origin;
    private String destination;

}
