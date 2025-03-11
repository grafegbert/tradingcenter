package org.afbb.tradingcenter.objects.dto.sets;

import lombok.Getter;

import java.util.List;

@Getter
public class FilteredCardsDTO {
    List<CardDTO> cardsList;
    int totalAmount;

    public FilteredCardsDTO(List<CardDTO> cardsList, int totalAmount){
        this.cardsList = cardsList;
        this.totalAmount = totalAmount;
    }
}
