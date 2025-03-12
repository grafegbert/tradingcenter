package org.afbb.tradingcenter.database;

import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.ParamType;
import org.jooq.conf.Settings;
import org.jooq.conf.StatementType;
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

    public Connector() { }

    public DSLContext getInstance() {
      try {
       
        Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/mysql", "root", "root");
        Settings settings = new Settings();
        settings.setStatementType(StatementType.STATIC_STATEMENT);
        DSLContext dslContext = DSL.using(conn, SQLDialect.MARIADB, settings);

          return dslContext;

      } catch(SQLException e){
          System.out.println("Could not connect to DB");
          e.printStackTrace();
          return null;
        }
      }
    }
