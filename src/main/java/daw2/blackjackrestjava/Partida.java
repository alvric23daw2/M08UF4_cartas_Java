package daw2.blackjackrestjava;

import java.util.ArrayList;
import java.util.List;

public class Partida {
    private String codigoPartida;
    private Cartas cartas;
    private List<String> manoJugador;
    private List<String> manoCrupier;

    public Partida(String codigoPartida) {
        this.codigoPartida = codigoPartida;
        this.cartas = new Cartas();
        this.manoJugador = new ArrayList<>();
        this.manoCrupier = new ArrayList<>();
    }

    public String getCodigoPartida() {
        return codigoPartida;
    }

    public void repartirCartasIniciales() {
        if (manoJugador.isEmpty()) {
            manoJugador.add(cartas.sacarCarta());
            manoJugador.add(cartas.sacarCarta());
            manoCrupier.add(cartas.sacarCarta());
            manoCrupier.add(cartas.sacarCarta());
        }
    }

    public void pedirCartaJugador() {
        manoJugador.add(cartas.sacarCarta());
    }

    public void plantarse() {
        while (calcularPuntos(manoCrupier) < 17) {
            manoCrupier.add(cartas.sacarCarta());
        }
    }

    public List<Object> obtenerManosIniciales() {
        List<Object> manos = new ArrayList<>();
        manos.add(manoJugador);
        manos.add(List.of(manoCrupier.get(0), "Oculta"));
        return manos;
    }

    public List<Object> obtenerManosActuales() {
        List<Object> manos = new ArrayList<>();
        manos.add(manoJugador);
        manos.add(List.of(manoCrupier.get(0), "Oculta"));
        return manos;
    }

    public List<Object> obtenerResultadoFinal() {
        List<Object> resultado = new ArrayList<>();
        resultado.add(manoJugador);
        resultado.add(manoCrupier);

        int puntosJugador = calcularPuntos(manoJugador);
        int puntosCrupier = calcularPuntos(manoCrupier);

        if (puntosJugador > 21) {
            resultado.add("Jugador Pierde");
        } else if (puntosCrupier > 21 || puntosJugador > puntosCrupier) {
            resultado.add("Jugador Gana");
        } else if (puntosJugador < puntosCrupier) {
            resultado.add("Jugador Pierde");
        } else {
            resultado.add("Empate");
        }

        return resultado;
    }

    private int calcularPuntos(List<String> mano) {
        int puntos = 0;
        int ases = 0;

        for (String carta : mano) {
            String valor = carta.split(" ")[0];
            if (valor.equals("J") || valor.equals("Q") || valor.equals("K")) {
                puntos += 10;
            } else if (valor.equals("A")) {
                ases += 1;
                puntos += 11;
            } else {
                puntos += Integer.parseInt(valor);
            }
        }

        while (puntos > 21 && ases > 0) {
            puntos -= 10;
            ases -= 1;
        }

        return puntos;
    }
}
