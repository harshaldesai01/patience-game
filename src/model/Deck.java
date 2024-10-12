package model;

import model.enums.Rank;
import model.enums.Suite;

import java.util.Collections;
import java.util.Stack;

/**
 * Class to represent deck of 52 cards of all 4 suites
 */
public class Deck {
    private final Stack<Card> cards;

    public Deck() {
        cards = new Stack<>();
        for(Suite suite : Suite.values()) {
            for(Rank rank: Rank.values()) {
                cards.add(new Card(suite, rank));
            }
        }
        shuffle();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        return !cards.isEmpty() ? cards.pop() : null;
    }

    public Stack<Card> getRemainingCards() {
        return cards;
    }

    public void clear() {
        cards.clear();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
