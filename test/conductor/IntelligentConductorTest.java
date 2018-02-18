package conductor;

import logging.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ticket.ElectronicTicket;
import ticket.TicketProducer;
import ticket.TicketRepository;

public class IntelligentConductorTest {
    private IntelligentConductor conductor;

    @Before
    public void produceTicketsAndConductor() {
        TicketRepository.instance.clear();
        ElectronicTicket.TicketBuilder.resetIdCounter();
        Logger.instance.init();
        TicketProducer.produceTickets(true);
        conductor = new IntelligentConductor();
        conductor.init();
    }

    @Test
    public void testConductorInitializationPassengerListNotNull() {
        IntelligentConductor initConductor = new IntelligentConductor();
        initConductor.init();

        Assert.assertNotNull(initConductor.getPassengerList());
    }

    @Test
    public void testConductorInitializationScannerNotNull() {
        IntelligentConductor initConductor = new IntelligentConductor();
        initConductor.init();

        Assert.assertNotNull(initConductor.getScanner());
    }

    @Test
    public void testPassengerListInstanceDifferentFromTicketProducer() {
        Assert.assertFalse(conductor.getPassengerList() == TicketProducer.getListOfPassengers());
    }

    @Test
    public void testPassengerListEquals52() {
        // 50 valid tickets for all seats and 2 invalid tickets
        Assert.assertEquals(52, conductor.getPassengerList().size());
    }

    @Test
    public void testEqualAmountOfPassengersAndTickets() {
        // There's one more passenger than tickets because one passenger has no ticket
        Assert.assertEquals(TicketRepository.instance.size(),
                conductor.getPassengerList().size() - 1);
    }

    @Test
    public void testPassengerListIsShuffled() {
        Assert.assertNotEquals(TicketProducer.getListOfPassengers(), conductor.getPassengerList());
    }

    @Test
    public void testDoYourJobNoException() {
        conductor.doYourJob();
    }

    @Test
    public void testConductorRemovesCounterfeitTicket() {
        int numberOfTicketsWithCounterfeit = TicketRepository.instance.size();
        conductor.doYourJob();

        Assert.assertTrue(numberOfTicketsWithCounterfeit > TicketRepository.instance.size());
    }
}
