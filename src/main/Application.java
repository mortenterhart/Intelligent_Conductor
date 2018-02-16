package main;

import logging.Logger;
import ticket.TicketProducer;
import conductor.IntelligentConductor;
import train.Train;

/**
 * Main Application which is called by Java
 * at execution time. It creates the train
 * and the intelligent conductor and initializes
 * the application by producing tickets and
 * giving them to the conductor. Finally, the
 * conductor starts his tour through the waggon
 * and checks the passenger's tickets.
 */
public class Application {
    private Train train;
    private IntelligentConductor conductor;

    public Application() {
        constructTrain();
        constructConductor();
    }

    /**
     * Sets the train instance to the attribute
     */
    private void constructTrain() {
        train = Train.getInstance();
    }

    /**
     * Creates an instance of the intelligent conductor
     */
    private void constructConductor() {
        conductor = new IntelligentConductor();
    }

    /**
     * Initializes the tickets and the conductor by passing
     * the list of voyagers to him.
     */
    private void initialize() {
        Logger.instance.init();
        Logger.instance.log("## Initializing tickets and conductor (Application.initialize())");
        TicketProducer.produceTickets(true);
        Logger.instance.newLine();
        conductor.init();
        Logger.instance.newLine();
    }

    /**
     * Executes the program and says the conductor to start
     * his tour.
     */
    private void execute() {
        Logger.instance.log("## Starting Application \"Intelligent Conductor\" (Application.execute())");
        conductor.doYourJob();
    }

    /**
     * Closes all resources used by this program at the end.
     */
    private void prepareShutdown() {
        Logger.instance.log("\n## Preparing Application for shutdown by " +
                "closing resources (Application.prepareShutdown)");
        Logger.instance.close();
    }

    /**
     * Main method (entry point to the program).
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Application application = new Application();
        application.initialize();
        application.execute();
        application.prepareShutdown();

        Logger.instance.newLine();
        Logger.instance.log("## Application shutdown");
    }
}
