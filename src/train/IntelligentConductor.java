package train;

import chip.IRFIDChip;
import ticket.ElectronicTicket;
import ticket.TicketProducer;
import ticket.TicketRepository;
import voyager.Voyager;

import java.util.ArrayList;
import java.util.List;

public class IntelligentConductor {
    private ScanningDevice scanner;
    private List<Voyager> passengerList;

    public IntelligentConductor() {
        scanner = new ScanningDevice();
        passengerList = new ArrayList<>();
    }

    public void init() {
        if (TicketProducer.areTicketsProduced()) {
            passengerList = TicketProducer.getListOfPassengers();
        }
    }

    public void checkTickets() {
        System.out.println("--- IntelligentConductor.checkTickets()");
        for (Voyager passenger : passengerList) {
            if (passenger.hasPhone() && passenger.hasTicketBought()) {
                if (scanner.validateTicket(passenger.getPhone().getChip())) {
                    System.out.println("Passenger " + passenger.getName() + " has a valid ticket!");
                } else {
                    System.out.println("Passenger " + passenger.getName() + " has a counterfeit ticket!");
                    System.out.println("**Throwing passenger out!**");
                }
            } else {
                System.out.println(passenger.getName() + " is a fare dodger **Throwing passenger out!**");
            }
        }
    }

    public void printTickets() {
        System.out.println("-- IntelligentConductor.printTickets()");
        System.out.println("Available tickets in repository:");
        for (ElectronicTicket ticket : TicketRepository.instance.repository.values()) {
            System.out.println(ticket);
        }
    }
}
