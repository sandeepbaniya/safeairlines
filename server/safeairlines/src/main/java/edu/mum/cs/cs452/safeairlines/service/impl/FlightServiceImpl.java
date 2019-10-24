package edu.mum.cs.cs452.safeairlines.service.impl;

import edu.mum.cs.cs452.safeairlines.model.Flight;
import edu.mum.cs.cs452.safeairlines.repository.FlightRepository;
import edu.mum.cs.cs452.safeairlines.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.mum.cs.cs452.safeairlines.model.Flight;
import edu.mum.cs.cs452.safeairlines.repository.FlightRepository;
import edu.mum.cs.cs452.safeairlines.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;

    @Autowired
    public FlightServiceImpl(FlightRepository flightRepository){
        this.flightRepository=flightRepository;
    }

   @Override
   public Flight saveFlight(Flight flight){
       return flightRepository.save(flight);
   }

   @Override
    public List<Flight> getAllFlights(){
        return flightRepository.findAll();
   }

    @Override
    public void deleteFlightById(Long id) {
        flightRepository.deleteById(id);

    }

    @Override
    public Flight getFlightById(Long id) {
        return  flightRepository.findById(id).orElse(null);
    }

    @Override
    public List<Flight> getFlightBaseOnCriteria(String chain) {
        //return flightRepository.findAllByFlightNumberContainingOrPlaneNumberContainingOrDepaturePlaceContainingOrArrivalPlaceContains(chain,chain,chain,chain);
        return flightRepository.findAllByFlightNumberContainingAndPlaneNumberContaining(chain,chain);
    }

    @Override
    public List<Flight> listFlightForBooking(LocalDate depDate, LocalDate returnDate, Long depPlace, Long ArrivPlace) {

        List<Flight> flights = flightRepository.findAll();
        List<Flight> result = new ArrayList<>();
        for(Flight flight : flights){
            if(flight.getDeptDate().equals(depDate)
            && flight.getDepaturePlace().getId()==depPlace&& flight.getArrivalPlace().getId()==ArrivPlace){
                result.add(flight);
            }
         }

        return result;
    }

    @Override
    public boolean compareDate(LocalDate departureDate, LocalDate arrivalDate) {
        System.out.println(departureDate);
        System.out.println(arrivalDate);
        return  departureDate.isBefore(LocalDate.now()) || arrivalDate.isBefore(departureDate);

    }

    @Override
    public boolean checkDepAndArrPlace(Flight flight) {
        return flight.getDepaturePlace().equals(flight.getArrivalPlace());
    }

    @Override
    public boolean generatePDF(List<Flight> flights, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        try {

            Document document=new Document(PageSize.A4, 15,15,15,15);
            String path=context.getRealPath("/resources/reports");
            File file=new File(path);
            boolean exists=new File(path).exists();
            if(!exists) new File(path).mkdirs();
            PdfWriter pdfWriter=PdfWriter.getInstance(document,new FileOutputStream(file+"/"+"flights"+".pdf"));
            document.open();
            Font font= FontFactory.getFont("Arial",10, BaseColor.BLACK);
            Paragraph paragraph=new Paragraph("List of Flights",font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setIndentationLeft(50);
            paragraph.setIndentationRight(50);
            paragraph.setSpacingAfter(10);
            document.add(paragraph);


            PdfPTable table=new PdfPTable(10);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10);

            Font headerFont=FontFactory.getFont("Arial",10, BaseColor.BLACK);
            Font bodyFont=FontFactory.getFont("Arial",9, BaseColor.BLACK);

            float []columnWidth= {2f,2f,3f,2f,2f,3f,2f,2f,2f,2f};
            table.setWidths(columnWidth);

            PdfPCell flightNumber=new PdfPCell(new Paragraph("Number", headerFont));
            flightNumber.setBorderColor(BaseColor.BLACK);
            flightNumber.setPaddingLeft(10);
            flightNumber.setHorizontalAlignment(Element.ALIGN_CENTER);
            flightNumber.setVerticalAlignment(Element.ALIGN_CENTER);
            flightNumber.setBackgroundColor(BaseColor.GRAY);
            flightNumber.setExtraParagraphSpace(5f);
            table.addCell(flightNumber);

            PdfPCell planeNumber=new PdfPCell(new Paragraph("Plane Number", headerFont));
            planeNumber.setBorderColor(BaseColor.BLACK);
            planeNumber.setPaddingLeft(10);
            planeNumber.setHorizontalAlignment(Element.ALIGN_CENTER);
            planeNumber.setVerticalAlignment(Element.ALIGN_CENTER);
            planeNumber.setBackgroundColor(BaseColor.GRAY);
            planeNumber.setExtraParagraphSpace(5f);
            table.addCell(planeNumber);

            PdfPCell depaturePlace=new PdfPCell(new Paragraph("Departure Place", headerFont));
            depaturePlace.setBorderColor(BaseColor.BLACK);
            depaturePlace.setPaddingLeft(10);
            depaturePlace.setHorizontalAlignment(Element.ALIGN_CENTER);
            depaturePlace.setVerticalAlignment(Element.ALIGN_CENTER);
            depaturePlace.setBackgroundColor(BaseColor.GRAY);
            depaturePlace.setExtraParagraphSpace(5f);
            table.addCell(depaturePlace);

            PdfPCell deptDate=new PdfPCell(new Paragraph("Departure Date", headerFont));
            deptDate.setBorderColor(BaseColor.BLACK);
            deptDate.setPaddingLeft(10);
            deptDate.setHorizontalAlignment(Element.ALIGN_CENTER);
            deptDate.setVerticalAlignment(Element.ALIGN_CENTER);
            deptDate.setBackgroundColor(BaseColor.GRAY);
            deptDate.setExtraParagraphSpace(5f);
            table.addCell(deptDate);

            PdfPCell deptTime=new PdfPCell(new Paragraph("Departure Time", headerFont));
            deptTime.setBorderColor(BaseColor.BLACK);
            deptTime.setPaddingLeft(10);
            deptTime.setHorizontalAlignment(Element.ALIGN_CENTER);
            deptTime.setVerticalAlignment(Element.ALIGN_CENTER);
            deptTime.setBackgroundColor(BaseColor.GRAY);
            deptTime.setExtraParagraphSpace(5f);
            table.addCell(deptTime);


            PdfPCell arrivalPlace=new PdfPCell(new Paragraph("Arrival Place", headerFont));
            arrivalPlace.setBorderColor(BaseColor.BLACK);
            arrivalPlace.setPaddingLeft(10);
            arrivalPlace.setHorizontalAlignment(Element.ALIGN_CENTER);
            arrivalPlace.setVerticalAlignment(Element.ALIGN_CENTER);
            arrivalPlace.setBackgroundColor(BaseColor.GRAY);
            arrivalPlace.setExtraParagraphSpace(5f);
            table.addCell(arrivalPlace);


            PdfPCell arrivalDate=new PdfPCell(new Paragraph("Arrival Date", headerFont));
            arrivalDate.setBorderColor(BaseColor.BLACK);
            arrivalDate.setPaddingLeft(10);
            arrivalDate.setHorizontalAlignment(Element.ALIGN_CENTER);
            arrivalDate.setVerticalAlignment(Element.ALIGN_CENTER);
            arrivalDate.setBackgroundColor(BaseColor.GRAY);
            arrivalDate.setExtraParagraphSpace(5f);
            table.addCell(arrivalDate);

            PdfPCell arrivalTime=new PdfPCell(new Paragraph("Arrival Time", headerFont));
            arrivalTime.setBorderColor(BaseColor.BLACK);
            arrivalTime.setPaddingLeft(10);
            arrivalTime.setHorizontalAlignment(Element.ALIGN_CENTER);
            arrivalTime.setVerticalAlignment(Element.ALIGN_CENTER);
            arrivalTime.setBackgroundColor(BaseColor.GRAY);
            arrivalTime.setExtraParagraphSpace(5f);
            table.addCell(arrivalTime);

            PdfPCell numbSeat=new PdfPCell(new Paragraph("Seat", headerFont));
            numbSeat.setBorderColor(BaseColor.BLACK);
            numbSeat.setPaddingLeft(10);
            numbSeat.setHorizontalAlignment(Element.ALIGN_CENTER);
            numbSeat.setVerticalAlignment(Element.ALIGN_CENTER);
            numbSeat.setBackgroundColor(BaseColor.GRAY);
            numbSeat.setExtraParagraphSpace(5f);
            table.addCell(numbSeat);

            PdfPCell price=new PdfPCell(new Paragraph("Price", headerFont));
            price.setBorderColor(BaseColor.BLACK);
            price.setPaddingLeft(10);
            price.setHorizontalAlignment(Element.ALIGN_CENTER);
            price.setVerticalAlignment(Element.ALIGN_CENTER);
            price.setBackgroundColor(BaseColor.GRAY);
            price.setExtraParagraphSpace(5f);
            table.addCell(price);
            System.out.println("2**********************");

            for(Flight fl:flights) {
                System.out.println("3**********************");
                PdfPCell flightNumberValue=new PdfPCell(new Paragraph(fl.getFlightNumber(), bodyFont));
                flightNumberValue.setBorderColor(BaseColor.BLACK);
                flightNumberValue.setPaddingLeft(10);
                flightNumberValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                flightNumberValue.setVerticalAlignment(Element.ALIGN_CENTER);
                flightNumberValue.setExtraParagraphSpace(5f);
                table.addCell(flightNumberValue);

                PdfPCell planeNumberValue=new PdfPCell(new Paragraph(fl.getPlaneNumber(), bodyFont));
                planeNumberValue.setBorderColor(BaseColor.BLACK);
                planeNumberValue.setPaddingLeft(10);
                planeNumberValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                planeNumberValue.setVerticalAlignment(Element.ALIGN_CENTER);
                planeNumberValue.setExtraParagraphSpace(5f);
                table.addCell(planeNumberValue);

                PdfPCell depaturePlaceValue=new PdfPCell(new Paragraph(fl.getDepaturePlace().getName(), bodyFont));
                depaturePlaceValue.setBorderColor(BaseColor.BLACK);
                depaturePlaceValue.setPaddingLeft(10);
                depaturePlaceValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                depaturePlaceValue.setVerticalAlignment(Element.ALIGN_CENTER);
                depaturePlaceValue.setExtraParagraphSpace(5f);
                table.addCell(depaturePlaceValue);

                PdfPCell depatureDateValue=new PdfPCell(new Paragraph(""+fl.getDeptDate(), bodyFont));
                depatureDateValue.setBorderColor(BaseColor.BLACK);
                depatureDateValue.setPaddingLeft(10);
                depatureDateValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                depatureDateValue.setVerticalAlignment(Element.ALIGN_CENTER);
                depatureDateValue.setExtraParagraphSpace(5f);
                table.addCell(depatureDateValue);

                PdfPCell depatureTimeValue=new PdfPCell(new Paragraph(""+fl.getDeptTime(), bodyFont));
                depatureTimeValue.setBorderColor(BaseColor.BLACK);
                depatureTimeValue.setPaddingLeft(10);
                depatureTimeValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                depatureTimeValue.setVerticalAlignment(Element.ALIGN_CENTER);
                depatureTimeValue.setExtraParagraphSpace(5f);
                table.addCell(depatureTimeValue);

                PdfPCell arrivalPlaceValue=new PdfPCell(new Paragraph(fl.getArrivalPlace().getName(), bodyFont));
                arrivalPlaceValue.setBorderColor(BaseColor.BLACK);
                arrivalPlaceValue.setPaddingLeft(10);
                arrivalPlaceValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                arrivalPlaceValue.setVerticalAlignment(Element.ALIGN_CENTER);
                arrivalPlaceValue.setExtraParagraphSpace(5f);
                table.addCell(arrivalPlaceValue);

                PdfPCell arrivalDateValue=new PdfPCell(new Paragraph(""+fl.getArrivalDate(), bodyFont));
                arrivalDateValue.setBorderColor(BaseColor.BLACK);
                arrivalDateValue.setPaddingLeft(10);
                arrivalDateValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                arrivalDateValue.setVerticalAlignment(Element.ALIGN_CENTER);
                arrivalDateValue.setExtraParagraphSpace(5f);
                table.addCell(arrivalDateValue);

                PdfPCell arrivalTimeValue=new PdfPCell(new Paragraph(""+fl.getArrivalTime(), bodyFont));
                arrivalTimeValue.setBorderColor(BaseColor.BLACK);
                arrivalTimeValue.setPaddingLeft(10);
                arrivalTimeValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                arrivalTimeValue.setVerticalAlignment(Element.ALIGN_CENTER);
                arrivalTimeValue.setExtraParagraphSpace(5f);
                table.addCell(arrivalTimeValue);

                PdfPCell seatValue=new PdfPCell(new Paragraph(""+fl.getNumbSeat(), bodyFont));
                seatValue.setBorderColor(BaseColor.BLACK);
                seatValue.setPaddingLeft(10);
                seatValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                seatValue.setVerticalAlignment(Element.ALIGN_CENTER);
                seatValue.setExtraParagraphSpace(5f);
                table.addCell(seatValue);

                PdfPCell priceValue=new PdfPCell(new Paragraph(""+fl.getPrice(), bodyFont));
                priceValue.setBorderColor(BaseColor.BLACK);
                priceValue.setPaddingLeft(10);
                priceValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                priceValue.setVerticalAlignment(Element.ALIGN_CENTER);
                priceValue.setExtraParagraphSpace(5f);
                table.addCell(priceValue);


            }
            document.add(table);
            document.close();
            pdfWriter.close();
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
