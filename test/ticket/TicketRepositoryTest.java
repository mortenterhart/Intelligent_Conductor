package ticket;

import chip.USChipAdapter;
import logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import train.TravelClass;
import train.WaggonSeat;
import voyager.MPhone;
import voyager.Voyager;

import java.util.Date;

public class TicketRepositoryTest {

    @Test
    public void testRepositoryInstanceNotNull() {
        Assert.assertNotNull(TicketRepository.instance);
    }

    @Test
    public void testRepositoryMapNotNull() {
        Assert.assertNotNull(TicketRepository.instance.tickets());
    }

    @Test
    public void testNotRegisterTicketAfterBuild() {
        ElectronicTicket.TicketBuilder builder = new ElectronicTicket.TicketBuilder();
        ElectronicTicket ticket = builder.setVoyager(new Voyager("John Doe", "FingerPrint",
                new MPhone(new USChipAdapter())))
                .setTravelTime(new Date())
                .setTravelCategory(TravelClass.SECOND)
                .setSeat(new WaggonSeat())
                .setSourceLocation(Source.Berlin)
                .setDestinationLocation(Destination.Stuttgart)
                .build();

        // build() method already registers ticket into repository so another call
        // with the same ticket returns false
        Assert.assertFalse(TicketRepository.instance.registerTicket(ticket));
        TicketRepository.instance.clear();
    }

    @Test
    public void testTicketIdRegistered() {
        ElectronicTicket.TicketBuilder builder = new ElectronicTicket.TicketBuilder();
        ElectronicTicket ticket = builder.setVoyager(new Voyager("John Doe", "FingerPrint",
                new MPhone(new USChipAdapter())))
                .setTravelTime(new Date())
                .setTravelCategory(TravelClass.SECOND)
                .setSeat(new WaggonSeat())
                .setSourceLocation(Source.Berlin)
                .setDestinationLocation(Destination.Stuttgart)
                .build();

        Assert.assertTrue(TicketRepository.instance.isTicketRegistered(ticket.getTicketId()));
        TicketRepository.instance.clear();
    }

    @Test
    public void testRemoveTicketById() {
        ElectronicTicket.TicketBuilder builder = new ElectronicTicket.TicketBuilder();
        ElectronicTicket ticket = builder.setVoyager(new Voyager("John Doe", "FingerPrint",
                new MPhone(new USChipAdapter())))
                .setTravelTime(new Date())
                .setTravelCategory(TravelClass.SECOND)
                .setSeat(new WaggonSeat())
                .setSourceLocation(Source.Berlin)
                .setDestinationLocation(Destination.Stuttgart)
                .build();

        Assert.assertTrue(TicketRepository.instance.removeTicket(ticket.getTicketId()));
        TicketRepository.instance.clear();
    }

    @Test
    public void testRemoveTicketByIdNotContainedInRepositoryAfterwards() {
        ElectronicTicket.TicketBuilder builder = new ElectronicTicket.TicketBuilder();
        ElectronicTicket ticket = builder.setVoyager(new Voyager("John Doe", "FingerPrint",
                new MPhone(new USChipAdapter())))
                .setTravelTime(new Date())
                .setTravelCategory(TravelClass.SECOND)
                .setSeat(new WaggonSeat())
                .setSourceLocation(Source.Berlin)
                .setDestinationLocation(Destination.Stuttgart)
                .build();

        TicketRepository.instance.removeTicket(ticket.getTicketId());
        Assert.assertFalse(TicketRepository.instance.contains(ticket));
        TicketRepository.instance.clear();
    }

    @Test
    public void testRemoveTicketByVoyager() {
        ElectronicTicket.TicketBuilder builder = new ElectronicTicket.TicketBuilder();
        Voyager insertedVoyager = new Voyager("John Doe", "FingerPrint",
                new MPhone(new USChipAdapter()));
        builder.setVoyager(insertedVoyager)
                .setTravelTime(new Date())
                .setTravelCategory(TravelClass.SECOND)
                .setSeat(new WaggonSeat())
                .setSourceLocation(Source.Berlin)
                .setDestinationLocation(Destination.Stuttgart)
                .build();

        Assert.assertTrue(TicketRepository.instance.removeTicket(insertedVoyager));
        TicketRepository.instance.clear();
    }

    @Test
    public void testRemoveTicketByVoyagerNotContainedInRepositoryAfterwards() {
        ElectronicTicket.TicketBuilder builder = new ElectronicTicket.TicketBuilder();
        Voyager insertedVoyager = new Voyager("John Doe", "FingerPrint",
                new MPhone(new USChipAdapter()));
        ElectronicTicket ticket = builder.setVoyager(insertedVoyager)
                .setTravelTime(new Date())
                .setTravelCategory(TravelClass.SECOND)
                .setSeat(new WaggonSeat())
                .setSourceLocation(Source.Berlin)
                .setDestinationLocation(Destination.Stuttgart)
                .build();

        TicketRepository.instance.removeTicket(insertedVoyager);
        Assert.assertFalse(TicketRepository.instance.contains(ticket));
        TicketRepository.instance.clear();
    }

    @Test
    public void testVoyagerHasTicketBought() {
        ElectronicTicket.TicketBuilder builder = new ElectronicTicket.TicketBuilder();
        Voyager insertedVoyager = new Voyager("John Doe", "FingerPrint",
                new MPhone(new USChipAdapter()));
        builder.setVoyager(insertedVoyager)
                .setTravelTime(new Date())
                .setTravelCategory(TravelClass.SECOND)
                .setSeat(new WaggonSeat())
                .setSourceLocation(Source.Berlin)
                .setDestinationLocation(Destination.Stuttgart)
                .build();

        Assert.assertTrue(TicketRepository.instance.hasTicketBought(insertedVoyager));
        TicketRepository.instance.clear();
    }

    @Test
    public void testRepositorySizeAfterTicketProduction() {
        Logger.instance.init();
        TicketProducer.produceTickets(true);

        Assert.assertEquals(51, TicketRepository.instance.size());
        TicketRepository.instance.clear();
    }
}
