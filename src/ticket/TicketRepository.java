package ticket;

import conductor.IntelligentConductor;
import conductor.ScanningDevice;
import train.Train;
import voyager.Voyager;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The central ticket repository as a singleton class realized
 * by an enumeration. This class holds the repository where
 * producers and builders can register their tickets that are
 * assumed to be valid tickets. The {@link IntelligentConductor} and
 * the {@link ScanningDevice} can examine the validity of tickets from
 * passengers and can look if tickets for specific voyagers or
 * ticket ids exist.
 * <p>
 * The repository memory is organized by mapping the unique ticket
 * id to the ticket instance.
 * Example: <code>5 -&lt; Ticket { ticketId, voyager, ... }</code>
 */
public enum TicketRepository {
    /**
     * The central instance of this TicketRepository
     */
    instance;

    /**
     * The total number of tickets constructed (equal to the number of seats).
     * The variable is initialized with the total number of seats so that there
     * is an equal amount of tickets and corresponding seats.
     */
    public final int numberOfTickets = Train.getInstance().getWaggon().getTotalNumberOfSeats();

    /**
     * The final repository containing all registered tickets. It maps ticket ids to
     * the concrete ticket instances and is initialized with a capacity equal to the
     * number of tickets.
     */
    private final Map<Integer, ElectronicTicket> repository = new HashMap<>(numberOfTickets);

    /**
     * Registers a new ticket in the ticket repository only if that ticket
     * has no instance in the repository already.
     *
     * @param ticket The ticket to register
     * @return true if the ticket has been newly added to the repository, false otherwise.
     */
    public boolean registerTicket(ElectronicTicket ticket) {
        if (!isTicketRegistered(ticket)) {
            repository.put(ticket.getTicketId(), ticket);
            return true;
        }
        return false;
    }

    /**
     * Examines if a given ticket is already registered within the repository or not.
     *
     * @param ticket the ticket to check
     * @return true if the ticket is already registered, false otherwise.
     */
    public boolean isTicketRegistered(ElectronicTicket ticket) {
        return repository.containsKey(ticket.getTicketId());
    }

    /**
     * Examines if a given ticket id is already registered within the repository or not.
     *
     * @param ticketId the ticket id to check
     * @return true if the ticket id is already registered, false otherwise.
     */
    public boolean isTicketRegistered(int ticketId) {
        return repository.containsKey(ticketId);
    }

    /**
     * Requests the delivery of the ticket with the designated unique id.
     *
     * @param ticketId the ticket id
     * @return the ticket if the id is registered in the repository, null otherwise.
     */
    public ElectronicTicket getTicket(int ticketId) {
        return repository.get(ticketId);
    }

    /**
     * Requests the delivery of the ticket for a special voyager and checks if the voyager
     * has bought a ticket
     *
     * @param voyager the voyager to look for
     * @return the ticket if the voyager has a ticket in the repository, null otherwise.
     */
    public ElectronicTicket getTicket(Voyager voyager) {
        for (ElectronicTicket ticket : repository.values()) {
            if (ticket.getVoyager() == voyager) {
                return ticket;
            }
        }
        return null;
    }

    /**
     * Searches the repository for the specified ticket and returns true if
     * the ticket was found, false otherwise.
     *
     * @param ticket the ticket to search for
     * @return true if the ticket is inside this repository, false otherwise.
     */
    public boolean contains(ElectronicTicket ticket) {
        return repository.containsValue(ticket);
    }

    /**
     * Removes a ticket with the specified id from the repository
     *
     * @param ticketId the ticket id
     * @return true if a ticket was removed, false otherwise.
     */
    public boolean removeTicket(int ticketId) {
        return repository.remove(ticketId) != null;
    }

    /**
     * Searches for a ticket registered to voyager and removes it if present
     *
     * @param voyager the voyager whose ticket should be removed
     * @return true if the ticket could be found and removed, false otherwise.
     */
    public boolean removeTicket(Voyager voyager) {
        ElectronicTicket ticket = getTicket(voyager);
        return ticket != null && repository.remove(ticket.getTicketId()) != null;
    }

    /**
     * Checks if the specified voyager has a registered ticket in the repository
     *
     * @param voyager the voyager
     * @return true if the voyager has a ticket, false otherwise.
     */
    public boolean hasTicketBought(Voyager voyager) {
        return getTicket(voyager) != null;
    }

    /**
     * Builds a set containing all ticket id keys of the repository.
     *
     * @return a set with all ticket ids
     */
    public Set<Integer> ticketIdSet() {
        return repository.keySet();
    }

    /**
     * Builds a collection of all ticket instances contained in the repository.
     *
     * @return a collection of all ticket instances
     */
    public Collection<ElectronicTicket> tickets() {
        return repository.values();
    }

    /**
     * Returns the size of this repository.
     *
     * @return the number of tickets stored in this repository.
     */
    public int size() {
        return repository.size();
    }

    /**
     * Clears the underlying repository by removing all ticket mappings.
     */
    public void clear() {
        repository.clear();
    }
}
