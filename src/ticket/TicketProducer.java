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

public class TicketProducer {
    private static ElectronicTicket.TicketBuilder builder = new ElectronicTicket.TicketBuilder();
    private static Train train = Train.getInstance();

    private TicketProducer() {
    }

    public static void produceTickets() {
        for (WaggonSeat leftSeat : train.getWaggon().getLeftSeats()) {
            Voyager leftVoyager = new Voyager("Dr. Left");
            leftVoyager.setFingerprint("Thumbs up \uD83D\uDC4D");
            leftVoyager.setPhone(new MPhone(new EUChip()));

            buildTicket(leftVoyager, new Date(), TravelClass.FIRST, leftSeat,
                    randomEnumConstant(Source.class), randomEnumConstant(Destination.class));
        }

        for (WaggonSeat rightSeat : train.getWaggon().getRightSeats()) {
            Voyager rightVoyager = new Voyager("Dr. Right");
            rightVoyager.setFingerprint("Thumbs down \uD83D\uDC4E");
            rightVoyager.setPhone(new MPhone(new USChipAdapter()));

            buildTicket(rightVoyager, new Date(), TravelClass.SECOND, rightSeat,
                    randomEnumConstant(Source.class), randomEnumConstant(Destination.class));
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
