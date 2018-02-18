package ticket;

import chip.USChipAdapter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import train.SeatLocation;
import train.TravelClass;
import train.WaggonSeat;
import voyager.MPhone;
import voyager.Voyager;

import java.util.Date;

public class TicketBuilderTest {
    private ElectronicTicket.TicketBuilder builder = new ElectronicTicket.TicketBuilder();
    private Voyager sampleVoyager;
    private WaggonSeat seat;

    @Before
    public void buildAttributes() {
        ElectronicTicket.TicketBuilder.resetIdCounter();
        sampleVoyager = new Voyager("John Doe", "FingerPrint", new MPhone(new USChipAdapter()));
        seat = new WaggonSeat(SeatLocation.right);
    }

    @Test
    public void testBuildResultNotNull() {
        ElectronicTicket ticket = builder.setVoyager(sampleVoyager)
                .setTravelTime(new Date())
                .setTravelCategory(TravelClass.SECOND)
                .setSeat(seat)
                .setSourceLocation(Source.Berlin)
                .setDestinationLocation(Destination.Dresden)
                .build();

        Assert.assertNotNull(ticket);
    }

    @Test
    public void testVoyagerNotNullAfterBuild() {
        ElectronicTicket ticket = builder.setVoyager(sampleVoyager)
                .setTravelTime(new Date())
                .setTravelCategory(TravelClass.SECOND)
                .setSeat(seat)
                .setSourceLocation(Source.Berlin)
                .setDestinationLocation(Destination.Dresden)
                .build();

        Assert.assertNotNull(ticket.getVoyager());
    }

    @Test
    public void testTravelTimeNotNullAfterBuild() {
        ElectronicTicket ticket = builder.setVoyager(sampleVoyager)
                .setTravelTime(new Date())
                .setTravelCategory(TravelClass.SECOND)
                .setSeat(seat)
                .setSourceLocation(Source.Berlin)
                .setDestinationLocation(Destination.Dresden)
                .build();

        Assert.assertNotNull(ticket.getTravelTime());
    }

    @Test
    public void testTravelCategoryNotNullAfterBuild() {
        ElectronicTicket ticket = builder.setVoyager(sampleVoyager)
                .setTravelTime(new Date())
                .setTravelCategory(TravelClass.SECOND)
                .setSeat(seat)
                .setSourceLocation(Source.Berlin)
                .setDestinationLocation(Destination.Dresden)
                .build();

        Assert.assertNotNull(ticket.getTravelCategory());
    }

    @Test
    public void testSeatNotNullAfterBuild() {
        ElectronicTicket ticket = builder.setVoyager(sampleVoyager)
                .setTravelTime(new Date())
                .setTravelCategory(TravelClass.SECOND)
                .setSeat(seat)
                .setSourceLocation(Source.Berlin)
                .setDestinationLocation(Destination.Dresden)
                .build();

        Assert.assertNotNull(ticket.getSeat());
    }

    @Test
    public void testSourceLocationNotNullAfterBuild() {
        ElectronicTicket ticket = builder.setVoyager(sampleVoyager)
                .setTravelTime(new Date())
                .setTravelCategory(TravelClass.SECOND)
                .setSeat(seat)
                .setSourceLocation(Source.Berlin)
                .setDestinationLocation(Destination.Dresden)
                .build();

        Assert.assertNotNull(ticket.getSourceLocation());
    }

    @Test
    public void testDestinationLocationNotNullAfterBuild() {
        ElectronicTicket ticket = builder.setVoyager(sampleVoyager)
                .setTravelTime(new Date())
                .setTravelCategory(TravelClass.SECOND)
                .setSeat(seat)
                .setSourceLocation(Source.Berlin)
                .setDestinationLocation(Destination.Dresden)
                .build();

        Assert.assertNotNull(ticket.getDestinationLocation());
    }

    @Test
    public void testSetVoyagerReturnsBuilder() {
        Assert.assertTrue(builder.setVoyager(sampleVoyager) != null);
    }

    @Test
    public void testSetTravelTimeReturnsBuilder() {
        Assert.assertTrue(builder.setTravelTime(new Date()) != null);
    }

    @Test
    public void testSetTravelCategoryReturnsBuilder() {
        Assert.assertTrue(builder.setTravelCategory(TravelClass.SECOND) != null);
    }

    @Test
    public void testSetSeatReturnsBuilder() {
        Assert.assertTrue(builder.setSeat(seat) != null);
    }

    @Test
    public void testSetSourceLocationReturnsBuilder() {
        Assert.assertTrue(builder.setSourceLocation(Source.Berlin) != null);
    }

    @Test
    public void testSetDestinationLocationReturnsBuilder() {
        Assert.assertTrue(builder.setDestinationLocation(Destination.Dresden) != null);
    }

    @Test
    public void testBuilderRegistersTicketInRepository() {
        ElectronicTicket ticket = builder.setVoyager(sampleVoyager)
                .setTravelTime(new Date())
                .setTravelCategory(TravelClass.SECOND)
                .setSeat(seat)
                .setSourceLocation(Source.Berlin)
                .setDestinationLocation(Destination.Dresden)
                .build();

        Assert.assertTrue(TicketRepository.instance.isTicketRegistered(ticket));
    }

    @Test
    public void testBuilderReservesSeat() {
        ElectronicTicket ticket = builder.setVoyager(sampleVoyager)
                .setTravelTime(new Date())
                .setTravelCategory(TravelClass.SECOND)
                .setSeat(seat)
                .setSourceLocation(Source.Berlin)
                .setDestinationLocation(Destination.Dresden)
                .build();

        Assert.assertEquals(ticket.getTicketId(), ticket.getSeat().getSeatId());
    }

    @Test
    public void testBuilderSendsTicketToPhone() {
        ElectronicTicket ticket = builder.setVoyager(sampleVoyager)
                .setTravelTime(new Date())
                .setTravelCategory(TravelClass.SECOND)
                .setSeat(seat)
                .setSourceLocation(Source.Berlin)
                .setDestinationLocation(Destination.Dresden)
                .build();

        Assert.assertEquals(ticket.getTicketId(), ticket.getVoyager().getPhone().getChip().readTicketId());
    }

    @Test
    public void testBuilderIncrementsIdCounter() {
        ElectronicTicket ticket1 = builder.setVoyager(sampleVoyager)
                .setTravelTime(new Date())
                .setTravelCategory(TravelClass.SECOND)
                .setSeat(seat)
                .setSourceLocation(Source.Berlin)
                .setDestinationLocation(Destination.Dresden)
                .build();

        ElectronicTicket ticket2 = builder.setVoyager(sampleVoyager)
                .setTravelTime(new Date())
                .setTravelCategory(TravelClass.SECOND)
                .setSeat(seat)
                .setSourceLocation(Source.Berlin)
                .setDestinationLocation(Destination.Dresden)
                .build();

        Assert.assertEquals(ticket1.getTicketId() + 1, ticket2.getTicketId());
    }

}
