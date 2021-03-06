package ticket;

import train.TravelClass;
import train.WaggonSeat;
import voyager.Voyager;

import java.util.Date;

/**
 * The ElectronicTicket represents an immutable model class which holds all
 * information associated to a train ticket. Those are:
 * <ul>
 * <li>ticketId: a unique ticket id</li>
 * <li>voyager: an instance of a Voyager used to access his phone to send
 * the ticketId to it</li>
 * <li>travelTime: the date of voyage</li>
 * <li>travelCategory: the travel class</li>
 * <li>seat: a reserved seat for the voyager</li>
 * <li>sourceLocation: the beginning location</li>
 * <li>destinationLocation: the destination location</li>
 * </ul>
 * <p>
 * The ElectronicTicket is constructed by using the {@link TicketBuilder} which automatically
 * does 5 things at one time on-the-fly:
 * <ul>
 * <li>builds the ticket</li>
 * <li>registers the ticket to the ticket repository</li>
 * <li>sends the ticket to the voyager's phone</li>
 * <li>reserves the seat in the train for the voyager</li>
 * <li>increments the static ticket id counter</li>
 * </ul>
 * <p>
 * The {@link TicketBuilder} implements the {@link IBuilder} interface and provides the
 * <code>build()</code> method to construct the resulting ticket.
 */
public class ElectronicTicket {
    /**
     * a unique ticket id
     */
    private int ticketId = -1;

    /**
     * an instance of a Voyager used to access his phone to send the ticketId to it
     */
    private Voyager voyager;

    /**
     * the date of voyage
     */
    private Date travelTime;

    /**
     * the travel class
     */
    private TravelClass travelCategory;

    /**
     * a reserved seat for the voyager
     */
    private WaggonSeat seat;

    /**
     * the beginning location
     */
    private Source sourceLocation;

    /**
     * the destination location
     */
    private Destination destinationLocation;

    /**
     * Creates a new instance of the ElectronicTicket by applying all
     * attributes of the builder instance to the new ticket instance.
     * The ticket id is fetched from the static id field of {@link TicketBuilder}.
     *
     * @param builder the builder instance holding all attributes
     */
    private ElectronicTicket(TicketBuilder builder) {
        ticketId = TicketBuilder.ticketId;
        voyager = builder.voyager;
        travelTime = builder.travelTime;
        travelCategory = builder.travelCategory;
        seat = builder.seat;
        sourceLocation = builder.sourceLocation;
        destinationLocation = builder.destinationLocation;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * Sends the ticket id of this instance to the voyager's phone
     * to be written into the RFID chip.
     */
    private void sendToPhone() {
        voyager.getPhone().receiveTicketId(ticketId);
    }

    /**
     * Reserves the seat associated to this ticket by setting the seat's
     * id to the ticket id.
     */
    private void reserveSeat() {
        seat.setSeatId(ticketId);
    }

    /*
    Only getter methods here because ElectronicTicket is immutable.
    Attributes are set by the builder.
     */
    public int getTicketId() {
        return ticketId;
    }

    public Voyager getVoyager() {
        return voyager;
    }

    public Date getTravelTime() {
        return travelTime;
    }

    public TravelClass getTravelCategory() {
        return travelCategory;
    }

    public WaggonSeat getSeat() {
        return seat;
    }

    public Source getSourceLocation() {
        return sourceLocation;
    }

    public Destination getDestinationLocation() {
        return destinationLocation;
    }

    /**
     * The TicketBuilder is used to construct instances of {@link ElectronicTicket}.
     * This class implements the {@link IBuilder} interface and the Builder pattern.
     * All setter methods inside this class return the same instance of the
     * builder to allow cascaded invocations of setter methods. Finally the
     * <code>build()</code> method builds the instance and can be appended
     * to the sequence of set calls.
     * <p>
     * Example Usage:
     * <pre>
     *     ElectronicTicket.TicketBuilder builder = new ElectronicTicket.TicketBuilder();
     *     ElectronicTicket ticket = builder.setVoyager(anyVoyager)
     *                                      .setTravelTime(new Date())
     *                                      .setTravelCategory(TravelClass.FIRST)
     *                                      .setSeat(leftSeat)
     *                                      .setSourceLocation(Source.Berlin)
     *                                      .setDestinationLocation(Destination.Munich)
     *                                      .build();
     * </pre>
     */
    public static class TicketBuilder implements IBuilder<ElectronicTicket> {
        /**
         * <code>ticketId</code> is a static number inside the ticket builder which is
         * incremented automatically when a ticked is constructed. It takes care about
         * giving an unique id to each of the tickets.
         *
         * @see ElectronicTicket#ticketId
         */
        private static int ticketId = 0;

        /**
         * an instance of a Voyager used to access his phone to send the ticketId to it
         *
         * @see ElectronicTicket#voyager
         */
        private Voyager voyager;

        /**
         * the date of voyage
         *
         * @see ElectronicTicket#travelTime
         */
        private Date travelTime;

        /**
         * the travel class
         *
         * @see ElectronicTicket#travelCategory
         */
        private TravelClass travelCategory;

        /**
         * a reserved seat for the voyager
         *
         * @see ElectronicTicket#seat
         */
        private WaggonSeat seat;

        /**
         * the beginning location
         *
         * @see ElectronicTicket#sourceLocation
         */
        private Source sourceLocation;

        /**
         * the destination location
         *
         * @see ElectronicTicket#destinationLocation
         */
        private Destination destinationLocation;

        /*
        Only setter methods here used for ticket construction.
        No getter methods.
         */

        /**
         * Applies the voyager <code>voyager</code> to this TicketBuilder instance.
         *
         * @param voyager the voyager attribute for the ticket
         * @return the current TicketBuilder instance.
         */
        public TicketBuilder setVoyager(Voyager voyager) {
            this.voyager = voyager;
            return this;
        }

        /**
         * Applies the travel time <code>time</code> to this TicketBuilder instance.
         *
         * @param time the travel time attribute for the ticket
         * @return the current TicketBuilder instance.
         */
        public TicketBuilder setTravelTime(Date time) {
            travelTime = time;
            return this;
        }

        /**
         * Applies the the travel category <code>category</code> to this TicketBuilder
         * instance.
         *
         * @param category the travel category attribute for the ticket
         * @return the current TicketBuilder instance.
         */
        public TicketBuilder setTravelCategory(TravelClass category) {
            travelCategory = category;
            return this;
        }

        /**
         * Applies the seat <code>seat</code> to this TicketBuilder instance.
         *
         * @param seat the seat attribute for the ticket
         * @return the current TicketBuilder instance.
         */
        public TicketBuilder setSeat(WaggonSeat seat) {
            this.seat = seat;
            return this;
        }

        /**
         * Applies the source location <code>source</code> to this TicketBuilder instance.
         *
         * @param source the source location attribute for the ticket
         * @return the current TicketBuilder instance.
         */
        public TicketBuilder setSourceLocation(Source source) {
            sourceLocation = source;
            return this;
        }

        /**
         * Applies the destination location <code>destination</code> to this TicketBuilder instance.
         *
         * @param destination the destination location attribute for the ticket
         * @return the current TicketBuilder instance.
         */
        public TicketBuilder setDestinationLocation(Destination destination) {
            destinationLocation = destination;
            return this;
        }

        /**
         * Constructs the final instance of the ticket, registers this ticket into
         * the ticket repository, sends the ticket to the voyager's phone, reserves
         * the associated seat and returns the instance.
         *
         * @return the built ticket instance
         */
        public ElectronicTicket build() {
            ElectronicTicket ticket = new ElectronicTicket(this);
            TicketRepository.instance.registerTicket(ticket);
            ticket.sendToPhone();
            ticket.reserveSeat();
            ticketId++;
            return ticket;
        }

        /**
         * Resets the internal static ticket id counter back to 0.
         *
         * @see ElectronicTicket.TicketBuilder#ticketId
         */
        public static void resetIdCounter() {
            ticketId = 0;
        }
    }

    /**
     * Returns a string representation of this ticket instance with the values
     * of all attributes
     *
     * @return the string representation of this instance
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ElectronicTicket { ");
        builder.append("ticketId = ").append(ticketId).append(", ");
        builder.append("voyager = { ").append(voyager).append(", ");
        builder.append("travelTime = ").append(travelTime).append(", ");
        builder.append("travelCategory = ").append(travelCategory.name()).append(" class, ");
        builder.append("seat = ").append(seat).append(", ");
        builder.append("sourceLocation = ").append(sourceLocation.name()).append(", ");
        builder.append("destinationLocation = ").append(destinationLocation.name()).append(" }");
        builder.trimToSize();
        return builder.toString();
    }
}
