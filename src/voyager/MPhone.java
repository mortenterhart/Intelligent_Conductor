package voyager;

import chip.EUChip;
import chip.IRFIDChip;

public class MPhone {
    private IRFIDChip chip;

    public MPhone() {
        chip = new EUChip();
    }

    public MPhone(IRFIDChip chipVariant) {
        chip = chipVariant;
    }

    public void receiveTicketId(int id) {
        chip.setTicketId(id);
    }

    public void setChip(IRFIDChip chip) {
        this.chip = chip;
    }

    public IRFIDChip getChip() {
        return chip;
    }


    @Override
    public String toString() {
        return "MPhone { chip = " + chip + " }";
    }
}
