package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.media.MediaView;

import org.example.utils.Utils;

public class Controller {
    
    @FXML
    private Button chooseFileButton;

    @FXML
    private Text resultText;

    @FXML
    private ImageView imageView;

    @FXML
    private MediaView mediaView;

    
    @FXML
    private void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a File");

        // Optional filter
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.mkv", "*.avi")
        );

        // Get the stage from the button
        Stage stage = (Stage) chooseFileButton.getScene().getWindow();

        File selectedFile = fileChooser.showOpenDialog(stage);
        
        if (selectedFile != null) {
            resultText.setText("Video Loaded");
        } else {
            resultText.setText("No file selected");
        }

        // WritableImage thumbnail = Utils.getImageMetadata(selectedFile);
        // imageView.setImage(thumbnail);
        
        File srt = Utils.getSRTContent(selectedFile.getAbsolutePath());
        
        String trimmedSRT = Utils.trimSRT(srt);

        srt.delete();
        
        Utils.writeToDocx(trimmedSRT);





        // ============================
    //    File placeholderImage = new File(getClass().getResource("/video_placeholder.png").toString());
    //    imageView.setImage(new Image(placeholderImage.getAbsolutePath()));
        // imageView.setImage(selectedFile.get);
    }

   
    
    @FXML
    private void downloadFFMPEG() {
        String result = Utils.downloadFFMPEG();
        resultText.setText(result);
    }

}
