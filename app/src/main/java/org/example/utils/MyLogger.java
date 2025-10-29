package org.example.utils;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {

    private Logger logger;
    private final String loggerFilePath = System.getProperty("java.io.tmpdir") + "/SRTVideo.log";

    public MyLogger() {
        this.logger = Logger.getLogger("MyLog");
        FileHandler fh;
        try {
            fh = new FileHandler(loggerFilePath);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Logger getLogger(){
        return this.logger;
    }
}
