package logging;

import main.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A logging instance writing all received messages to the
 * standard output channel and to a log file specified in
 * the {@link Configuration} class.
 */
public enum Logger {
    instance;

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private PrintWriter writer;

    /**
     * Builds the logger with the log file path specified in the Configuration.
     */
    public void init() {
        try {
            writer = new PrintWriter(new File(Configuration.instance.logFile));
        } catch (FileNotFoundException exc) {
            System.err.println("Error: Log File '" + Configuration.instance.logFile +
                    "' could not be created!");
            exc.printStackTrace();
        }
    }

    /**
     * Writes a message to the log file preceding all new line characters
     * with the current date prefix.
     *
     * @param message the message
     */
    public void write(String message) {
        String datePrefix = "[" + dateFormat.format(new Date()) + "]: ";
        writer.write(datePrefix + message.replaceAll("\n", "\n" + datePrefix) +
                Configuration.instance.lineSeparator);
        writer.flush();
    }

    /**
     * Logs a message to the log file and the standard output.
     *
     * @param message the message
     */
    public void log(String message) {
        write(message);
        System.out.println(message);
    }

    /**
     * Writes a new line in the log file and on standard output.
     */
    public void newLine() {
        write("");
        System.out.println();
    }

    /**
     * Closes the logging instance.
     */
    public void close() {
        writer.close();
    }
}
