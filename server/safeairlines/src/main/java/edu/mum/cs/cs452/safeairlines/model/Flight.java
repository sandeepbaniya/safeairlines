package edu.mum.cs.cs452.safeairlines.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Flight_Number")
    @NotBlank
    private String flightNumber;
    @NotBlank
    private String planeNumber;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deptDate;
    //@DateTimeFormat(pattern = )
    private LocalTime deptTime;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate arrivalDate;
    private LocalDateTime arrivalTime;
    @NotBlank
    private String depaturePlace;
    @NotBlank
    private String arrivalPlace;
    private Integer numbSeat;
    private Double price;
    // I dont want to create record yet in the database for this field
   // @Transient
    //private List<FeedBack> feedBacks = new ArrayList<>();
    @Transient
    private List<BookingRecord> bookingRecords = new ArrayList<>();


    public Flight() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getPlaneNumber() {
        return planeNumber;
    }

    public void setPlaneNumber(String planeNumber) {
        this.planeNumber = planeNumber;
    }

    public LocalDate getDeptDate() {
        return deptDate;
    }

    public void setDeptDate(LocalDate deptDate) {
        this.deptDate = deptDate;
    }

    public LocalTime getDeptTime() {
        return deptTime;
    }

    public void setDeptTime(LocalTime deptTime) {
        this.deptTime = deptTime;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getDepaturePlace() {
        return depaturePlace;
    }

    public void setDepaturePlace(String depaturePlace) {
        this.depaturePlace = depaturePlace;
    }

    public String getArrivalPlace() {
        return arrivalPlace;
    }

    public void setArrivalPlace(String arrivalPlace) {
        this.arrivalPlace = arrivalPlace;
    }

    public Integer getNumbSeat() {
        return numbSeat;
    }

    public void setNumbSeat(Integer numbSeat) {
        this.numbSeat = numbSeat;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public List<BookingRecord> getBookingRecords() {
        return bookingRecords;
    }

    public void setBookingRecords(List<BookingRecord> bookingRecords) {
        this.bookingRecords = bookingRecords;
    }
}
