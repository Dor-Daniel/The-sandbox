@echo off
REM ===============================================
REM  Build & Run Arkanoid Game
REM ===============================================

REM Define paths (relative to this .bat file)
set SRC=src
set OUT=out
set LIB=%SRC%\Externals\biuoop-1.4.jar

REM Clean previous build
if exist "%OUT%" rmdir /s /q "%OUT%"

echo.
echo Compiling Java sources...
echo -----------------------------------------------

REM Collect all .java files recursively
dir /s /b "%SRC%\*.java" > "%SRC%\sources.txt"

REM Compile everything
javac -cp ".;%LIB%" -d "%OUT%" @"%SRC%\sources.txt"

if errorlevel 1 (
    echo.
    echo Compilation failed.
    pause
    exit /b
)

del sources.txt

echo.
echo Running Arkanoid Game...
echo -----------------------------------------------
java -cp ".;%OUT%;%LIB%" Main

echo.
pause
