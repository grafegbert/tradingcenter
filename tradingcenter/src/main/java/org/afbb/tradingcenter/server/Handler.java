package org.afbb.tradingcenter.server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.extern.slf4j.Slf4j;
import org.afbb.tradingcenter.database.CardService;
import org.afbb.tradingcenter.objects.Card;
import org.afbb.tradingcenter.objects.arrays.CardImages;
import org.afbb.tradingcenter.objects.arrays.CardPrices;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Handler implements HttpHandler {
  private static final Card testCard = new Card(123, "card", "fd", "df", "sfd", "sdsd", "dfsdf", "d", "kjsdf", null, new CardImages(123, "https://images.ygoprodeck.com/images/cards/89631139.jpg", "https://images.ygoprodeck.com/images/cards/89631139.jpg", "https://images.ygoprodeck.com/images/cards/89631139.jpg"), new CardPrices(123, 12.00, 12.00, 12.00, 12.00, 12.00));
  private static final String HTTP_GET = "GET";
  private static final String HTTP_OPTIONS = "OPTIONS";
  private static final Charset charset = StandardCharsets.UTF_8;
  private String responseBody;
  private int statusCode = 200;

  @Override
  public void handle(HttpExchange httpExchange) throws IOException {
    String httpMethod = httpExchange.getRequestMethod();
    Headers responseHeaders = new Headers();
    responseHeaders.add("Access-Control-Allow-Origin", "*");
    responseHeaders.put("Access-Control-Allow-Headers", List.of("*", "authorization"));
    responseHeaders.add("Content-Type", "application/json");
    httpExchange.getResponseHeaders().putAll(responseHeaders);

    if (HTTP_GET.equals(httpMethod)) {
      System.out.println("GET REQUEST");

      String filter;
      Integer pageSize;
      Integer page;

      try {
        Map<String, String> params = queryToMap(httpExchange.getRequestURI().getQuery());
        System.out.println("FILTER: " + params.toString());
        filter = params.get("filter");
        pageSize = Integer.parseInt(params.get("pagesize"));
        page = Integer.parseInt(params.get("pageindex"));

      } catch (IndexOutOfBoundsException e){
        System.out.println("Request headers not as expected");
        statusCode = 404;
        return;
      }

      try {
        System.out.println("Starting to fetch data from db");
        //new CardService().getCards(filter, page, pageSize)
        responseBody = buildResponseBody(new CardService().getCards(filter, page, pageSize));
        System.out.println("RESPBODY: " + responseBody);

      } catch (SQLException e) {
        System.out.println("database related problem occurred");
        e.printStackTrace();
        statusCode = 500;
      }

      byte[] bytes = responseBody.getBytes(StandardCharsets.UTF_8);

      try {
        httpExchange.sendResponseHeaders(statusCode, bytes.length);

      } catch (IOException e) {
        System.out.println("Response headers could not be sent");
      }

    } else if (HTTP_OPTIONS.equals(httpMethod)) {
      System.out.println("OPTIONS REQUEST");
      Headers optionHeaders = new Headers();
      optionHeaders.add("Access-Control-Allow-Methods", HTTP_GET + "," + HTTP_OPTIONS);

      httpExchange.getResponseHeaders().putAll(optionHeaders);

      // if responseLength is set to -1 then no Body will be sent
      httpExchange.sendResponseHeaders(204, -1);  //TODO könnte noch verallgemeinert werden
      sendResponse(httpExchange, null);

    } else {
      System.out.println("NOT SUPPORTED HTTP METHOD!");

      // if responseLength is set to -1 then no Body will be sent
      httpExchange.sendResponseHeaders(405, -1);
      sendResponse(httpExchange, null);
    }
  }

  private void sendResponse(HttpExchange httpExchange, String responseBody) {
    try (Writer writer = new OutputStreamWriter(httpExchange.getResponseBody(), charset)){
      writer.write(responseBody);

    } catch(IOException e){
      System.out.println("Response could not be sent");

    } finally {
      httpExchange.close();
    }
  }

  private String buildResponseBody(List<Card> cards) {
    //DEBUG
    //cards = List.of(testCard, testCard);
    //DEBUG
    StringBuilder builder = new StringBuilder();
    int totalAmount = cards.size();

    System.out.println("Fetched Cards amount: " + totalAmount);

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

  private Map<String, String> queryToMap(String query) {
    if (query == null) {
      return null;
    }
    Map<String, String> result = new HashMap<>();
    for (String param : query.split("&")) {
      String[] entry = param.split("=");
      if (entry.length > 1) {
        result.put(
          URLDecoder.decode(entry[0], StandardCharsets.UTF_8),
          URLDecoder.decode(entry[1], StandardCharsets.UTF_8)
        );
      } else {
        result.put(
          URLDecoder.decode(entry[0], StandardCharsets.UTF_8),
          ""
        );
      }
    }
    return result;
  }
}
