package main;

import logging.Logger;
import voyager.MPhone;
import ticket.Destination;
import ticket.ElectronicTicket;
import ticket.Source;
import ticket.TicketRepository;
import train.IntelligentConductor;
import train.Train;
import train.TravelClass;
import train.WaggonSeat;
import voyager.Voyager;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Application {
    private Train train;
    private IntelligentConductor conductor;
    private List<WaggonSeat> seats;
    private int ticketIdCounter = 0;

    public Application() {
        constructTrain();
        constructConductor();
    }

    public void produceTickets() {
        ElectronicTicket.TicketBuilder builder = new ElectronicTicket.TicketBuilder();
        while (ticketIdCounter < seats.size()) {
            Voyager voyager = new Voyager("Any Name");
            voyager.setPhone(new MPhone());
            voyager.setFingerprint("fingerprint");

            builder.setTicketId(ticketIdCounter)
                    .setVoyager(voyager)
                    .setTravelTime(new Date())
                    .setTravelCategory(TravelClass.FIRST)
                    .setSeat(seats.get(ticketIdCounter))
                    .setSourceLocation(Source.Cologne)
                    .setDestinationLocation(Destination.Heidelberg)
                    .build();

            ticketIdCounter++;
        }
        Logger.instance.write(TicketRepository.instance.repository.values().toString());
    }

    public void constructTrain() {
        train = new Train();
        seats = Stream.of(train.getWaggon().getLeftSeats(), train.getWaggon().getRightSeats()).
                flatMap(Collection::stream).collect(Collectors.toList());
    }

    public void constructConductor() {
        conductor = new IntelligentConductor();
    }

    public void execute() {
        Logger.instance.init();
        produceTickets();
        conductor.init();
        conductor.checkTickets();
        conductor.printTickets();
    }

    public static void main(String[] args) {
        Application application = new Application();
        application.execute();
    }
}
