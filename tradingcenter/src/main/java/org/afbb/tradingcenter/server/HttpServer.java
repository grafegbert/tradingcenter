package org.afbb.tradingcenter.server;

import lombok.NoArgsConstructor;

import java.io.IOException;
import java.net.InetSocketAddress;

@NoArgsConstructor
public class HttpServer {
  private com.sun.net.httpserver.HttpServer httpServer;

  public void setUpServer() throws IOException {
    this.httpServer = com.sun.net.httpserver.HttpServer.create(new InetSocketAddress(8080),0);
    httpServer.createContext("/", new Handler());
  }

  public void start(){
    httpServer.start();
    System.out.println("Server gestartet");
  }

  public void stop(){
    httpServer.stop(0);
  }
}
