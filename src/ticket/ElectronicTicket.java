package ticket;

import train.TravelClass;
import train.WaggonSeat;
import voyager.Voyager;

import java.util.Date;

public class ElectronicTicket {
    private int ticketId = 0;
    private Voyager voyager;
    private Date travelTime;
    private TravelClass travelCategory;
    private WaggonSeat seat;
    private Source sourceLocation;
    private Destination destinationLocation;

    private ElectronicTicket(TicketBuilder builder) {
        ticketId            = builder.ticketId;
        voyager             = builder.voyager;
        travelTime          = builder.travelTime;
        travelCategory      = builder.travelCategory;
        seat                = builder.seat;
        sourceLocation      = builder.sourceLocation;
        destinationLocation = builder.destinationLocation;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void sendToPhone() {
        voyager.getPhone().receiveTicketId(ticketId);
    }

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

    public static class TicketBuilder implements IBuilder<ElectronicTicket> {
        private int ticketId = 0;
        private Voyager voyager;
        private Date travelTime;
        private TravelClass travelCategory;
        private WaggonSeat seat;
        private Source sourceLocation;
        private Destination destinationLocation;

        public TicketBuilder setTicketId(int id) {
            ticketId = id;
            return this;
        }

        public TicketBuilder setVoyager(Voyager voyager) {
            this.voyager = voyager;
            return this;
        }

        public TicketBuilder setTravelTime(Date time) {
            travelTime = time;
            return this;
        }

        public TicketBuilder setTravelCategory(TravelClass category) {
            travelCategory = category;
            return this;
        }

        public TicketBuilder setSeat(WaggonSeat seat) {
            this.seat = seat;
            return this;
        }

        public TicketBuilder setSourceLocation(Source source) {
            sourceLocation = source;
            return this;
        }

        public TicketBuilder setDestinationLocation(Destination destination) {
            destinationLocation = destination;
            return this;
        }

        @Override
        public ElectronicTicket build() {
            ElectronicTicket ticket = new ElectronicTicket(this);
            TicketRepository.instance.registerTicket(ticket);
            ticket.sendToPhone();
            return ticket;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Ticket { ");
        builder.append("ticketId = ").append(ticketId).append(", ");
        builder.append("voyager = { ").append(voyager).append(", ");
        builder.append("travelTime = ").append(travelTime).append(", ");
        builder.append("travelCategory = ").append(travelCategory.name()).append(" class, ");
        builder.append("seat = ").append(seat).append(", ");
        builder.append("sourceLocation = ").append(sourceLocation.name()).append(", ");
        builder.append("destinationLocation = ").append(destinationLocation.name()).append(" }");
        return builder.toString();
    }
}
