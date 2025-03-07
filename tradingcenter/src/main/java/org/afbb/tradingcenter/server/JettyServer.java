package org.afbb.tradingcenter.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.server.ResourceConfig;

public class JettyServer {
    private final Server server;

    public JettyServer(int port) {
        server = new Server(port);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        ResourceConfig config = new ServerConfig();
        ServletContainer servletContainer = new ServletContainer(config);

        context.addServlet(new org.eclipse.jetty.servlet.ServletHolder(servletContainer), "/api/*");
    }

    public void start() throws Exception {
        server.start();
        server.join();
    }
}