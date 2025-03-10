package org.afbb.tradingcenter.server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.extern.slf4j.Slf4j;
import org.afbb.tradingcenter.database.CardService;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

@Slf4j
public class Handler implements HttpHandler {
  private static final String HTTP_GET = "GET";
  private static final Charset charset = StandardCharsets.UTF_8;
  private String responseBody;

  @Override
  public void handle(HttpExchange httpExchange){
    String httpMethod = httpExchange.getRequestMethod();

    if (HTTP_GET.equals(httpMethod)) {
      Headers requestHeaders = httpExchange.getRequestHeaders();
      System.out.println(requestHeaders.toString());

      String filter;
      Integer pageSize;
      Integer page;

      try {
        filter = requestHeaders.get("filter").get(0);
        pageSize = Integer.parseInt(requestHeaders.get("pagesize").get(0));
        page = Integer.parseInt(requestHeaders.get("pageindex").get(0));
      } catch (IndexOutOfBoundsException e){
        log.warn("Request headers not as expected", e);
        return;
      }


      try {
        responseBody = new CardService().getCards(filter, page, pageSize).toString();
      } catch (SQLException e) {
        log.warn("database related problem occurred", e);
      }
      byte[] bytes = responseBody.getBytes(StandardCharsets.UTF_8);
      Headers responseHeaders = new Headers();
      responseHeaders.add("Access-Control-Allow-Origin", "*");
      responseHeaders.put("Access-Control-Allow-Headers", List.of("*", "authorization"));
      responseHeaders.add("Access-Control-Allow-Methods", "GET");
      responseHeaders.add("Content-Type", "application/json");

      try {
        httpExchange.sendResponseHeaders(200, bytes.length);
      } catch (IOException e) {
        log.warn("Response headers could not be sent", e);
      }

      try (Writer writer = new OutputStreamWriter(httpExchange.getResponseBody(), charset)){
        writer.write(responseBody);
      } catch(IOException e){
        log.warn("Response could not be sent", e);
      } finally {
        httpExchange.close();
      }

    } else{
      throw new RuntimeException();
    }
  }
}
