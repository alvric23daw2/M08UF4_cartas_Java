package daw2.blackjackrestjava;

import java.util.*;

public class Game {
    private Deck deck;
    private List<String> playerHand;
    private List<String> dealerHand;

    public Game() {
        this.deck = new Deck();
        this.playerHand = new ArrayList<>();
        this.dealerHand = new ArrayList<>();
    }

    public void initialDeal() {
        if (playerHand.isEmpty()) {
            playerHand.add(deck.drawCard());
            playerHand.add(deck.drawCard());
            dealerHand.add(deck.drawCard());
            dealerHand.add(deck.drawCard());
        }
    }

    public void playerHit() {
        playerHand.add(deck.drawCard());
    }

    public void stand() {
        while (calculatePoints(dealerHand) < 17) {
            dealerHand.add(deck.drawCard());
        }
    }

    public Map<String, Object> getInitialHands() {
        Map<String, Object> hands = new HashMap<>();
        hands.put("playerHand", playerHand);
        hands.put("dealerHand", List.of(dealerHand.get(0), "Hidden"));
        return hands;
    }

    public Map<String, Object> getCurrentHands() {
        Map<String, Object> hands = new HashMap<>();
        hands.put("playerHand", playerHand);
        hands.put("dealerHand", List.of(dealerHand.get(0), "Hidden"));
        return hands;
    }

    public Map<String, Object> getFinalResult() {
        Map<String, Object> result = new HashMap<>();
        result.put("playerHand", playerHand);
        result.put("dealerHand", dealerHand);

        int playerPoints = calculatePoints(playerHand);
        int dealerPoints = calculatePoints(dealerHand);

        if (playerPoints > 21) {
            result.put("result", "Player Loses");
        } else if (dealerPoints > 21 || playerPoints > dealerPoints) {
            result.put("result", "Player Wins");
        } else if (playerPoints < dealerPoints) {
            result.put("result", "Player Loses");
        } else {
            result.put("result", "Tie");
        }

        return result;
    }

    private int calculatePoints(List<String> hand) {
        int points = 0;
        int aces = 0;

        for (String card : hand) {
            String value = card.split(" ")[0];
            if (value.equals("J") || value.equals("Q") || value.equals("K")) {
                points += 10;
            } else if (value.equals("A")) {
                aces += 1;
                points += 11;
            } else {
                points += Integer.parseInt(value);
            }
        }

        while (points > 21 && aces > 0) {
            points -= 10;
            aces -= 1;
        }

        return points;
    }
}
