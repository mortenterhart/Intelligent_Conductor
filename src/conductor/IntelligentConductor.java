package conductor;

import chip.IRFIDChip;
import logging.Logger;
import main.Configuration;
import ticket.ElectronicTicket;
import ticket.TicketProducer;
import ticket.TicketRepository;
import voyager.Voyager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The IntelligentConductor is one of the main classes in
 * this project and contains the most execution logic. The
 * Application caller doesn't need to care about the different
 * execution steps because the method <code>doYourJob</code>
 * contains the exact order of private execution methods.
 */
public class IntelligentConductor {
    /**
     * scanner used to validate ticket ids stored on RFID chips and
     * to return tickets matching a chip id
     */
    private ScanningDevice scanner;

    /**
     * The list of voyagers the conductor iterates over.
     */
    private List<Voyager> passengerList;

    public IntelligentConductor() {
        scanner = new ScanningDevice();
        passengerList = new ArrayList<>();
    }

    /**
     * This method initializes the list of passengers only if the tickets are
     * already produced. If so, the list is fetched from the TicketProducer
     * and is shuffled to remove the order and to not be able to say that an
     * invalid ticket is on a specific location.
     */
    public void init() {
        Logger.instance.log("--- Fetching list of voyagers to be checked by the conductor " +
                "(IntelligentConductor.init())");
        if (TicketProducer.areTicketsProduced()) {
            passengerList = TicketProducer.getListOfPassengers();
            Logger.instance.log("> Tickets were produced, conductor received list of passengers.");
            Logger.instance.log("> Shuffling the list of passengers randomly to hide invalid tickets!");

            // Shuffle the list to hide invalid tickets (i.e. they should appear to any time) and to
            // prevent them to be at a specific location.
            Collections.shuffle(passengerList, Configuration.instance.mersenneTwister);
        }
    }

    /**
     * Main execution method: Executes all steps the conductor does in the
     * correct oder.
     */
    public void doYourJob() {
        // Output the list of all passengers first
        printPassengerList();
        Logger.instance.newLine();

        // Check all tickets of those passengers
        checkTickets();
        Logger.instance.newLine();

        // Print the remaining tickets after invalid tickets have been removed
        printRemainingTickets();
        Logger.instance.newLine();
    }

    /**
     * Step 1: Print all boarded passengers and their fingerprints
     */
    private void printPassengerList() {
        Logger.instance.log("--- Printing all passengers (IntelligentConductor.printPassengerList())");
        for (Voyager passenger : passengerList) {
            Logger.instance.log("> Passenger '" + passenger.getName() + "' (Fingerprint \"" +
                    passenger.getFingerPrint() + "\") is on board.");
        }
    }

    /**
     * Step 2: Go through the train and check each voyager on ticket availability. If ticket is absent or
     * counterfeit, throw that person out of the train.
     */
    private void checkTickets() {
        Logger.instance.log("--- Checking tickets of passengers (IntelligentConductor.checkTickets())");
        for (Voyager passenger : passengerList) {

            Logger.instance.log("> Asking passenger '" + passenger.getName() + "' for his/her phone");
            if (passenger.hasPhone() && passenger.hasTicketBought()) {

                IRFIDChip phoneChip = passenger.getPhone().getChip();
                Logger.instance.log("> Scanning RFID Chip of type '" +
                        phoneChip.getVersion() + "' with scanning device");

                if (scanner.validateTicket(phoneChip)) {

                    Logger.instance.log("  > Passenger '" + passenger.getName() + "' (Fingerprint \"" +
                            passenger.getFingerPrint() + "\") is registered to a valid ticket.");
                    Logger.instance.log("  Chip ID: " + scanner.readChipId(phoneChip));
                    Logger.instance.log("  Ticket information: " + scanner.getTicket(phoneChip).toString());

                } else {

                    Logger.instance.log("  > Invalid ticket recognized: Passenger '" + passenger.getName() +
                            "' (Fingerprint \"" + passenger.getFingerPrint() + "\") counterfeited his/her ticket!");
                    Logger.instance.log("  Chip ID " + scanner.readChipId(phoneChip) + " not registered as ticket!");
                    Logger.instance.log("  ** Throwing passenger out and destroying invalid ticket! **");
                    TicketRepository.instance.removeTicket(passenger);

                }
            } else {
                Logger.instance.log("  > There is a vagabond aboard: Fare Dodger " + passenger.getName() +
                        " (Fingerprint \"" + passenger.getFingerPrint() + "\") has no ticket!");
                Logger.instance.log("  Chip ID: " + scanner.readChipId(passenger.getPhone().getChip()));
                Logger.instance.log("  ** Throwing passenger out! **");
            }
            Logger.instance.newLine();
        }
    }

    /**
     * Step 3: Output all tickets of remaining passengers and associated information
     */
    private void printRemainingTickets() {
        Logger.instance.log("--- Printing remaining tickets (IntelligentConductor.printRemainingTickets())");
        System.out.println("Registered tickets in ticket repository:");

        for (ElectronicTicket ticket : TicketRepository.instance.tickets()) {
            Logger.instance.log(String.format("> Ticket %2d:", ticket.getTicketId()));
            Logger.instance.log("    voyager:     " + ticket.getVoyager().getName());
            Logger.instance.log("    travelTime:  " + ticket.getTravelTime());
            Logger.instance.log("    travelClass: " + ticket.getTravelCategory().name() + " class");
            Logger.instance.log("    seat:        " + ticket.getSeat().getSeatId() + " on the " +
                    ticket.getSeat().getLocation().name() + " side of the waggon");
            Logger.instance.log("    source:      " + ticket.getSourceLocation().name());
            Logger.instance.log("    destination: " + ticket.getDestinationLocation().name());
            Logger.instance.newLine();
        }
    }
}
