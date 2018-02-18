package conductor;

import chip.EUChip;
import chip.IRFIDChip;
import chip.USChipAdapter;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ticket.Destination;
import ticket.ElectronicTicket;
import ticket.Source;
import ticket.TicketRepository;
import train.SeatLocation;
import train.TravelClass;
import train.WaggonSeat;
import voyager.MPhone;
import voyager.Voyager;

import java.util.Date;

public class ScanningDeviceTest {
    private static ScanningDevice scanner;
    private static IRFIDChip euChip;
    private static IRFIDChip usChip;

    @BeforeClass
    public static void buildInstancesAndRegisterTickets() {
        TicketRepository.instance.clear();
        ElectronicTicket.TicketBuilder.resetIdCounter();
        scanner = new ScanningDevice();
        ElectronicTicket.TicketBuilder builder = new ElectronicTicket.TicketBuilder();

        WaggonSeat leftSeat = new WaggonSeat(SeatLocation.left);
        euChip = new EUChip();
        builder.setVoyager(new Voyager("EU citizen", "Thumbs up", new MPhone(euChip)))
                .setTravelTime(new Date())
                .setTravelCategory(TravelClass.FIRST)
                .setSeat(leftSeat)
                .setSourceLocation(Source.Cologne)
                .setDestinationLocation(Destination.Heidelberg)
                .build();

        WaggonSeat rightSeat = new WaggonSeat(SeatLocation.right);
        usChip = new USChipAdapter();
        builder.setVoyager(new Voyager("US citizen", "Thumbs down", new MPhone(usChip)))
                .setTravelTime(new Date())
                .setTravelCategory(TravelClass.SECOND)
                .setSeat(rightSeat)
                .setSourceLocation(Source.Hamburg)
                .setDestinationLocation(Destination.Munich)
                .build();

    }

    @Test
    public void testValidateEUChip() {
        Assert.assertTrue(scanner.validateTicket(euChip));
    }

    @Test
    public void testValidateUSChip() {
        Assert.assertTrue(scanner.validateTicket(usChip));
    }

    @Test
    public void testReadEUChipId() {
        Assert.assertEquals(euChip.readTicketId(), scanner.readChipId(euChip));
    }

    @Test
    public void testReadUSChipId() {
        Assert.assertEquals(usChip.readTicketId(), scanner.readChipId(usChip));
    }

    @Test
    public void testGetEUTicket() {
        Assert.assertEquals(TicketRepository.instance.getTicket(0), scanner.getTicket(euChip));
    }

    @Test
    public void testGetUSTicket() {
        Assert.assertEquals(TicketRepository.instance.getTicket(1), scanner.getTicket(usChip));
    }
}
