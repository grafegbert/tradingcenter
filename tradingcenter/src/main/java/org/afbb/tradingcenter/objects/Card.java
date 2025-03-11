package org.afbb.tradingcenter.objects;

import org.afbb.tradingcenter.objects.arrays.CardImages;
import org.afbb.tradingcenter.objects.arrays.CardPrices;
import org.afbb.tradingcenter.objects.arrays.CardSet;
import lombok.Getter;

import java.util.List;

@Getter
public class Card {
    private final Integer id;
    private final String name;
    private final String type;
    private final String humanReadableCardType;
    private final String frameType;
    private final String description;
    private final String race;
    private final String archetype;
    private final String ygoprodeckUrl;
    private final List<CardSet> sets;
    private final CardImages imageLinks;
    private final CardPrices prices;

    public Card(       Integer id,
                       String name,
                       String type,
                       String humanReadableCardType,
                       String frameType,
                       String description,
                       String race,
                       String archetype,
                       String ygoprodeckUrl,
                       List<CardSet> sets,
                       CardImages imageLinks,
                       CardPrices prices) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.humanReadableCardType = humanReadableCardType;
        this.frameType = frameType;
        this.description = description;
        this.race = race;
        this.archetype = archetype;
        this.ygoprodeckUrl = ygoprodeckUrl;
        this.sets = sets;
        this.imageLinks = imageLinks;
        this.prices = prices;
    }

  @Override
  public String toString() {
    return "{" +
      "\"id\":" + id + "," +
      "\"name\":\"" + name + "\"," +
      "\"type\":\"" + type + "\"," +
      "\"humanReadableCardType\":\"" +humanReadableCardType + "\"," +
      "\"frameType\":\"" + frameType + "\"," +
      "\"description\":\"" + description + "\"," +
      "\"race\":\"" + race + "\"," +
      "\"archetype\":\"" + archetype + "\"," +
      "\"ygoprodeckUrl\":\"" + ygoprodeckUrl + "\"," +
      "\"imageLinksTest\":\"" + imageLinks.getImageUrl() + "\"," +
      "\"pricesTest\":\"" + prices.getCardMarketPrice() + "\"" +
      "}";
  }

}
