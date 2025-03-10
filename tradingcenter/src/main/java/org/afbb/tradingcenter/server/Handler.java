package org.afbb.tradingcenter.server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Handler implements HttpHandler {
  private static final String HTTP_GET = "GET";
  private static final Charset charset = StandardCharsets.UTF_8;
  private String responseBody;

  @Override
  public void handle(HttpExchange httpExchange){
    String httpMethod = httpExchange.getRequestMethod();

    if (HTTP_GET.equals(httpMethod)) {
      Headers headers = httpExchange.getRequestHeaders();
      System.out.println(headers.toString());

      responseBody = "Funktioniert oder sowas";
      byte[] bytes = responseBody.getBytes(StandardCharsets.UTF_8);
      try {
        httpExchange.sendResponseHeaders(200, bytes.length);
      } catch (IOException e) {
        System.out.println("Blöd gelaufen");
      }

      try (Writer writer = new OutputStreamWriter(httpExchange.getResponseBody(), charset)){
        writer.write(responseBody);
      } catch(IOException e){
        System.out.println("Alles kacke");
      } finally {
        httpExchange.close();
      }

    } else{
      throw new RuntimeException();
    }
  }
}
