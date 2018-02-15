package chip;

public class USChipAdapter extends USChip implements IRFIDChip {

    public USChipAdapter() {
        super();
    }

    public boolean hasValidTicket() {
        return super.hasValidTicketUS();
    }

    public int readTicketId() {
        return super.readTicketIdUS();
    }

    public void setTicketId(int id) {
        super.setTicketIdUS(id);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
