package voyager;

import chip.EUChip;
import chip.IRFIDChip;

/**
 * The MPhone is a class representing a mobile phone
 * with a builtin {@link IRFIDChip}. It implements the
 * Bridge Pattern for the <code>chip</code> attribute.
 * Each {@link Voyager} has a MPhone as attribute.
 * <p>
 * When a ticket is built, the ticket id is sent to the
 * voyager's MPhone automatically. Then the method
 * <code>receiveTicketId()</code> is called to write the
 * ticket id into the RFIDChip.
 */
public class MPhone {
    /**
     * The builtin {@link IRFIDChip} implementing the Bridge Design Pattern
     * where the ticket id is stored. This chip reference is used by the scanning
     * device to read the chip id. There are two variants of the IRFIDChip:
     * * USChip with a special USChipAdapter
     * * EUChip
     */
    private IRFIDChip chip;

    /**
     * Default constructor: The MPhone is created with an {@link EUChip} by default, if
     * called without parameter.
     */
    public MPhone() {
        chip = new EUChip();
    }

    public MPhone(IRFIDChip chipVariant) {
        chip = chipVariant;
    }

    /**
     * Writes the ticket id <code>id</code> onto the {@link IRFIDChip}
     *
     * @param id the ticket id to store in the chip
     */
    public void receiveTicketId(int id) {
        chip.writeTicketId(id);
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
