package train;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Waggon {
    private List<WaggonSeat> leftSeats;
    private List<WaggonSeat> rightSeats;
    private int numberOfSeats = 25;
    private Aisle aisle;

    public Waggon() {
        // Initialize all seats on the left side with a unique id
        leftSeats = new ArrayList<>(25);
        IntStream.range(0, numberOfSeats).forEachOrdered(leftId ->
                leftSeats.add(new WaggonSeat(leftId, SeatLocation.left)));

        // Initialize all seats on the right side with a unique id
        rightSeats = new ArrayList<>(25);
        IntStream.range(numberOfSeats, getTotalNumberOfSeats()).forEachOrdered(rightId ->
                rightSeats.add(new WaggonSeat(rightId, SeatLocation.right)));

        // Instantiate the aisle
        aisle = new Aisle();
    }

    public List<WaggonSeat> getLeftSeats() {
        return leftSeats;
    }

    public List<WaggonSeat> getRightSeats() {
        return rightSeats;
    }

    public int getNumberOfSeatsOnOneSide() {
        return numberOfSeats;
    }

    public int getTotalNumberOfSeats() {
        return numberOfSeats * 2;
    }

    public Aisle getAisle() {
        return aisle;
    }
}
