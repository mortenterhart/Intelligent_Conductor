package train;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Waggon {
    private List<WaggonSeat> seats;
    private int numberOfSeats = 50;
    private Aisle aisle;

    public Waggon() {
        // Initialize all seats on the left side with a unique id
        seats = new ArrayList<>(numberOfSeats);
        IntStream.range(0, numberOfSeats / 2).forEachOrdered(leftId ->
                seats.add(new WaggonSeat(leftId, SeatLocation.left)));

        // Initialize all seats on the right side with a unique id
        IntStream.range(numberOfSeats / 2, numberOfSeats).forEachOrdered(rightId ->
                seats.add(new WaggonSeat(rightId, SeatLocation.right)));

        // Instantiate the aisle
        aisle = new Aisle(0.7);
    }

    public List<WaggonSeat> getLeftSeats() {
        return seats.stream().filter(seat -> seat.getLocation() == SeatLocation.left)
                .collect(Collectors.toList());
    }

    public List<WaggonSeat> getRightSeats() {
        return seats.stream().filter(seat -> seat.getLocation() == SeatLocation.right)
                .collect(Collectors.toList());
    }

    public int getTotalNumberOfSeats() {
        return numberOfSeats;
    }

    public Aisle getAisle() {
        return aisle;
    }
}
