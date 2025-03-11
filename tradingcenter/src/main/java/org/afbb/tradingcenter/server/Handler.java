package org.afbb.tradingcenter.server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.extern.slf4j.Slf4j;
import org.afbb.tradingcenter.database.CardService;
import org.afbb.tradingcenter.objects.Card;

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
  private static final String HTTP_OPTIONS = "OPTIONS";
  private static final Charset charset = StandardCharsets.UTF_8;
  private String responseBody;
  private int statusCode = 200;

  @Override
  public void handle(HttpExchange httpExchange) throws IOException {
    String httpMethod = httpExchange.getRequestMethod();

    if (HTTP_GET.equals(httpMethod)) {
      log.debug("GET REQUEST");
      Headers requestHeaders = httpExchange.getRequestHeaders();
      Headers responseHeaders = new Headers();
      responseHeaders.add("Access-Control-Allow-Origin", "*");
      responseHeaders.put("Access-Control-Allow-Headers", List.of("*", "authorization"));
      responseHeaders.add("Content-Type", "application/json");

      String filter;
      Integer pageSize;
      Integer page;

      try {
        filter = requestHeaders.get("filter").get(0);
        pageSize = Integer.parseInt(requestHeaders.get("pagesize").get(0));
        page = Integer.parseInt(requestHeaders.get("pageindex").get(0));

      } catch (IndexOutOfBoundsException e){
        log.warn("Request headers not as expected", e);
        statusCode = 404;
        return;
      }

      try {
        responseBody = buildResponseBody(new CardService().getCards(filter, page, pageSize));

      } catch (SQLException e) {
        log.warn("database related problem occurred", e);
        statusCode = 500;
      }

      byte[] bytes = responseBody.getBytes(StandardCharsets.UTF_8);

      try {
        httpExchange.sendResponseHeaders(statusCode, bytes.length);

      } catch (IOException e) {
        log.warn("Response headers could not be sent", e);
      }

    } else if (HTTP_OPTIONS.equals(httpMethod)) {
      log.debug("OPTIONS REQUEST");
      Headers optionHeaders = new Headers();
      optionHeaders.add("Access-Control-Allow-Methods", HTTP_GET + "," + HTTP_OPTIONS);

      httpExchange.getResponseHeaders().putAll(optionHeaders);

      // if responseLength is set to -1 then no Body will be sent
      httpExchange.sendResponseHeaders(204, -1);  //TODO könnte noch verallgemeinert werden
      sendResponse(httpExchange, null);

    } else {
      log.error("NOT SUPPORTED HTTP METHOD!");

      // if responseLength is set to -1 then no Body will be sent
      httpExchange.sendResponseHeaders(405, -1);
      sendResponse(httpExchange, null);
    }
  }

  private void sendResponse(HttpExchange httpExchange, String responseBody) {
    try (Writer writer = new OutputStreamWriter(httpExchange.getResponseBody(), charset)){
      writer.write(responseBody);

    } catch(IOException e){
      log.warn("Response could not be sent", e);

    } finally {
      httpExchange.close();
    }
  }

  private String buildResponseBody(List<Card> cards) {
    StringBuilder builder = new StringBuilder();
    int totalAmount = cards.size();

    builder.append("{\"totalamount\": ");
    builder.append(totalAmount);
    builder.append(",");
    builder.append("\"MonsterCards\": [");

    for (int index = 0; index < totalAmount; index++) {
      builder.append(cards.get(index));

      if (index -1 != totalAmount) {
        builder.append(",");
      }
    }

    builder.append("]}");
    return builder.toString();
  }
}
