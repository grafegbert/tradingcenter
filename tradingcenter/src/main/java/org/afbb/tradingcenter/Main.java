package org.afbb.tradingcenter;

import org.afbb.tradingcenter.server.JettyServer;

public class Main {
    public static void main(String[] args) {
        JettyServer server = new JettyServer(8080);
        try {
            server.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}