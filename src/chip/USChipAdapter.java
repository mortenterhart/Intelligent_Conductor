package chip;

/**
 * An adapter for US RFID chips to ensure compatibility between {@link IRFIDChip}
 * and {@link USChip} and to convert messages to US adaptions. This class extends
 * the {@link USChip} and implements the Adapter Design Pattern by delegating the
 * correct requests sent from {@link IRFIDChip} to {@link USChip}. There the adapter
 * converts the request into US standards and calls the super methods/constructors.
 */
public class USChipAdapter extends USChip implements IRFIDChip {

    /**
     * Constructs a new USChipAdapter to convert to {@link USChip}.
     * Calls the super constructor.
     *
     * @see USChip#USChip()
     */
    public USChipAdapter() {
        super();
    }

    /**
     * Checks whether the ticket saved on the RFID chip is registered
     * in the ticket repository.
     * Calls the super method.
     *
     * @return true if the ticket is registered, false otherwise.
     * @see USChip#hasValidTicketUS()
     * @deprecated because the chip should not have access to the ticket repository.
     * Possible security vulnerability.
     */
    @Deprecated
    public boolean hasValidTicket() {
        return super.hasValidTicketUS();
    }

    /**
     * Reads the ticket id saved on the RFID chip.
     * Calls the super method.
     *
     * @return the saved ticket id
     * @see USChip#readTicketIdUS()
     */
    public int readTicketId() {
        return super.readTicketIdUS();
    }

    /**
     * Writes the ticket id <code>id</code> onto the chip.
     * Calls the super method.
     *
     * @param id the id
     * @see USChip#writeTicketIdUS(int)
     */
    public void writeTicketId(int id) {
        super.writeTicketIdUS(id);
    }

    /**
     * Returns the specific RFID chip variant.
     * Calls the super method.
     *
     * @return a version string
     * @see USChip#getVersionUS()
     */
    public String getVersion() {
        return super.getVersionUS();
    }

    /**
     * Returns a string representation describing this {@link USChip}
     *
     * @return a string representation of the {@link USChip} object
     * @see USChip#toString()
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
