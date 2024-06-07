package daw2.blackjackrestjava;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Cartas {
    private List<String> cartas;

    public Cartas() {
        this.cartas = new LinkedList<>();
        // Añadir cartas a la baraja
        String[] palos = {"♥", "♦", "♣", "♠"};
        String[] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (String palo : palos) {
            for (String valor : valores) {
                cartas.add(valor + " de " + palo);
            }
        }

        Collections.shuffle(cartas);
    }

    public String sacarCarta() {
        return cartas.remove(0);
    }
}
