package ticket;

import chip.EUChip;
import chip.IRFIDChip;
import chip.USChipAdapter;
import logging.Logger;
import main.Configuration;
import train.Train;
import train.TravelClass;
import train.WaggonSeat;
import voyager.MPhone;
import voyager.Voyager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is meant to use the {@link ElectronicTicket.TicketBuilder}
 * class to produce a list of tickets depending on how
 * many seats are available in the train waggon. The
 * tickets are filled with example data as depicted in
 * the methods below. In addition the created voyagers
 * are collected in a list of passengers that can be
 * accessed by a getter method.
 * <p>
 * All methods inside this class are static to provide
 * direct access when invoking. Therefore the constructor
 * is private so that no instances of this class can be
 * created.
 */
public class TicketProducer {
    /**
     * The Concrete {@link ElectronicTicket.TicketBuilder} instance
     */
    private static ElectronicTicket.TicketBuilder builder = new ElectronicTicket.TicketBuilder();

    /**
     * The instance of the {@link Train}
     */
    private static Train train = Train.getInstance();

    /**
     * The collection of voyagers (passenger list) that is created
     * during ticket production.
     */
    private static List<Voyager> voyagerList = new ArrayList<>();

    /**
     * A boolean value denoting whether the tickets were already produced
     * or not.
     */
    private static boolean ticketsProduced = false;

    /**
     * Do not allow instantiation.
     */
    private TicketProducer() {
    }

    /**
     * Produces tickets that will be registered to the ticket repository
     * and creates instances with example data different for the left or
     * the right side of the waggon. A ticket is produced for each seat
     * the waggon offers.
     * <p>
     * By default, invalid tickets are also produced to test the conductor.
     */
    public static void produceTickets() {
        produceTickets(true);
    }

    /**
     * Produces tickets that will be registered to the ticket repository
     * and creates instances with example data different for the left or
     * the right side of the waggon. A ticket is produced for each seat
     * the waggon offers.
     *
     * @param withInvalidTickets a switch causing invalid tickets to be
     *                           produced or not
     */
    public static void produceTickets(boolean withInvalidTickets) {
        voyagerList.clear();
        Logger.instance.log("--- Started producing tickets for all available seats " +
                "(TicketProducer.produceTickets())");

        for (WaggonSeat leftSeat : train.getWaggon().getLeftSeats()) {
            Voyager leftVoyager = new Voyager("Dr. Left");
            leftVoyager.setFingerPrint("Thumbs up");
            leftVoyager.setPhone(new MPhone(new EUChip()));
            voyagerList.add(leftVoyager);

            buildTicket(leftVoyager, TravelClass.FIRST, leftSeat,
                    randomEnumConstant(Source.class), randomEnumConstant(Destination.class));
            Logger.instance.log("> Producing ticket for " + leftSeat.getLocation().name() +
                    " seat with id " + leftSeat.getSeatId());
        }

        for (WaggonSeat rightSeat : train.getWaggon().getRightSeats()) {
            Voyager rightVoyager = new Voyager("Dr. Right");
            rightVoyager.setFingerPrint("Thumbs down");
            rightVoyager.setPhone(new MPhone(new USChipAdapter()));
            voyagerList.add(rightVoyager);

            buildTicket(rightVoyager, TravelClass.SECOND, rightSeat,
                    randomEnumConstant(Source.class), randomEnumConstant(Destination.class));
            Logger.instance.log("> Producing ticket for " + rightSeat.getLocation().name() +
                    " seat with id " + rightSeat.getSeatId());
        }
        Logger.instance.newLine();

        if (withInvalidTickets) {
            produceInvalidTickets();
        }

        ticketsProduced = true;
    }

    /**
     * Inserts two voyagers whose ticket information are incorrect.
     * The intelligent conductor should recognize the invalidity of
     * their tickets and remove the stowaways from the train (i.e.
     * throwing them out).
     */
    private static void produceInvalidTickets() {
        Logger.instance.log("--- Producing corrupt tickets to be identified by the conductor " +
                "(TicketProducer.produceInvalidTickets())");

        // 1. Voyager without a ticket (condition: no ticket registered in TicketRepository)
        IRFIDChip euChip = new EUChip();
        euChip.writeTicketId(TicketRepository.instance.numberOfTickets + 2);
        Voyager fareDodger = new Voyager("Daniel Vagabond", "Hopefully nobody notices!",
                new MPhone(euChip));
        voyagerList.add(fareDodger);

        Logger.instance.log("> Producing voyager without building ticket (fare dodger)");
        Logger.instance.log("   -> Voyager '" + fareDodger.getName() + "' should be found by " +
                "the intelligent conductor");

        // 2. Voyager with a counterfeit ticket (condition: ticket id on RFIDChip not matching with any
        // of the ids in the repository)
        IRFIDChip usAdapter = new USChipAdapter();
        Voyager counterfeitTicket = new Voyager("Donald Trump", "It's all fake news!",
                new MPhone(usAdapter));
        buildTicket(counterfeitTicket, TravelClass.SECOND, new WaggonSeat(),
                Source.Cologne, Destination.Stuttgart);
        // Overwrite chip id set in the ticket builder with an invalid ticket id so that
        // the intelligent conductor notices the counterfeit ticket.
        usAdapter.writeTicketId(TicketRepository.instance.numberOfTickets + 3);
        voyagerList.add(counterfeitTicket);

        Logger.instance.log("> Producing voyager with disparate ticket id on RFIDChip that is not " +
                "registered in the ticket repository");
        Logger.instance.log("   -> Voyager '" + counterfeitTicket.getName() + "' should be found by " +
                "the intelligent conductor");
    }

    /**
     * Builds an instance of a ticket by using the internal
     * {@link ElectronicTicket.TicketBuilder}.
     *
     * @param voyager      the voyager attached to the ticket
     * @param category     the travel class attached to the ticket
     * @param seat         the seat attached to the ticket
     * @param srcLocation  the source location attached to the ticket
     * @param destLocation the destination location attached to the ticket
     */
    private static void buildTicket(Voyager voyager, TravelClass category, WaggonSeat seat,
                                    Source srcLocation, Destination destLocation) {
        builder.setVoyager(voyager)
                .setTravelTime(new Date())
                .setTravelCategory(category)
                .setSeat(seat)
                .setSourceLocation(srcLocation)
                .setDestinationLocation(destLocation)
                .build();
    }

    /**
     * Selects randomly an enum constant out of the given enum <code>enumType</code>
     * and returns it. Uses the random generator {@link random.MersenneTwisterFast}.
     *
     * @param enumType the class object holding the enumeration type
     * @param <E>      the type qualifier ensuring the class is a subclass of {@link Enum}&lt;E&gt;
     * @return the enumeration constant of type E
     */
    private static <E extends Enum<E>> E randomEnumConstant(Class<E> enumType) {
        int x = Configuration.instance.mersenneTwister.nextInt(enumType.getEnumConstants().length);
        return enumType.getEnumConstants()[x];
    }

    /**
     * Checks if the tickets were already produced once.
     *
     * @return true if the tickets were already produced, false otherwise.
     */
    public static boolean areTicketsProduced() {
        return ticketsProduced;
    }

    /**
     * Returns the list of passengers that is built during ticket production.
     *
     * @return the list of passengers
     */
    public static List<Voyager> getListOfPassengers() {
        return voyagerList;
    }
}
