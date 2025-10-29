package org.example.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

import javafx.scene.image.WritableImage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

public class Utils {

    private static final Logger LOGGER = new MyLogger().getLogger();

     public static WritableImage getImageMetadata(File file){
        WritableImage thumbnail = new WritableImage(100,100);
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setVisible(false);
        mediaPlayer.muteProperty();
        mediaPlayer.seek(new Duration(media.getDuration().toMillis()/2));
        mediaView.snapshot(null, thumbnail);

        return thumbnail;

    }

    public static String downloadFFMPEG(){

        ProcessBuilder builder = new ProcessBuilder("cmd.exe","%appdata%\\..\\Local\\Microsoft\\WindowsApps\\winget.exe","install ffmpeg");
        try {
            Process p = builder.start();
            if(p.exitValue() == 0){
                return "FFMPEG Downloaded Successfully";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Error downloading FFMPEG";
    }

    public static File getSRTContent(String videoFilePath){
        final String FILE_NAME = "in.srt";
        Thread t1 = new WriteSRTToFileTask(videoFilePath,FILE_NAME);
        t1.start();
        synchronized (t1){
            try {
                t1.wait();
            } catch (Exception e) {
                LOGGER.severe("Error extracting SRT file");
                LOGGER.severe(e.getMessage());
            }
        }

        System.out.println("Thread Finished");
        return new File(FILE_NAME);
        
    }

    public static String trimSRT(File srtContent){
        String res = "";
        try {
            Path pathToFile = srtContent.toPath();
            List<String> strST = Files.readAllLines(pathToFile.toAbsolutePath());
            for (String line : strST) {
                String regex1 = "^\\d+$";
                String regex2 = "^\\d{2}:\\d{2}:\\d{2},\\d{3}.*";
                res = res + "\n" + line.replaceAll(regex1, "").replaceAll(regex2, "").trim();
            }
            res = res.replaceAll("(?m)^[ \\t]*\\r?\\n", "");

        } catch (IOException e) {
            LOGGER.severe("Error Writing TXT File");
            if(res == "")
                LOGGER.severe("RES is empty");
            LOGGER.severe(e.getMessage());
        }
        return res;
    }
    
    public static void writeToDocx(String content){
        // try {
        //     WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();
        //     MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();
        //     mainDocumentPart.addParagraphOfText(content);

        LOGGER.info("Here begins the content of the extracted file");
        File docxFile = new File(System.getProperty("user.home") + "/Desktop/output.txt");
        //     wordPackage.save(docxFile);
        // }catch(Exception e){
        //     e.printStackTrace();
        // }

        try {
            Files.write(docxFile.toPath(), content.getBytes() );
        } catch (Exception e) {
            LOGGER.severe("Could not create TXT file");
            LOGGER.severe(e.getMessage());
        }
        LOGGER.info("Here ends the content of the extracted file");

    }

}
