package chip;

import ticket.TicketRepository;

public class EUChip implements IRFIDChip {
    private int ticketId;

    @Override
    public boolean hasValidTicket() {
        return TicketRepository.instance.isTicketIdRegistered(ticketId);
    }

    @Override
    public int readTicketId() {
        return ticketId;
    }

    public void setTicketId(int id) {
        ticketId = id;
    }

    @Override
    public String toString() {
        return "chip.EUChip { ticketId = " + ticketId + " }";
    }
}
