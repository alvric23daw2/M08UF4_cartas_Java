package daw2.blackjackrestjava;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

@Path("/blackjack")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BlackJackResource {
    private static List<Partida> partidas = new ArrayList<>();

    @POST
    @Path("/iniciarPartida/{codiPartida}")
    public Response iniciarPartida(@PathParam("codiPartida") String codiPartida) {
        Partida partida = encontrarPartida(codiPartida);
        if (partida == null) {
            partida = new Partida(codiPartida);
            partidas.add(partida);
            return Response.ok("Partida " + codiPartida + " iniciada.").build();
        } else {
            return Response.ok("La partida " + codiPartida + " ya está en curso.").build();
        }
    }

    @GET
    @Path("/obtenerCarta/{codiPartida}")
    public Response obtenerCarta(@PathParam("codiPartida") String codiPartida) {
        Partida partida = encontrarPartida(codiPartida);
        if (partida == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        partida.repartirCartasIniciales();
        return Response.ok(partida.obtenerManosIniciales()).build();
    }

    @GET
    @Path("/pedirCarta/{codiPartida}")
    public Response pedirCarta(@PathParam("codiPartida") String codiPartida) {
        Partida partida = encontrarPartida(codiPartida);
        if (partida == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        partida.pedirCartaJugador();
        return Response.ok(partida.obtenerManosActuales()).build();
    }

    @GET
    @Path("/plantarse/{codiPartida}")
    public Response plantarse(@PathParam("codiPartida") String codiPartida) {
        Partida partida = encontrarPartida(codiPartida);
        if (partida == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        partida.plantarse();
        return Response.ok(partida.obtenerResultadoFinal()).build();
    }

    private Partida encontrarPartida(String codiPartida) {
        for (Partida partida : partidas) {
            if (partida.getCodigoPartida().equals(codiPartida)) {
                return partida;
            }
        }
        return null;
    }
}
