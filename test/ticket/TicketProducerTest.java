package ticket;

import chip.USChipAdapter;
import logging.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import train.TravelClass;
import train.WaggonSeat;
import voyager.MPhone;
import voyager.Voyager;

import java.util.Date;

public class TicketProducerTest {

    @BeforeClass
    public static void initLogger() {
        ElectronicTicket.TicketBuilder.resetIdCounter();
        Logger.instance.init();
    }

    @Test
    public void testProduceTicketsNotNull() {
        TicketProducer.produceTickets();

        Assert.assertNotNull(TicketProducer.getListOfPassengers());
    }

    @Test
    public void testProduceTicketsAreTicketsProduced() {
        TicketProducer.produceTickets(true);

        Assert.assertTrue(TicketProducer.areTicketsProduced());
    }

    @Test
    public void testProduceTicketsWithInvalidLength52() {
        TicketProducer.produceTickets(true);

        Assert.assertEquals(52, TicketProducer.getListOfPassengers().size());
    }

    @Test
    public void testProduceTicketsWithoutInvalidLength50() {
        TicketProducer.produceTickets(false);

        Assert.assertEquals(50, TicketProducer.getListOfPassengers().size());
    }

    @Test
    public void testProduceTicketsRegisteredInRepository() {
        TicketProducer.produceTickets(true);
    }

    @Test
    public void testProduceTicketsIsSpecificTicketBuilt() {
        ElectronicTicket.TicketBuilder builder = new ElectronicTicket.TicketBuilder();
        ElectronicTicket testTicket = builder.setVoyager(new Voyager("Donald Trump", "It's all fake news!",
                new MPhone(new USChipAdapter())))
                .setTravelTime(new Date())
                .setTravelCategory(TravelClass.SECOND)
                .setSeat(new WaggonSeat())
                .setSourceLocation(Source.Cologne)
                .setDestinationLocation(Destination.Stuttgart)
                .build();

        TicketProducer.produceTickets(true);

        Assert.assertTrue(TicketRepository.instance.isTicketRegistered(testTicket));
    }
}
