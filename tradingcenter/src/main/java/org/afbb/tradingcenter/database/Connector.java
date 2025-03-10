package org.afbb.tradingcenter.database;

import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class Connector {
    private static DSLContext dslContext;
    private String dbUrl;   //TODO muss noch mit reingegeben werden
    private String dbUser;
    private String dbPass;

    private Connector() { }

    public static DSLContext getInstance() {
      if (dslContext == null) {
        try {
          Connection connection = DriverManager.getConnection("jdbc:mariadb:31.22.4.234/yudooalr_db_yugiooh", "yudooalr_TC_APP_Adm", "IJyLNGbSQ[x;");
          dslContext = DSL.using(connection);

        } catch (SQLException e) {
          log.error("Could not connect to Database", e);
        }
      }

      return dslContext;
    }
}
