package train;

import chip.IRFIDChip;
import ticket.TicketRepository;

public class ScanningDevice {

    public boolean validateTicket(IRFIDChip chip) {
        System.out.println("validateTicket: chipId is " + chip.readTicketId());
        return TicketRepository.instance.isTicketIdRegistered(chip.readTicketId());
    }
}
