@echo off
rem Crée un dossier "out" si inexistant
if not exist out mkdir out

rem Compile en envoyant les .class dans "out"
javac -d out src\Main.java

rem Exécute à partir du dossier "out"
java -cp out Main

