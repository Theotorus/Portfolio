@echo off
cd /d %~dp0

echo Starting local server for Portfolio...
echo.

rem Check if Python is installed
python --version >nul 2>&1
IF %ERRORLEVEL% NEQ 0 (
    echo Python n'est pas installé sur ce système.
    echo Impossible de lancer le portfolio.
    pause
    exit /b
)

cd dist
start "" http://localhost:5173
python -m http.server 5173