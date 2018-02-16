package train;

/**
 * As part of the {@link Waggon}, the Aisle is created
 * within the Waggon class. It provides the conductor's
 * frequency and a custom height.
 */
public class Aisle {
    private double conductorFrequency = 0.0;
    private int height = 0;

    public Aisle() {
        this(0.7);
    }

    public Aisle(double conductorFrequency) {
        this.conductorFrequency = conductorFrequency;
        height = 5;
    }

    public double getConductorFrequency() {
        return conductorFrequency;
    }

    public int getHeight() {
        return height;
    }
}
