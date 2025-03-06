package org.afbb.tradingcenter.objects.arrays;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "card_prices")
public class CardPrices extends CardDataArray {
    @Id
    private final int id;
    private final Double cardMarketPrice;
    private final Double tcgPlayerPrice;
    private final Double ebayPrice;
    private final Double amazonPrice;
    private final Double coolStuffIncPrice;

    public CardPrices(int id, Double cardMarketPrice, Double tcgPlayerPrice, Double ebayPrice, Double amazonPrice, Double coolStuffIncPrice) {
        super();
        this.id = id;
        this.cardMarketPrice = cardMarketPrice;
        this.tcgPlayerPrice = tcgPlayerPrice;
        this.ebayPrice = ebayPrice;
        this.amazonPrice = amazonPrice;
        this.coolStuffIncPrice = coolStuffIncPrice;
    }
}
