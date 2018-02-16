package chip;

/**
 * An adapter for US RFID chips to ensure compatibility between {@link IRFIDChip}
 * and {@link USChip} and to convert messages to US adaptions.
 */
public class USChipAdapter extends USChip implements IRFIDChip {

    /**
     * Calls the super constructor
     */
    public USChipAdapter() {
        super();
    }

    /**
     * Calls the super method.
     * Checks whether the ticket saved on the RFID chip is registered
     * in the ticket repository.
     *
     * @return true if the ticket is registered, false otherwise.
     * @deprecated because the chip should not have access to the ticket repository.
     * Possible security vulnerability.
     */
    @Deprecated
    public boolean hasValidTicket() {
        return super.hasValidTicketUS();
    }

    /**
     * Calls the super method.
     * Reads the ticket id saved on the RFID chip
     *
     * @return the saved ticket id
     */
    public int readTicketId() {
        return super.readTicketIdUS();
    }

    /**
     * Calls the super method.
     * Writes the ticket id <code>id</code> onto the chip
     *
     * @param id the id
     */
    public void writeTicketId(int id) {
        super.setTicketIdUS(id);
    }

    /**
     * Calls the super method.
     * Returns the specific RFID chip variant
     *
     * @return a version string
     */
    public String getVersion() {
        return super.getVersionUS();
    }

    /**
     * Calls the super method.
     *
     * @return a string representation of the {@link USChip} object
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
