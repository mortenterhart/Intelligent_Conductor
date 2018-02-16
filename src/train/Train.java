package train;

/**
 * This class defines the Train object
 * and is organized as a singleton class
 * because the instance is required in
 * different classes. There should be
 * only one instance containing the waggon
 * with all seats.
 */
public class Train {
    private static Train instance = new Train();
    private Waggon waggon;

    private Train() {
        waggon = new Waggon();
    }

    /**
     * Returns the singleton instance of the train.
     *
     * @return the singleton instance
     */
    public static Train getInstance() {
        return instance;
    }

    public Waggon getWaggon() {
        return waggon;
    }
}
