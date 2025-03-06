package org.afbb.tradingcenter.database;

import org.afbb.tradingcenter.objects.Card;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.sql.*;


public class CardRepository {
    private final Session databaseSession = DatabaseConnection.getInstance().getSession();

    public CardRepository() throws SQLException {
        databaseSession.beginTransaction();
    }

    public List<Card> getCardsBySearchTerm(String searchTerm, int firstResult, int pageSize) throws IOException {
        String sqlQueryFromFile = new String(Files.readAllBytes(
                Paths.get("src/main/java/org/afbb/tradingcenter/database/queries/select_cards_by_term.sql")
        ));

        Query<Card> query = databaseSession.createQuery(sqlQueryFromFile, Card.class);
        query.setParameter("search", "%" + searchTerm + "%");
        query.setFirstResult(firstResult);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    public List<Card> getAllCards(int firstResult, int pageSize) throws IOException {
        String sqlQueryFromFile = new String(Files.readAllBytes(Paths.get("src/main/java/org/afbb/tradingcenter/database/queries/select_all_cards.sql")));
        Query<Card> query = databaseSession.createQuery(sqlQueryFromFile, Card.class);
        query.setFirstResult(firstResult);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public int getCountCardsBySearchTerm(String searchTerm) throws IOException {
        String sqlQueryFromFile = new String(Files.readAllBytes(Paths.get("src/main/java/org/afbb/tradingcenter/database/queries/count_cards_by_term.sql")));
        Query<Long> countQuery = databaseSession.createQuery(sqlQueryFromFile, Long.class);
        countQuery.setParameter("search", "%" + searchTerm + "%");
        return countQuery.getSingleResult().intValue();
    }

    public int getCountCards() throws IOException {
        String sqlQueryFromFile = new String(Files.readAllBytes(Paths.get("src/main/java/org/afbb/tradingcenter/database/queries/count_all_cards.sql")));
        Query<Long> countQuery = databaseSession.createQuery(sqlQueryFromFile , Long.class);
        return countQuery.getSingleResult().intValue();
    }
}

