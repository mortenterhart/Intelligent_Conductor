package ticket;

/**
 * Interface for a concrete builder instance
 * @param <T> the object type this builder builds
 */
public interface IBuilder<T> {

    /**
     * Constructs an instance of object of type <code>T</code>
     * @return the instance
     */
    public T build();
}
