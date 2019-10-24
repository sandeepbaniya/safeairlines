package edu.mum.cs.cs452.safeairlines.model;

import lombok.Data;
import lombok.NoArgsConstructor;
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
@Data
@NoArgsConstructor
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
    @OneToOne
    @NotNull
    private Airport depaturePlace;
    @OneToOne
    @NotNull
    private Airport arrivalPlace;
    private Integer numbSeat;
    private Double price;

    //private List<FeedBack> feedBacks = new ArrayList<>();
    @OneToMany(mappedBy = "flight",cascade = CascadeType.ALL)
    private List<BookingRecord> bookingRecords = new ArrayList<>();

    public void setBokingRecord (BookingRecord bookingRecord){
        bookingRecord.setFlight(this);
        this.bookingRecords.add(bookingRecord);
    }


}
