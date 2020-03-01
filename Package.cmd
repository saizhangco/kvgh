@echo off
set PATH=%cd%
mvn clean package -DskipTests
copy /y %PATH%\web\target\web-service.jar %PATH%/install/Windows/server/
