package org.afbb.tradingcenter.objects.arrays;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class CardPrices extends CardDataArray {
    private final Double cardMarketPrice;
    private final Double tcgPlayerPrice;
    private final Double ebayPrice;
    private final Double amazonPrice;
    private final Double coolStuffIncPrice;

    public CardPrices(Double cardMarketPrice, Double tcgPlayerPrice, Double ebayPrice, Double amazonPrice, Double coolStuffIncPrice) {
        super();
        this.cardMarketPrice = cardMarketPrice;
        this.tcgPlayerPrice = tcgPlayerPrice;
        this.ebayPrice = ebayPrice;
        this.amazonPrice = amazonPrice;
        this.coolStuffIncPrice = coolStuffIncPrice;
    }
}
