package org.afbb.tradingcenter.database;

import org.afbb.tradingcenter.objects.Card;
import org.afbb.tradingcenter.objects.MonsterCard;
import org.afbb.tradingcenter.objects.dto.sets.CardDTO;
import org.afbb.tradingcenter.objects.dto.sets.FilteredCardsDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CardService {
    private CardRepository cardRepository;

    public CardService() {

    }

    public FilteredCardsDTO getCards(String searchTerm, int page, int pageSize) throws SQLException {
        int firstResult = page * pageSize;

        try {
          this.cardRepository = new CardRepository();
          List<MonsterCard> monsterCards = cardRepository.getCardsBySearchTerm(searchTerm, firstResult, pageSize);
          List<CardDTO> dtoCards = CardMapper.mapToDtoList(monsterCards);
          return new FilteredCardsDTO(dtoCards, dtoCards.size());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
