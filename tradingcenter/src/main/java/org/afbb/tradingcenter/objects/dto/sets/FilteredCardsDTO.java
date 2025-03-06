package org.afbb.tradingcenter.objects.dto.sets;

import java.util.List;

public class FilteredCardsDTO {
    List<CardDTO> cardsList;
    int totalAmount;

    public FilteredCardsDTO(List<CardDTO> cardsList, int totalAmount){
        this.cardsList = cardsList;
        this.totalAmount = totalAmount;
    }
}
