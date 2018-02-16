package chip;

/**
 * An interface declaring standard methods for RFID Chips
 */
public interface IRFIDChip {

    /**
     * Checks whether the ticket saved on the RFID chip is registered
     * in the ticket repository.
     *
     * @return true if the ticket is registered, false otherwise.
     * @deprecated because the chip should not have access to the ticket repository.
     * Possible security vulnerability.
     */
    @Deprecated
    public boolean hasValidTicket();

    /**
     * Reads the ticket id saved on the RFID chip
     *
     * @return the saved ticket id
     */
    public int readTicketId();

    /**
     * Writes the ticket id <code>id</code> onto the chip
     *
     * @param id the id
     */
    public void writeTicketId(int id);

    /**
     * Returns the specific RFID chip variant
     *
     * @return a version string
     */
    public String getVersion();

}
