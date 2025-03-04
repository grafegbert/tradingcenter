!!! Backup-Skript mit XCOPY für Windows !!!

Dieses Skript kopiert alle Dateien und Unterordner aus einem bestimmten Quellverzeichnis auf einen USB-Stick. Es nutzt den Befehl xcopy, um eine vollständige Sicherung durchzuführen.

Installation & Einrichtung
1. Speichern des Skripts:
2. Erstelle eine neue Textdatei und benenne sie z. B. backup.bat.
3. Kopiere den Code in die Datei
4. Pfad anpassen
	- Ersetze C:\Pfad\zum\Ordner mit dem Ordner, den du sichern möchtest.
	- Ersetze E:\Backup mit dem Laufwerksbuchstaben deines USB-Sticks (z. B. F:\Backup).
5. Speichern & Ausführen
	- Speichere die Datei mit der Endung .bat.
	- Doppelklicke die Datei, um das Backup zu starten.

Automatisierung (Optional|Empfohlen)
- Öffne die Taskplaner-App (taskschd.msc).
- Erstelle eine neue Aufgabe und wähle "Einfacher Trigger" (z. B. täglich um 18:00 Uhr).
- Wähle "Programm starten" und gib den Pfad zur backup.bat-Datei an.
- Speichern & Fertig!

Fehlerbehebung
- Falls der USB-Stick nicht erkannt wird, prüfe den richtigen Laufwerksbuchstaben.
- Falls Dateien fehlen, überprüfe Berechtigungen für den Quellordner.
