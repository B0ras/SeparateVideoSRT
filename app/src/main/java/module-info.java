module srtvideomodule {
    
    requires java.base;
    requires java.desktop;
    requires javafx.controls;
    requires javafx.media;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    
    requires java.logging;
    
    
    
    opens org.example to javafx.fxml;
    

    exports org.example;
    
}
