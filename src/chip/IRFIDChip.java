package chip;

public interface IRFIDChip {

    /**
     * Checks whether the ticket saved on the RFID chip is registered
     * in the ticket repository.
     * @return
     */
    public boolean hasValidTicket();

    /**
     *
     * @return
     */
    public int readTicketId();

    public void setTicketId(int id);

}
