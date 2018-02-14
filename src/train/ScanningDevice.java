package train;

import chip.IRFIDChip;

public class ScanningDevice {

    public boolean scan(IRFIDChip chip) {
        return chip.hasValidTicket();
    }
}
