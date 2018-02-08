package train;

public class WaggonSeat {
    private int seatId = 0;
    private SeatLocation location;

    public WaggonSeat(int id) {
        seatId = id;
        location = SeatLocation.left;
    }

    public WaggonSeat(SeatLocation seatLocation) {
        this(0);
        location = seatLocation;
    }

    public WaggonSeat(int id, SeatLocation seatLocation) {
        seatId = id;
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
        return "train.WaggonSeat { seatId = " + seatId + ", location = " + location.toString();
    }
}
