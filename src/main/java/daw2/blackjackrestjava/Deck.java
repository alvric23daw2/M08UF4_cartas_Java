package daw2.blackjackrestjava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<String> cards;

    public Deck() {
        this.cards = createDeck();
        shuffle();
    }

    private List<String> createDeck() {
        String[] suits = {"♥", "♦", "♣", "♠"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        List<String> deck = new ArrayList<>();

        for (String suit : suits) {
            for (String value : values) {
                deck.add(value + " of " + suit);
            }
        }

        return deck;
    }

    private void shuffle() {
        Collections.shuffle(this.cards);
    }

    public String drawCard() {
        return this.cards.remove(this.cards.size() - 1);
    }
}
