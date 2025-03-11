package org.afbb.tradingcenter.database;

import org.afbb.tradingcenter.objects.Card;
import org.afbb.tradingcenter.objects.arrays.CardImages;
import org.afbb.tradingcenter.objects.arrays.CardPrices;
import org.jooq.DSLContext;
import org.jooq.Result;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.field;

public class CardRepository {

  private final DSLContext dslContext;

  public CardRepository() throws SQLException {
    this.dslContext = Connector.getInstance();
  }

  public List<Card> getCardsBySearchTerm(String searchTerm, int firstResult, int pageSize) throws SQLException {
    /*
    Is not implemented because in frontend only monster cards are implemented

    Result<org.jooq.Record> cardResult = dslContext.select()
      .from("card")
      .where(field("name").like("%" + searchTerm + "%"))
      .limit(pageSize)
      .offset(firstResult)
      .fetch();
     */

    Result<org.jooq.Record> monsterCardResult = dslContext.select()
      .from("monster_cards")
      .where(field("name").like("%" + searchTerm + "%"))
      .limit(pageSize)
      .offset(firstResult)
      .fetch();

    return monsterCardResult.stream()
      .map(record -> new Card(
        record.getValue("id", Integer.class),
        record.getValue("name", String.class),
        record.getValue("type", String.class),
        record.getValue("human_readable_card_type", String.class),
        record.getValue("frame_type", String.class),
        record.getValue("description", String.class),
        record.getValue("race", String.class),
        record.getValue("archetype", String.class),
        record.getValue("ygoprodeck_url", String.class),
        null,
        getImageLink(record.getValue("image_card_id", Integer.class)),
        getCardPrices(record.getValue("prices_id", Integer.class))))
      .collect(Collectors.toList());
  }

  private CardImages getImageLink(Integer cardId) {
    Result<org.jooq.Record> result = dslContext.select()
      .from("card_images")
      .where(field("id").like(String.valueOf(cardId))).fetch();

    return result.get(0)
      .map(record -> new CardImages(
        cardId,
        record.getValue("image_url", String.class),
        null,
        null
      ));
  }

  private CardPrices getCardPrices(Integer cardId) {
    Result<org.jooq.Record> result = dslContext.select()
      .from("card_images")
      .where(field("id").like(String.valueOf(cardId))).fetch();

    return result.get(0)
      .map(record -> new CardPrices(
        cardId,
        record.getValue("card_market_price", Double.class),
        null,
        null,
        null,
        null
      ));
  }
}

