package org.afbb.tradingcenter;

import org.afbb.tradingcenter.server.HttpServer;

public class Main {
    public static void main(String[] args) {
      HttpServer server = new HttpServer();
      try {
        server.setUpServer();
        server.start();
      } catch (Exception e) {
        System.out.println("Server error");
        server.stop();
      }
  }
}
