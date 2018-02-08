package ticket;

import java.util.HashMap;
import java.util.Map;

public enum TicketRepository {
    instance;

    private int numberOfTickets = 50;
    public Map<Integer, ElectronicTicket> repository = new HashMap<>(numberOfTickets);

    /**
     * Registers a new ticket in the ticket repository only if that ticket
     * has no instance in the repository already.
     * @param ticket The ticket to register
     * @return true if the ticket has been newly added to the repository, false otherwise.
     */
    public boolean registerTicket(ElectronicTicket ticket) {
        if(!isTicketRegistered(ticket)) {
            repository.put(ticket.getTicketId(), ticket);
            return true;
        }
        return false;
    }

    /**
     * Examines if a given ticket is already registered within the repository or not.
     * @param ticket the ticket to check
     * @return true if the ticket is already registered, false otherwise.
     */
    public boolean isTicketRegistered(ElectronicTicket ticket) {
        return repository.containsKey(ticket.getTicketId());
    }

    /**
     * Examines if a given ticket id is already registered within the repository or not.
     * @param ticketId the ticket id to check
     * @return true if the ticket id is already registered, false otherwise.
     */
    public boolean isTicketIdRegistered(int ticketId) {
        return repository.containsKey(ticketId);
    }

    /**
     * Requests the delivery of the ticket with the designated unique id.
     * @param ticketId the ticket id
     * @return the ticket if the id is registered in the repository, null otherwise.
     */
    public ElectronicTicket getTicket(int ticketId) {
        return repository.get(ticketId);
    }
}
