package voyager;

import ticket.TicketRepository;

/**
 * This class describes the Voyager object model. A voyager
 * is a person entering the train in order to travel to another
 * location. A voyager has a name, a fingerprint signature and
 * a mobile phone holding an {@link chip.IRFIDChip}.
 */
public class Voyager {
    private String name;
    private String fingerPrint;
    private MPhone phone;

    public Voyager() {
        name = new String();
        fingerPrint = new String();
    }

    public Voyager(String name) {
        this();
        this.name = name;
    }

    public Voyager(String name, String fingerPrint, MPhone phone) {
        this.name = name;
        this.fingerPrint = fingerPrint;
        this.phone = phone;
    }

    /**
     * Checks if this voyager hosts an instance of MPhone different from null.
     *
     * @return true if the phone instance is non-null, false otherwise.
     */
    public boolean hasPhone() {
        return phone != null;
    }

    /**
     * Investigates if this voyager has a corresponding ticket inside the ticket
     * repository and returns true if so.
     *
     * @return true if this voyager has bought a ticket, false otherwise.
     */
    public boolean hasTicketBought() {
        return TicketRepository.instance.hasTicketBought(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public MPhone getPhone() {
        return phone;
    }

    public void setPhone(MPhone phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "Voyager { name = " + name + ", fingerPrint = \"" + fingerPrint + "\", phone = " + phone + " }";
    }
}
