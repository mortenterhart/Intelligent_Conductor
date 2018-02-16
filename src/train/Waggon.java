package train;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The Waggon is a part of the train and contains
 * a list of {@link WaggonSeat}s and an aisle. Also
 * an instance defines the total number of seats.
 * All seats of the Waggon are instantiated inside
 * the constructor with a differentiation between
 * seats on the left and the right side. The location
 * of the seat is specified over the {@link SeatLocation}
 * attribute.
 */
public class Waggon {
    private List<WaggonSeat> seats;
    private int numberOfSeats = 50;
    private Aisle aisle;

    /**
     * The constructor initializes the Waggon with seats counting from 0 to
     * 24 on the left side and 25 to 49 on the right side. The aisle is
     * created too.
     */
    public Waggon() {
        // Initialize all seats on the left side with a unique id
        seats = new ArrayList<>(numberOfSeats);
        IntStream.range(0, numberOfSeats / 2).forEachOrdered(leftId ->
                seats.add(new WaggonSeat(SeatLocation.left)));

        // Initialize all seats on the right side with a unique id
        IntStream.range(numberOfSeats / 2, numberOfSeats).forEachOrdered(rightId ->
                seats.add(new WaggonSeat(SeatLocation.right)));

        // Instantiate the aisle
        aisle = new Aisle(0.7);
    }

    /**
     * Filters out all seats located on the left side and returns them as list.
     *
     * @return a list of seats on the left side
     */
    public List<WaggonSeat> getLeftSeats() {
        return seats.stream().filter(seat -> seat.getLocation() == SeatLocation.left)
                .collect(Collectors.toList());
    }

    /**
     * Filters out all seats located on the right side and returns them as list.
     *
     * @return a list of seats on the right side
     */
    public List<WaggonSeat> getRightSeats() {
        return seats.stream().filter(seat -> seat.getLocation() == SeatLocation.right)
                .collect(Collectors.toList());
    }

    /**
     * Returns the number of seats.
     *
     * @return the number of seats.
     */
    public int getTotalNumberOfSeats() {
        return numberOfSeats;
    }

    /**
     * Returns the instance of the aisle.
     *
     * @return the aisle instance
     */
    public Aisle getAisle() {
        return aisle;
    }
}
