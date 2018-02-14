package ticket;

import chip.EUChip;
import chip.USChipAdapter;
import main.Configuration;
import train.Train;
import train.TravelClass;
import train.WaggonSeat;
import voyager.MPhone;
import voyager.Voyager;

import java.util.Date;
import java.util.List;

public class TicketProducer {
    private static ElectronicTicket.TicketBuilder builder = new ElectronicTicket.TicketBuilder();
    private static Train train = Train.getInstance();

    private TicketProducer() {
    }

    public static void produceTickets() {
        Voyager leftVoyager = new Voyager("Dr. Left");
        leftVoyager.setFingerprint("Thumbs up \uD83D\uDC4D");
        leftVoyager.setPhone(new MPhone(new EUChip()));
        buildTicketsForEachSeat(train.getWaggon().getLeftSeats(), leftVoyager, TravelClass.FIRST);

        Voyager rightVoyager = new Voyager("Dr. Right");
        rightVoyager.setFingerprint("Thumbs down \uD83D\uDC4E");
        rightVoyager.setPhone(new MPhone(new USChipAdapter()));
        buildTicketsForEachSeat(train.getWaggon().getRightSeats(), rightVoyager, TravelClass.SECOND);
    }

    private static void buildTicketsForEachSeat(List<WaggonSeat> availableSeats, Voyager sampleVoyager, TravelClass category) {
        for (WaggonSeat seat : availableSeats) {
            Voyager copiedVoyager = new Voyager(sampleVoyager);

            Source source = randomEnumConstant(Source.class);
            Destination destination = randomEnumConstant(Destination.class);

            buildTicket(copiedVoyager, new Date(), category,
                    seat, source, destination);
        }
    }

    private static void buildTicket(Voyager voyager, Date travelTime, TravelClass category,
                                    WaggonSeat seat, Source srcLocation, Destination destLocation) {
        builder.setVoyager(voyager)
                .setTravelTime(travelTime)
                .setTravelCategory(category)
                .setSeat(seat)
                .setSourceLocation(srcLocation)
                .setDestinationLocation(destLocation)
                .build();
    }

    private static <E extends Enum<E>> E randomEnumConstant(Class<E> clazz) {
        int x = Configuration.instance.mersenneTwister.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
