package train;

import chip.IRFIDChip;
import chip.USChipAdapter;
import ticket.ElectronicTicket;
import ticket.TicketRepository;

import java.util.ArrayList;
import java.util.List;

public class IntelligentConductor {
    private List<IRFIDChip> phoneChips;

    public IntelligentConductor() {
        phoneChips = new ArrayList<>();
    }

    public void init() {
        for (ElectronicTicket ticket : TicketRepository.instance.repository.values()) {
            phoneChips.add(ticket.getVoyager().getPhone().getChip());
        }

        for (int i = 0; i < 100; i++) {
            IRFIDChip chip = new USChipAdapter();
            chip.setTicketId(i);
            phoneChips.add(chip);
        }
    }

    public void checkTickets() {
        System.out.println("--- train.IntelligentConductor.checkTickets()");
        for (IRFIDChip chip : phoneChips) {
            if (chip.hasValidTicket()) {
                System.out.println("Chip " + chip.toString() + " has a valid ticket!");
            } else {
                System.out.println("Chip " + chip.toString() + " is not valid");
            }
        }
    }

    public void printTickets() {
        System.out.println("-- train.IntelligentConductor.printTickets()");
        System.out.println("Available tickets in repository!");
        for (ElectronicTicket ticket : TicketRepository.instance.repository.values()) {
            System.out.println(ticket);
        }
    }
}
