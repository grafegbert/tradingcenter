package org.afbb.tradingcenter.database;

import org.afbb.tradingcenter.objects.Card;
import org.afbb.tradingcenter.objects.MonsterCard;
import org.afbb.tradingcenter.objects.arrays.CardImages;
import org.afbb.tradingcenter.objects.arrays.CardPrices;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.impl.DSL;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;

public class CardRepository {

  private final DSLContext dslContext;

  public CardRepository() throws SQLException {
    this.dslContext = new Connector().getInstance();
  }

  public List<MonsterCard> getCardsBySearchTerm(String searchTerm, int firstResult, int pageSize) throws SQLException {
    System.out.println("DB conn established");
    /*
    Is not implemented because in frontend only monster cards are implemented

    Result<org.jooq.Record> cardResult = dslContext.select()
      .from("card")
      .where(field("name").like("%" + searchTerm + "%"))
      .limit(pageSize)
      .offset(firstResult)
      .fetch();
     */

    /*Result<org.jooq.Record> monsterCardResult = dslContext.select()
      .from("monster_cards")
      .where(field("name").like("%" + searchTerm + "%"))
      .limit(pageSize)
      .offset(firstResult)
      .fetch();*/

    /*Result<org.jooq.Record> monsterCardResult = dslContext.select()
      .from("monster_cards")
      .join(table("cards"))
      .on(field("monster_cards.id").eq(field("cards.id")))
      .join(table("races"))
      .on(field("monster_cards.id").eq(field("races.id")))
      .where(field("cards.name").like("%" + searchTerm + "%"))
      .orderBy(field("monster_cards.id").asc())
      .limit(DSL.inline(pageSize), DSL.inline(firstResult))
      .fetch();*/

    Result<org.jooq.Record> monsterCardResult;

    if(searchTerm.isEmpty()){
      monsterCardResult = dslContext
        .select(
          DSL.asterisk(),
          field("r.name").as("race_name"),
          field("c.name").as("card_name"),
          field("a.name").as("archetype_name")
        )
        .from(table("monster_cards").as("mc"))
        .join(table("cards").as("c"))
        .on(field("mc.id").eq(field("c.id")))
        .join(table("races").as("r"))
        .on(field("c.race_id").eq(field("r.id")))
        .join(table("archetypes").as("a"))
        .on(field("c.archetype_id").eq(field("a.id")))
        //.where(field("c.name").like("%" + searchTerm + "%"))
        .orderBy(field("mc.id").asc())
        .limit(pageSize)
        .offset(firstResult)
        .fetch();
    } else {
       monsterCardResult = dslContext
        .select(
          DSL.asterisk(),
          field("r.name").as("race_name"),
          field("c.name").as("card_name"),
          field("a.name").as("archetype_name")
        )
        .from(table("monster_cards").as("mc"))
        .join(table("cards").as("c"))
        .on(field("mc.id").eq(field("c.id")))
        .join(table("races").as("r"))
        .on(field("c.race_id").eq(field("r.id")))
        .join(table("archetypes").as("a"))
        .on(field("c.archetype_id").eq(field("a.id")))
        .where(field("c.name").like("%" + searchTerm + "%"))
        .orderBy(field("mc.id").asc())
        .limit(pageSize)
        .offset(firstResult)
        .fetch();
    }

    System.out.println("Query successfull");

    // Mappen der MonsterCards zu CardDTOs
    return monsterCardResult.stream()
      .map(record -> new MonsterCard(
        record.getValue("id", Integer.class),
        record.getValue("card_name", String.class),
        record.getValue("type", String.class),
        record.getValue("human_readable_card_type", String.class),
        record.getValue("frame_type", String.class),
        getCleanStrings(
          record.getValue("description", String.class)),
        record.getValue("race_name", String.class),
        record.getValue("archetype_name", String.class),
        record.getValue("ygoprodeck_url", String.class),
        null,
        getImageLink(record.getValue("image_links_id", Integer.class)),
        getCardPrices(record.getValue("prices_id", Integer.class)),
        record.getValue("attack", Integer.class),
        record.getValue("defense", Integer.class),
        record.getValue("level", Integer.class),
        record.getValue("attribute", String.class)
      ))
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
      .from("card_prices")
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

  public int getTotalAmount(String searchTerm) {
    int totalCount;
    if (searchTerm.isEmpty()) {
      totalCount = dslContext
        .selectCount()
        .from(table("monster_cards").as("mc"))
        .join(table("cards").as("c"))
        .on(field("mc.id").eq(field("c.id")))
        .join(table("races").as("r"))
        .on(field("c.race_id").eq(field("r.id")))
        .join(table("archetypes").as("a"))
        .on(field("c.archetype_id").eq(field("a.id")))
        .fetchOneInto(int.class);
        ;
    } else {
      totalCount = dslContext
        .selectCount()
        .from(table("monster_cards").as("mc"))
        .join(table("cards").as("c"))
        .on(field("mc.id").eq(field("c.id")))
        .join(table("races").as("r"))
        .on(field("c.race_id").eq(field("r.id")))
        .join(table("archetypes").as("a"))
        .on(field("c.archetype_id").eq(field("a.id")))
        .where(field("c.name").like("%" + searchTerm + "%"))
        .fetchOneInto(int.class);
    }
    return totalCount;
  }

  private String getCleanStrings(String input){
    //return input.replaceAll("\"", "'");
    return"";
  }
}

