package chip;

import ticket.TicketRepository;

public class USChip {
    private int ticketId;
    private String version = "USChip 1.4";

    /**
     * Protected access because an instance of USChip should only be created
     * by using the USChipAdapter
     */
    protected USChip() {
        ticketId = -1;
    }

    /**
     * Checks whether the ticket saved on the RFID chip is registered
     * in the ticket repository.
     *
     * @return true if the ticket is registered, false otherwise.
     * @deprecated because the chip should not have access to the ticket repository.
     * Possible security vulnerability.
     */
    @Deprecated
    protected boolean hasValidTicketUS() {
        return TicketRepository.instance.isTicketRegistered(ticketId);
    }

    protected int readTicketIdUS() {
        return ticketId;
    }

    protected void writeTicketIdUS(int id) {
        ticketId = id;
    }

    protected String getVersionUS() {
        return version;
    }

    @Override
    public String toString() {
        return "USChip { ticketId = " + ticketId + " }";
    }
}
