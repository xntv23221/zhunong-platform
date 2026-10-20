@echo off
setlocal

set "ROOT=%~sdp0"
set "PID_FILE=%ROOT%target\app.pid"

if not exist "%PID_FILE%" (
  echo No PID file found.
  exit /b 0
)

for /f "usebackq delims=" %%i in ("%PID_FILE%") do set "APP_PID=%%i"

if not defined APP_PID (
  echo PID file is empty.
  del /q "%PID_FILE%" >nul 2>nul
  exit /b 0
)

tasklist /fi "PID eq %APP_PID%" | findstr /r " %APP_PID% " >nul
if errorlevel 1 (
  echo Process %APP_PID% is not running.
  del /q "%PID_FILE%" >nul 2>nul
  exit /b 0
)

taskkill /pid %APP_PID% /f >nul
if errorlevel 1 (
  echo Failed to stop process %APP_PID%.
  exit /b 1
)

del /q "%PID_FILE%" >nul 2>nul
echo Project stopped. PID=%APP_PID%
exit /b 0
