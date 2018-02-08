package voyager;

import chip.EUChip;
import chip.IRFIDChip;

public class MPhone {
    private IRFIDChip chip;
    private int ticketId = 0;

    public MPhone() {
        ticketId = 0;
        chip = new EUChip();
    }

    public MPhone(IRFIDChip chipVariant) {
        chip = chipVariant;
    }

    public void receiveTicketId(int id) {
        ticketId = id;
        chip.setTicketId(id);
    }

    public void setChip(IRFIDChip chip) {
        this.chip = chip;
    }

    public IRFIDChip getChip() {
        return chip;
    }

    public int getTicketId() {
        return ticketId;
    }

    @Override
    public String toString() {
        return "voyager.MPhone { chip = " + chip + ", ticketId = " + ticketId + " }";
    }
}
