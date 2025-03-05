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
6. Automatisierung
	- Öffne die Taskplaner-App (taskschd.msc).
	- Erstelle eine neue Aufgabe und wähle "Einfacher Trigger" (z. B. täglich um 18:00 Uhr).
	- Wähle "Programm starten" und gib den Pfad zur backup.bat-Datei an.
	- Speichern & Fertig!
