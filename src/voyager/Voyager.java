package voyager;

import ticket.TicketRepository;

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

    public boolean hasPhone() {
        return phone != null;
    }

    public boolean hasTicketBought() {
        return TicketRepository.instance.hasTicketBought(this);
    }

    public void setPhone(MPhone phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "Voyager { name = " + name + ", fingerPrint = \"" + fingerPrint + "\", phone = " + phone + " }";
    }
}
