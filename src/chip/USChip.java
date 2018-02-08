package chip;

import ticket.TicketRepository;

public class USChip {
    protected int ticketId;

    public boolean hasValidTicketUS() {
        return TicketRepository.instance.isTicketIdRegistered(ticketId);
    }

    public int readTicketIdUS() {
        return ticketId;
    }

    public void setTicketId(int id) {
        ticketId = id;
    }

    @Override
    public String toString() {
        return "chip.USChip { ticketId = " + ticketId + " }";
    }
}
