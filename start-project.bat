@echo off
setlocal

set "ROOT=%~sdp0"
set "JAVA_HOME=D:\jdk8"
set "PATH=%JAVA_HOME%\bin;%PATH%"
set "JAR=%ROOT%target\zhunong79djy629-0.0.1-SNAPSHOT.jar"
set "PID_FILE=%ROOT%target\app.pid"
set "OUT_LOG=%ROOT%target\app-stdout.log"
set "ERR_LOG=%ROOT%target\app-stderr.log"

if not exist "%JAVA_HOME%\bin\java.exe" (
  echo JDK8 not found: %JAVA_HOME%
  exit /b 1
)

if exist "%PID_FILE%" (
  for /f "usebackq delims=" %%i in ("%PID_FILE%") do set "APP_PID=%%i"
  if defined APP_PID (
    tasklist /fi "PID eq %APP_PID%" | findstr /r " %APP_PID% " >nul
    if not errorlevel 1 (
      echo Project is already running. PID=%APP_PID%
      echo Admin: http://localhost:8014/zhunong79djy629/admin/dist/index.html
      echo Front: http://localhost:8014/zhunong79djy629/front/dist/index.html
      exit /b 0
    )
  )
)

if not exist "%ROOT%target" mkdir "%ROOT%target"

echo Building project with JDK8...
call mvn -DskipTests clean package
if errorlevel 1 (
  echo Build failed.
  exit /b 1
)

if not exist "%JAR%" (
  echo Jar not found: %JAR%
  exit /b 1
)

echo Starting project...
powershell -NoProfile -ExecutionPolicy Bypass -Command "$p = Start-Process -FilePath '%JAVA_HOME%\bin\java.exe' -ArgumentList '-jar','%JAR%' -WorkingDirectory '%ROOT%' -RedirectStandardOutput '%OUT_LOG%' -RedirectStandardError '%ERR_LOG%' -PassThru; $p.Id | Set-Content -LiteralPath '%PID_FILE%'; Start-Sleep -Seconds 8; if ($p.HasExited) { exit 1 }"
if errorlevel 1 (
  echo Start failed. Check logs:
  echo   %OUT_LOG%
  echo   %ERR_LOG%
  exit /b 1
)

echo Project started.
for /f "usebackq delims=" %%i in ("%PID_FILE%") do echo PID=%%i
echo Admin: http://localhost:8014/zhunong79djy629/admin/dist/index.html
echo Front: http://localhost:8014/zhunong79djy629/front/dist/index.html
exit /b 0
