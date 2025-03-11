package org.afbb.tradingcenter.database;

import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
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
        Connection conn = DriverManager.getConnection("jdbc:mariadb://31.22.4.234:3306", "yudooalr_TC_App_Adm", "6v#Pzb_kQ+,8");
        DSLContext dslContext = DSL.using(conn, SQLDialect.MARIADB);

          return dslContext;

      } catch(SQLException e){
          System.out.println("Could not connect to DB");
          e.printStackTrace();
          return null;
        }
      }
    }
