package daw2.blackjackrestjava;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

@Path("/blackjack")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BlackJackResource {
    private static Map<String, Game> games = new HashMap<>();

    @POST
    @Path("/iniciarPartida/{codiPartida}")
    public Response iniciarPartida(@PathParam("codiPartida") String codiPartida) {
        Game game = new Game();
        games.put(codiPartida, game);
        return Response.ok().build();
    }

    @GET
    @Path("/obtenerCarta/{codiPartida}")
    public Response obtenerCarta(@PathParam("codiPartida") String codiPartida) {
        Game game = games.get(codiPartida);
        if (game == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        game.initialDeal();
        return Response.ok(game.getInitialHands()).build();
    }

    @GET
    @Path("/pedirCarta/{codiPartida}")
    public Response pedirCarta(@PathParam("codiPartida") String codiPartida) {
        Game game = games.get(codiPartida);
        if (game == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        game.playerHit();
        return Response.ok(game.getCurrentHands()).build();
    }

    @GET
    @Path("/plantarse/{codiPartida}")
    public Response plantarse(@PathParam("codiPartida") String codiPartida) {
        Game game = games.get(codiPartida);
        if (game == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        game.stand();
        return Response.ok(game.getFinalResult()).build();
    }
}
