package org.afbb.tradingcenter.server;

import org.glassfish.jersey.server.ResourceConfig;

public class ServerConfig extends ResourceConfig{
    public ServerConfig() {
        packages("org.afbb.tradingcenter.controller");
    }
}
