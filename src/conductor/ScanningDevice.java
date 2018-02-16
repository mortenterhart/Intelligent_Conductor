package conductor;

import chip.IRFIDChip;
import ticket.ElectronicTicket;
import ticket.TicketRepository;

/**
 * The ScanningDevice is an item possessed by the conductor
 * with which he can read the RFID chips from the voyager's
 * phones. The device can return the ticket id stored on the
 * chip and can check the ticket's validity by connecting to
 * the {@link TicketRepository}. Based on the ticket id it is
 * also possible to get the whole ticket instance.
 */
public class ScanningDevice {

    /**
     * Checks if the ticket stored on the chip is registered in the
     * ticket repository and is valid.
     *
     * @param chip the chip to check
     * @return true if the ticket id has an entry in the ticket repository,
     * false otherwise.
     */
    public boolean validateTicket(IRFIDChip chip) {
        return TicketRepository.instance.isTicketIdRegistered(chip.readTicketId());
    }

    /**
     * Reads the ticket id from the designated chip.
     *
     * @param chip the chip
     * @return the ticket id stored on the chip
     */
    public int readChipId(IRFIDChip chip) {
        return chip.readTicketId();
    }

    /**
     * Returns the {@link ElectronicTicket} matching the ticket id on the chip.
     * If there is no ticket with the id on the chip, null is returned.
     *
     * @param chip the chip
     * @return the ticket if existent, null otherwise.
     */
    public ElectronicTicket getTicket(IRFIDChip chip) {
        return TicketRepository.instance.getTicket(chip.readTicketId());
    }
}
