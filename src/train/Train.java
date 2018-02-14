package train;

public class Train {
    private static Train instance = new Train();
    private Waggon waggon;

    private Train() {
        waggon = new Waggon();
    }

    public static Train getInstance() {
        return instance;
    }

    public Waggon getWaggon() {
        return waggon;
    }
}
