package main;

import random.MersenneTwisterFast;

/**
 * Global Configuration enum. Some preferences
 * can be set here such as the log file path or
 * the used random generator.
 */
public enum Configuration {
    instance;

    /**
     * user directory path
     */
    public String userDirectory = System.getProperty("user.dir");

    /**
     * system specific file separator
     */
    public String fileSeparator = System.getProperty("file.separator");

    /**
     * system specific line separator
     */
    public String lineSeparator = System.lineSeparator();



    /**
     * Log file path used by enum {@link logging.Logger}
     */
    public String logFile = userDirectory + fileSeparator + "log" + fileSeparator + "IntelligentConductor.log";

    /**
     * Central instance of the random generator {@link MersenneTwisterFast}
     */
    public MersenneTwisterFast mersenneTwister = new MersenneTwisterFast();
}
