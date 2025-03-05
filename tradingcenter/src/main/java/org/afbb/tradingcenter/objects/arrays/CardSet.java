package org.afbb.tradingcenter.objects.arrays;

import lombok.Getter;

@Getter
public class CardSet extends CardDataArray {
    private String name;
    private String code;
    private String rarity;
    private String rarityCode;
    private Double price;

    public CardSet(String name, String code, String rarity, String rarityCode, Double price) {
        super();
        this.name = name;
        this.code = code;
        this.rarity = rarity;
        this.rarityCode = rarityCode;
        this.price = price;
    }
}
