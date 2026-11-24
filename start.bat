@echo off
echo =====================================
echo       Starting Car Repair System
echo =====================================

cd /d "%~dp0"

if not exist "mvnw.cmd" (
    echo ERROR: Maven Wrapper not found!
    echo Make sure this script is in the project root directory.
    pause
    exit /b 1
)

call mvnw.cmd spring-boot:run

if errorlevel 1 (
    echo.
    echo Application could not be started.
    pause
    exit /b 1
)

pause