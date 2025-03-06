package org.afbb.tradingcenter.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.afbb.tradingcenter.database.CardMapper;
import org.afbb.tradingcenter.database.CardService;
import org.afbb.tradingcenter.objects.dto.sets.FilteredCardsDTO;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Path("/cards")
@Produces(MediaType.APPLICATION_JSON)
public class CardResource {
    @GET
    public Response getCards(
            @QueryParam("search") String searchTerm,
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("5") int pageSize) throws SQLException {

        CardService cardService = new CardService();
        FilteredCardsDTO cards = new FilteredCardsDTO(CardMapper.mapToDtoList
                (cardService.getCards(searchTerm, page, pageSize)), cardService.getTotalAmount(searchTerm));

        Map<String, Object> response = new HashMap<>();
        response.put("cards", cards);

        return Response.ok(response).build();
    }
}
