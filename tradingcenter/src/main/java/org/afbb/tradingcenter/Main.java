package org.afbb.tradingcenter;

import org.afbb.tradingcenter.database.CardService;
import org.afbb.tradingcenter.objects.Card;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        //TODO: delete example code

        CardService cardService;
        try {
            cardService = new CardService();
            List<Card> cards = cardService.getCards("Dark Magician", 1, 100);
            for(Card o : cards){
                System.out.println(o.getName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}