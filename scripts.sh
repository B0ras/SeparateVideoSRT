#!/usr/bin/bash
javac -d out \
--module-path ~/Downloads/javafx-jmods-21.0.8:$JAVA_HOME/jmods \
--add-modules java.base,java.desktop,javafx.controls,javafx.fxml,javafx.graphics,javafx.media \
app/src/main/java/module-info.java

javac -d out \
--module-path ~/Downloads/javafx-jmods-21.0.8:$JAVA_HOME/jmods \
--add-modules java.base,java.desktop,javafx.controls,javafx.fxml,javafx.graphics,javafx.media \
app/src/main/java/org/example/App.java

jlink --launcher customjrelauncher2=testfx/org.example.App \
--module-path ~/Downloads/javafx-jmods-21.0.8:$JAVA_HOME/jmods:out \
--add-modules java.base,java.desktop,javafx.controls,javafx.fxml,javafx.graphics,javafx.media,testfx \
--output customjre2  

jpackage --name srtvideoex \
--input app/build/libs \
--main-jar app.jar \
--runtime-image customjre \
--linux-shortcut \
--add-modules java.base,java.desktop,javafx.controls,javafx.fxml,javafx.graphics,javafx.media \
--main-class org.example.App
