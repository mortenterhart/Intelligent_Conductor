package ticket;

import chip.EUChip;
import chip.IRFIDChip;
import chip.USChipAdapter;
import main.Configuration;
import train.Train;
import train.TravelClass;
import train.WaggonSeat;
import voyager.MPhone;
import voyager.Voyager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketProducer {
    private static ElectronicTicket.TicketBuilder builder = new ElectronicTicket.TicketBuilder();
    private static Train train = Train.getInstance();
    private static List<Voyager> voyagerList = new ArrayList<>();
    private static boolean ticketsProduced = false;

    private TicketProducer() {
    }

    public static void produceTickets() {
        voyagerList.clear();

        for (WaggonSeat leftSeat : train.getWaggon().getLeftSeats()) {
            Voyager leftVoyager = new Voyager("Dr. Left");
            leftVoyager.setFingerPrint("Thumbs up \uD83D\uDC4D");
            leftVoyager.setPhone(new MPhone(new EUChip()));
            voyagerList.add(leftVoyager);

            buildTicket(leftVoyager, TravelClass.FIRST, leftSeat,
                    randomEnumConstant(Source.class), randomEnumConstant(Destination.class));
        }

        for (WaggonSeat rightSeat : train.getWaggon().getRightSeats()) {
            Voyager rightVoyager = new Voyager("Dr. Right");
            rightVoyager.setFingerPrint("Thumbs down \uD83D\uDC4E");
            rightVoyager.setPhone(new MPhone(new USChipAdapter()));
            voyagerList.add(rightVoyager);

            buildTicket(rightVoyager, TravelClass.SECOND, rightSeat,
                    randomEnumConstant(Source.class), randomEnumConstant(Destination.class));
        }

        // Insert two voyagers whose ticket information are incorrect. The intelligent conductor
        // should throw these two out of the train.
        IRFIDChip euChip = new EUChip();
        euChip.setTicketId(TicketRepository.instance.numberOfTickets + 2);
        Voyager fareDodger = new Voyager("Fare Dodger", "hustler", new MPhone(euChip));
        voyagerList.add(fareDodger);

        IRFIDChip usAdapter = new USChipAdapter();
        Voyager voyagerWithFakeTicket = new Voyager("Fake Ticket", "not good",
                new MPhone(usAdapter));
        buildTicket(voyagerWithFakeTicket, TravelClass.SECOND, new WaggonSeat(),
                Source.Cologne, Destination.Stuttgart);
        // Overwrite chip id set in the ticket builder with an invalid ticket id so that
        // the intelligent conductor notices the counterfeit ticket.
        usAdapter.setTicketId(TicketRepository.instance.numberOfTickets + 3);
        voyagerList.add(voyagerWithFakeTicket);

        ticketsProduced = true;
    }

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

    private static <E extends Enum<E>> E randomEnumConstant(Class<E> clazz) {
        int x = Configuration.instance.mersenneTwister.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public static boolean areTicketsProduced() {
        return ticketsProduced;
    }

    public static List<Voyager> getListOfPassengers() {
        return voyagerList;
    }
}
