package model;

import model.enums.Rank;
import model.enums.Suite;

/**
 * Card entity represented by suite, rank and faceUp to indicate whether it's facing up or not
 */
public class Card {
    private final Suite suite;
    private final Rank rank;
    private boolean faceUp;

    public Card(Suite suite, Rank rank) {
        this.suite = suite;
        this.rank = rank;
        this.faceUp = false; // Cards are face-down by default
    }

    public void flip() {
        this.faceUp = !this.faceUp; // Flip the card (face up/down)
    }

    public boolean isFaceUp() {
        return this.faceUp;
    }

    public Suite getSuite() {
        return this.suite;
    }

    public Rank getRank() {
        return this.rank;
    }

    public boolean isRed(){
        return suite.isRed();
    }

    @Override
    public String toString() {
        return faceUp ? rank + " of " + suite : "XX";
    }
}
