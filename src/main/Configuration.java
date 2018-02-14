package main;

import random.MersenneTwisterFast;

public enum Configuration {
    instance;

    public String userDirectory = System.getProperty("user.dir");
    public String fileSeparator = System.getProperty("file.separator");
    public String lineSeparator = System.lineSeparator();

    public String logFile = userDirectory + fileSeparator + "log" + fileSeparator + "IntelligentConductor.log";

    public MersenneTwisterFast mersenneTwister = new MersenneTwisterFast();
}
