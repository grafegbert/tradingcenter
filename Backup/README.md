# Backup-Skript mit XCOPY für Windows  

## 📌 Übersicht  
Dieses Skript kopiert alle Dateien und Unterordner aus einem bestimmten Quellverzeichnis auf einen USB-Stick. Es nutzt den Befehl `xcopy`, um eine vollständige Sicherung durchzuführen.  

## 📂 Installation & Einrichtung  
1. **Speichern des Skripts:**  
   - Lade dir `backup.bat` herunter.  
2. **Pfad anpassen:**  
   - Ersetze `C:\Pfad\zum\Ordner` mit dem Ordner, den du sichern möchtest.  
   - Ersetze `E:\Backup` mit dem Laufwerksbuchstaben deines USB-Sticks (z. B. `F:\Backup`).  
3. **Speichern & Ausführen:**  
   - Speichere die Datei mit der Endung `.bat`.  
   - Doppelklicke die Datei, um das Backup zu starten.  

## ⚙ Automatisierung (Optional)  
Falls du das Backup automatisch starten möchtest, kannst du eine geplante Aufgabe in Windows einrichten:  
1. Öffne die **Taskplaner**-App (`taskschd.msc`).  
2. Erstelle eine neue Aufgabe und wähle **"Einfacher Trigger"** (z. B. täglich um 18:00 Uhr).  
3. Wähle **"Programm starten"** und gib den Pfad zur `backup.bat`-Datei an.  
4. Speichern & Fertig!  

## 🛠 Fehlerbehebung  
- Falls der USB-Stick nicht erkannt wird, prüfe den richtigen Laufwerksbuchstaben.  
- Falls Dateien fehlen, überprüfe Berechtigungen für den Quellordner.  
