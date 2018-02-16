package chip;

import ticket.TicketRepository;

public class EUChip implements IRFIDChip {
    private int ticketId;

    public EUChip() {
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
    public boolean hasValidTicket() {
        return TicketRepository.instance.isTicketIdRegistered(ticketId);
    }

    /**
     * Reads the ticket id saved on the RFID chip
     *
     * @return the saved ticket id
     */
    public int readTicketId() {
        return ticketId;
    }

    /**
     * Writes the ticket id <code>id</code> onto the chip
     *
     * @param id the id
     */
    public void writeTicketId(int id) {
        ticketId = id;
    }

    /**
     * Returns the specific RFID chip variant
     *
     * @return a version string
     */
    public String getVersion() {
        return "EUChip 1.1";
    }

    @Override
    public String toString() {
        return "EUChip { ticketId = " + ticketId + " }";
    }
}
