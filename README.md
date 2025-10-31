# SeparateVideoSRT
App that extracts SRT files from Video files using FFMPEG (Needs to be installed)

# Scripts

*Requires Java 21*

### Command to Install FFMPEG on Windows
`winget install ffmpeg`

## Run Dev Environment
### Linux 
`./gradlew run`
### Windows
`.\gradlew.bat run`

## Build App
*Note change gradle.build script depending on OS*
### Linux 
`./gradlew build jlink jpackage`
### Windows
`.\gradlew.bat build jlink jpackage`
