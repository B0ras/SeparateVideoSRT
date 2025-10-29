package org.example.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class WriteSRTToFileTask extends Thread {
    private String videoFilePath;
    private String filePathToWrite;
    private final Logger LOGGER = new MyLogger().getLogger();
    
    WriteSRTToFileTask(String videoFilePath, String filePathToWrite){
        this.videoFilePath = videoFilePath;
        this.filePathToWrite = filePathToWrite;
    }

    public void run(){
        
        try{
            
            String[] command = new String[]{"ffmpeg","-y","-i", videoFilePath ,"-map", "0:s:0", filePathToWrite };
            ProcessBuilder builder = new ProcessBuilder(command);
            Process process = builder.start();
            

            InputStreamReader error = new InputStreamReader(process.getErrorStream());
            BufferedReader errorReader = new BufferedReader(error);

            String line = null;
            LOGGER.info("Here begins the output of ffmpeg");
            while((line = errorReader.readLine()) != null){
                LOGGER.info(line);
            }
            LOGGER.info("Here ends the output of ffmpeg");
            int exitValue = process.waitFor();
            LOGGER.info("Process Finished with value: " + exitValue);
            
        }catch(Exception e){
            LOGGER.severe("Error extracting subtitles");
            LOGGER.severe(e.getMessage());
        }
    }
}
