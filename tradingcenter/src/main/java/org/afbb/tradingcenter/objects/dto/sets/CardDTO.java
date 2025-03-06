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
               Double prices) {

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
    }
}
