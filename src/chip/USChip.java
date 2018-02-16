package chip;

import ticket.TicketRepository;

public class USChip {
    protected int ticketId;

    /**
     * Protected access because an instance of USChip should only be created
     * by using the USChipAdapter
     */
    protected USChip() {
        ticketId = -1;
    }

    @Deprecated
    protected boolean hasValidTicketUS() {
        return TicketRepository.instance.isTicketIdRegistered(ticketId);
    }

    protected int readTicketIdUS() {
        return ticketId;
    }

    protected void setTicketIdUS(int id) {
        ticketId = id;
    }

    protected String getVersionUS() {
        return "USChip 1.4";
    }

    @Override
    public String toString() {
        return "USChip { ticketId = " + ticketId + " }";
    }
}
