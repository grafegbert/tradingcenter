package org.afbb.tradingcenter.database;

import org.afbb.tradingcenter.objects.Card;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CardService {
    private final CardRepository cardRepository;

    public CardService() throws SQLException {
        this.cardRepository = new CardRepository();
    }

    public List<Card> getCards(String searchTerm, int page, int pageSize) {
        int firstResult = (page - 1) * pageSize;
        try {
            if (searchTerm.isEmpty()){
                return cardRepository.getCardsBySearchTerm( searchTerm, firstResult, pageSize);
            }
            return cardRepository.getCardsBySearchTerm(searchTerm, firstResult, pageSize);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
