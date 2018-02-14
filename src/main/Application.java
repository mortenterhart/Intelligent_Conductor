package main;

import logging.Logger;
import ticket.ElectronicTicket;
import ticket.TicketProducer;
import train.IntelligentConductor;
import train.Train;

public class Application {
    private Train train;
    private IntelligentConductor conductor;
    private ElectronicTicket.TicketBuilder builder = new ElectronicTicket.TicketBuilder();

    public Application() {
        constructTrain();
        constructConductor();
    }

    private void constructTrain() {
        train = Train.getInstance();
    }

    private void constructConductor() {
        conductor = new IntelligentConductor();
    }

    private void execute() {
        Logger.instance.init();
        TicketProducer.produceTickets();
        conductor.init();
        conductor.checkTickets();
        conductor.printTickets();
    }

    public static void main(String[] args) {
        Application application = new Application();
        application.execute();
    }
}
