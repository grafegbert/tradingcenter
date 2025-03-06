package org.afbb.tradingcenter.database;

import org.afbb.tradingcenter.objects.Card;
import org.afbb.tradingcenter.objects.MonsterCard;
import org.afbb.tradingcenter.objects.arrays.CardImages;
import org.afbb.tradingcenter.objects.arrays.CardPrices;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private static SessionFactory factory ;

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private DatabaseConnection() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Card.class)
                .addAnnotatedClass(MonsterCard.class)
                .addAnnotatedClass(CardImages.class)
                .addAnnotatedClass(CardPrices.class)
                .buildSessionFactory();
    }

    public Session getSession(){
        return factory.openSession();
    }
}
