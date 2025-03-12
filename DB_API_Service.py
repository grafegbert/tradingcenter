import mysql.connector
import requests
from datetime import datetime
from mysql.connector import Error

# API-URL
API_URL = "https://db.ygoprodeck.com/api/v7/cardinfo.php"

# Datenbankverbindung herstellen
def get_db_connection():
    try:
        return mysql.connector.connect(
            host="127.0.0.1",
            user="API_Check",
            password="",
            database=""
        )
    except Error as e:
        print(f"Fehler bei der Datenbankverbindung: {e}")
        exit(1)

# Hilfsfunktion zum Abrufen oder Einfügen von Datensätzen
def get_or_insert(db, cursor, table, column, value):
    try:
        query = f"SELECT id FROM `{table}` WHERE `{column}` = %s"
        cursor.execute(query, (value,))
        result = cursor.fetchone()  # Nur ein einzelnes Ergebnis abrufen
        if result:
            return result[0]  # ID zurückgeben, wenn gefunden
        else:
            query = f"INSERT INTO `{table}` (`{column}`) VALUES (%s)"
            cursor.execute(query, (value,))
            db.commit()
            return cursor.lastrowid  # Die ID des neu eingefügten Datensatzes zurückgeben
    except Error as e:
        print(f"Fehler in get_or_insert: {e}")
        return None

# Funktion zum Abrufen oder Einfügen eines Sets basierend auf dem Code
def get_set_by_code_or_insert(db, cursor, set_name, set_code, set_rarity, set_rarity_code):
    try:
        query = "SELECT id FROM sets WHERE code = %s OR name = %s"
        cursor.execute(query, (set_code, set_name))
        result = cursor.fetchone()  # Nur ein einzelnes Ergebnis abrufen
        if result:
            return result[0]  # ID zurückgeben, wenn gefunden
        else:
            query = """
                INSERT INTO sets (name, code, rarity, rarity_code, modified_at)
                VALUES (%s, %s, %s, %s, %s)
            """
            cursor.execute(query, (set_name, set_code, set_rarity, set_rarity_code, datetime.now()))
            db.commit()
            return cursor.lastrowid  # Die ID des neu eingefügten Datensatzes zurückgeben
    except Error as e:
        print(f"Fehler in get_set_by_code_or_insert: {e}")
        return None

# Hauptfunktion zur Synchronisierung
def sync_data():
    try:
        response = requests.get(API_URL)
        response.raise_for_status()
        data = response.json().get("data", [])
        print(f"{len(data)} Karten aus der API geladen.")

        db = get_db_connection()
        cursor = db.cursor()

        for card in data:
            card_id = card.get("id")
            name = card.get("name")
            card_type = card.get("type")
            description = card.get("desc")
            race_name = card.get("race")
            archetype_name = card.get("archetype")
            frame_type = card.get("frameType")
            ygoprodeck_url = card.get("card_images", [{}])[0].get("image_url", "")

            # Rasse und Archetyp verarbeiten
            race_id = get_or_insert(db, cursor, "races", "name", race_name) if race_name else None
            if race_id is None and race_name:  # Fehlerbehandlung
                print(f"Fehler beim Einfügen/Abrufen der Rasse: {race_name}")

            archetype_id = get_or_insert(db, cursor, "archetypes", "name", archetype_name) if archetype_name else None
            if archetype_id is None and archetype_name:  # Fehlerbehandlung
                print(f"Fehler beim Einfügen/Abrufen des Archetyps: {archetype_name}")

            # Kartenbilder speichern
            images = card.get("card_images", [])
            image_links_id = None
            if images:
                image_url = images[0].get("image_url")
                image_url_small = images[0].get("image_url_small")
                image_url_cropped = images[0].get("image_url_cropped")

                cursor.execute("""
                    INSERT INTO card_images (image_url, image_url_small, image_url_cropped, created_at, modified_at)
                    VALUES (%s, %s, %s, %s, %s)
                    ON DUPLICATE KEY UPDATE
                    image_url=%s, image_url_small=%s, image_url_cropped=%s, modified_at=%s
                """, (image_url, image_url_small, image_url_cropped, datetime.now(), datetime.now(),
                      image_url, image_url_small, image_url_cropped, datetime.now()))
                db.commit()
                image_links_id = cursor.lastrowid

            # Preise speichern
            prices = card.get("card_prices", [{}])[0]
            prices_id = None
            if prices:
                cursor.execute("""
                    INSERT INTO card_prices (card_market_price, tcg_player_price, ebay_price, amazon_price, cool_stuff_inc_price, created_at, modified_at)
                    VALUES (%s, %s, %s, %s, %s, %s, %s)
                    ON DUPLICATE KEY UPDATE
                    card_market_price=%s, tcg_player_price=%s, ebay_price=%s, amazon_price=%s, cool_stuff_inc_price=%s, modified_at=%s
                """, (
                    prices.get("cardmarket_price"), prices.get("tcgplayer_price"), prices.get("ebay_price"),
                    prices.get("amazon_price"), prices.get("coolstuffinc_price"), datetime.now(), datetime.now(),
                    prices.get("cardmarket_price"), prices.get("tcgplayer_price"), prices.get("ebay_price"),
                    prices.get("amazon_price"), prices.get("coolstuffinc_price"), datetime.now()))
                db.commit()
                prices_id = cursor.lastrowid

            # Hauptkarte speichern
            cursor.execute("""
                INSERT INTO cards (id, name, type, human_readable_card_type, frame_type, description, race_id, archetype_id, ygoprodeck_url, image_links_id, prices_id, created_at, modified_at)
                VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
                ON DUPLICATE KEY UPDATE
                name=%s, type=%s, human_readable_card_type=%s, frame_type=%s, description=%s, race_id=%s, archetype_id=%s, ygoprodeck_url=%s, image_links_id=%s, prices_id=%s, modified_at=%s
            """, (card_id, name, card_type, card_type, frame_type, description, race_id, archetype_id, ygoprodeck_url, image_links_id, prices_id, datetime.now(), datetime.now(),
                  name, card_type, card_type, frame_type, description, race_id, archetype_id, ygoprodeck_url, image_links_id, prices_id, datetime.now()))
            db.commit()

            # Sets speichern
            card_sets = card.get("card_sets", [])
            for card_set in card_sets:
                set_name = card_set.get("set_name")
                set_code = card_set.get("set_code")
                set_rarity = card_set.get("set_rarity")
                set_rarity_code = card_set.get("set_rarity_code")
                set_price = card_set.get("set_price")

                set_id = get_set_by_code_or_insert(db, cursor, set_name, set_code, set_rarity, set_rarity_code)
                if set_id is None:
                    print(f"Fehler beim Einfügen/Abrufen des Sets: {set_name}, {set_code}")

                if set_id:
                    cursor.execute("""
                        INSERT INTO card_sets (card_id, set_id, price, created_at, modified_at)
                        VALUES (%s, %s, %s, %s, %s)
                        ON DUPLICATE KEY UPDATE price=%s, modified_at=%s
                    """, (card_id, set_id, set_price, datetime.now(), datetime.now(), set_price, datetime.now()))
                    db.commit()

            # Monsterkarten speichern, wenn zutreffend
            if "Monster" in card_type:
                cursor.execute("""
                    INSERT INTO monster_cards (id, attack, defense, level, attribute, created_at, modified_at)
                    VALUES (%s, %s, %s, %s, %s, %s, %s)
                    ON DUPLICATE KEY UPDATE attack=%s, defense=%s, level=%s, attribute=%s, modified_at=%s
                """, (card_id, card.get("atk"), card.get("def"), card.get("level"), card.get("attribute"), datetime.now(), datetime.now(),
                      card.get("atk"), card.get("def"), card.get("level"), card.get("attribute"), datetime.now()))
                db.commit()

        print("Synchronisierung erfolgreich abgeschlossen.")

    except requests.exceptions.RequestException as e:
        print(f"Fehler beim Abrufen der API: {e}")
    except Error as e:
        print(f"Datenbankfehler: {e}")
    finally:
        if 'cursor' in locals():
            cursor.close()
        if 'db' in locals():
            db.close()

if __name__ == "__main__":
    sync_data()
