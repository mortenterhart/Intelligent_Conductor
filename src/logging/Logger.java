package logging;

import main.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public enum Logger {
    instance;

    private PrintWriter writer;

    public void init() {
        try {
            writer = new PrintWriter(new File(Configuration.instance.logFile));
        } catch (FileNotFoundException exc) {
            System.err.println("Error: Log File '" + Configuration.instance.logFile +
                    "' could not be created!");
            exc.printStackTrace();
        }
    }

    public void write(String message) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentDate = new Date();
        writer.write(dateFormat.format(currentDate) + ": " + message +
                Configuration.instance.lineSeparator);
    }
}
