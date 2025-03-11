package org.afbb.tradingcenter.database;

import org.afbb.tradingcenter.objects.MonsterCard;
import org.afbb.tradingcenter.objects.arrays.CardImages;
import org.afbb.tradingcenter.objects.arrays.CardPrices;
import org.afbb.tradingcenter.objects.dto.sets.CardDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CardMapper {
    public static List<CardDTO> mapToDtoList(List<MonsterCard> cards) {
        return cards.stream().map(CardMapper::mapToDto).collect(Collectors.toList());
    }

    private static CardDTO mapToDto(MonsterCard card) {
        String imageLinks = convertCardImageToString(card.getImageLinks());
        Double prices = convertCardMarketPricesToDouble(card.getPrices());

        return new CardDTO(
                card.getId(),
                card.getName(),
                card.getType(),
                card.getHumanReadableCardType(),
                card.getFrameType(),
                card.getDescription(),
                card.getRace(),
                card.getArchetype(),
                card.getYgoprodeckUrl(),
                imageLinks,
                prices,
                card.getAttack(),
                card.getDefense(),
                card.getLevel(),
                card.getAttribute()
        );
    }

    private static String convertCardImageToString(CardImages cardImages) {
        if (cardImages != null && !cardImages.getImageUrl().isEmpty()) {
            return cardImages.getImageUrl();
        }
        return null;
    }

    // Umwandlung der CardPrices zu einem Double (Beispiel: der erste Preis)
    private static Double convertCardMarketPricesToDouble(CardPrices cardPrices) {
        if (cardPrices != null) {
            return cardPrices.getCardMarketPrice();
        }
        return null;
    }
}
