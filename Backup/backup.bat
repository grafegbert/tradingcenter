@echo off
set QUELLORDNER=C:\Pfad\zum\Ordner
set ZIELORDNER=E:\Backup

echo Backup wird gestartet...
xcopy "%QUELLORDNER%" "%ZIELORDNER%" /E /H /C /I /Y
echo Backup abgeschlossen.

pause