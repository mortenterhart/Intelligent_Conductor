package chip;

public class USChipAdapter extends USChip implements IRFIDChip {

    @Override
    public boolean hasValidTicket() {
        return super.hasValidTicketUS();
    }

    @Override
    public int readTicketId() {
        return super.readTicketIdUS();
    }

    @Override
    public void setTicketId(int id) {
        super.setTicketId(id);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
