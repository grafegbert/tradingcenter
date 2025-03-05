# Projekt: Yu-Gi-Oh! Tradingcenter
## Projektdokumentation
Die Projektdokumentation befindet sich in Google Docs.
Das Dokument lässt sich mit folgendem Link aufrufen: <https://docs.google.com/document/d/1ZxABB00cOMgvIFrtl8Kiy9vB3zweUhL91OzMy_G2Hy4/edit?usp=sharing>.
**BITTE NICHT DIE BRANCHES MERGEN!!!**


# Backup-Skript mit XCOPY für Windows

Dieses Skript kopiert alle Dateien und Unterordner aus einem bestimmten Quellverzeichnis auf einen USB-Stick. Es nutzt den Befehl xcopy, um eine vollständige Sicherung durchzuführen.

## Installation & Einrichtung
1. Speichern des Skripts:
2. Erstelle eine neue Textdatei und benenne sie z. B. backup.bat.
3. Kopiere den Code in die Datei
4. Pfad anpassen
	- Ersetze C:\Pfad\zum\Ordner mit dem Ordner, den du sichern möchtest.
	- Ersetze E:\Backup mit dem Laufwerksbuchstaben deines USB-Sticks (z. B. F:\Backup).
5. Speichern & Ausführen
	- Speichere die Datei mit der Endung .bat.
	- Doppelklicke die Datei, um das Backup zu starten.

## Automatisierung (Optional|Empfohlen)
- Öffne die Taskplaner-App (taskschd.msc).
- Erstelle eine neue Aufgabe und wähle "Einfacher Trigger" (z. B. täglich um 18:00 Uhr).
- Wähle "Programm starten" und gib den Pfad zur backup.bat-Datei an.
- Speichern & Fertig!

## Fehlerbehebung
- Falls der USB-Stick nicht erkannt wird, prüfe den richtigen Laufwerksbuchstaben.
- Falls Dateien fehlen, überprüfe Berechtigungen für den Quellordner.

## Erklärung der Parameter
- /E – Kopiert alle Unterverzeichnisse, auch leere.
- /H – Kopiert versteckte und Systemdateien.
- /C – Setzt die Kopie auch bei Fehlern fort.
- /I – Falls das Ziel nicht existiert, wird es als Verzeichnis behandelt.
- /Y – Überschreibt vorhandene Dateien ohne Nachfrage.
- Falls dein USB-Stick einen anderen Laufwerksbuchstaben hat, ersetze E: durch den korrekten Buchstaben.
