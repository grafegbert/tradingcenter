package org.afbb.tradingcenter.objects.dto.sets;

import java.util.List;

public class MonsterCardDTO extends CardDTO{
    private Integer attack;
    private Integer defense;
    private Integer level;
    private String attribute;

    public MonsterCardDTO(  Integer id,
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

        super(id, name, type, humanReadableCardType, frameType, description, race, archetype, ygoprodeckUrl, imageLinks, prices);
        this.attack = attack;
        this.defense = defense;
        this.level = level;
        this.attribute = attribute;
    }
}
