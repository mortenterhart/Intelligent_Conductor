package chip;

import ticket.TicketRepository;

public class EUChip implements IRFIDChip {
    private int ticketId;

    public boolean hasValidTicket() {
        return TicketRepository.instance.isTicketIdRegistered(ticketId);
    }

    public int readTicketId() {
        return ticketId;
    }

    public void setTicketId(int id) {
        ticketId = id;
    }

    @Override
    public String toString() {
        return "EUChip { ticketId = " + ticketId + " }";
    }
}
