package train;

/**
 * A WaggonSeat is a single seat that is part of a
 * {@link Waggon}. Each seat provides a unique seat
 * id that is set initially when a new ticket is built.
 * The builder attempts to reserve the specific seat
 * and initializes the seat id with the ticket id.
 * The {@link SeatLocation} tells on which side the
 * seat is located inside the {@link Waggon}.
 */
public class WaggonSeat {
    private int seatId = -1;
    private SeatLocation location;

    public WaggonSeat() {
        location = SeatLocation.left;
    }

    public WaggonSeat(SeatLocation seatLocation) {
        location = seatLocation;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public SeatLocation getLocation() {
        return location;
    }

    public void setLocation(SeatLocation location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "WaggonSeat { seatId = " + seatId + ", location = " + location.toString();
    }
}
