package org.afbb.tradingcenter.database;

import org.afbb.tradingcenter.objects.Card;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CardRepository {
    private Session databaseSession = DatabaseConnection.getInstance().getSession();
    private static List<Card> cardList = new ArrayList<>();

    public CardRepository() throws SQLException {
        databaseSession.beginTransaction();
    }

    public List<Card> getCardsByName(String name) throws SQLException {
        Card card = databaseSession.get(Card.class, name);
        return null; //TODO: add function to get multiple cards by name
    }
}


