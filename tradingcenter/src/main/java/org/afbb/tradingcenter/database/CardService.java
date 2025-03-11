package org.afbb.tradingcenter.database;

import org.afbb.tradingcenter.objects.Card;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CardService {
    private CardRepository cardRepository;

    public CardService() {

    }

    public List<Card> getCards(String searchTerm, int page, int pageSize) throws SQLException {
        int firstResult = page * pageSize;

        try {
          this.cardRepository = new CardRepository();
            return cardRepository.getCardsBySearchTerm(searchTerm, firstResult, pageSize);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
