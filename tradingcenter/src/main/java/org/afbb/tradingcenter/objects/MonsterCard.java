package org.afbb.tradingcenter.objects;

import org.afbb.tradingcenter.objects.arrays.CardImages;
import org.afbb.tradingcenter.objects.arrays.CardPrices;
import org.afbb.tradingcenter.objects.arrays.CardSet;
import lombok.Getter;

import javax.persistence.Entity;
import java.util.List;

@Getter
@Entity
public class MonsterCard extends Card {
    private Integer attack;
    private Integer defense;
    private Integer level;
    private String attribute;

    public MonsterCard(Integer id,
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
        CardPrices prices,
        Integer attack,
        Integer defense,
        Integer level,
        String attribute) {

        super(id, name, type, humanReadableCardType, frameType, description, race, archetype, ygoprodeckUrl, sets, imageLinks, prices);
        this.attack = attack;
        this.defense = defense;
        this.level = level;
        this.attribute = attribute;
    }
}
