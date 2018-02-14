package voyager;

import chip.EUChip;
import chip.IRFIDChip;
import chip.USChip;
import chip.USChipAdapter;

public class Voyager {
    private String name;
    private String fingerprint;
    private MPhone phone;

    public Voyager() {
        name = new String();
        fingerprint = new String();
    }

    public Voyager(String name) {
        this();
        this.name = name;
    }

    public void buyTicket() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public MPhone getPhone() {
        return phone;
    }

    public void setPhone(MPhone phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return "Voyager { name = " + name + ", fingerprint = \"" + fingerprint + "\", phone = " + phone + " }";
    }
}
