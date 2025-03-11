package org.afbb.tradingcenter.objects.dto.sets;

import lombok.Getter;

import javax.persistence.Entity;

@Getter
@Entity
public class CardDTO {
    private final Integer id;
    private final String name;
    private final String type;
    private final String humanReadableCardType;
    private final String frameType;
    private final String description;
    private final String race;
    private final String archetype;
    private final String ygoprodeckUrl;
    private final String imageLinks;
    private final Double prices;
    private final Integer attack;
    private final Integer defense;
    private final Integer level;
    private final String attribute;

public CardDTO(Integer id,
               String name,
               String type,
               String humanReadableCardType,
               String frameType,
               String description,
               String race,
               String archetype,
               String ygoprodeckUrl,
               String imageLinks,
               Double prices,
               Integer attack,
               Integer defense,
               Integer level,
               String attribute) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.humanReadableCardType = humanReadableCardType;
        this.frameType = frameType;
        this.description = description;
        this.race = race;
        this.archetype = archetype;
        this.ygoprodeckUrl = ygoprodeckUrl;
        this.imageLinks = imageLinks;
        this.prices = prices;
        this.attack = attack;
        this.defense = defense;
        this.level = level;
        this.attribute = attribute;
  }
  public String toJsonString() {
    return "{" +
      "\"id\":" + id + "," +
      "\"name\":\"" + name + "\"," +
      "\"type\":\"" + type + "\"," +
      "\"humanReadableCardType\":\"" + humanReadableCardType + "\"," +
      "\"frameType\":\"" + frameType + "\"," +
      "\"description\":\"" + description + "\"," +
      "\"race\":\"" + race + "\"," +
      "\"archetype\":\"" + archetype + "\"," +
      "\"ygoprodeckUrl\":\"" + ygoprodeckUrl + "\"," +
      "\"imageLinks\":\"" + imageLinks + "\"," +
      "\"prices\":" + prices + "," +
      "\"attack\":" + attack + "," +
      "\"defense\":" + defense + "," +
      "\"level\":" + level + "," +
      "\"attribute\":\"" + attribute + "\"" +
      "}";
  }
}
