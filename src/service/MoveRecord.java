package service;

import model.Card;
import model.Pile;

import java.util.List;

/**
 * Class to store a particular move and a method which checks if a given move is a reverse of the current move
 */
public class MoveRecord {
    private final Pile sourcePile;
    private final Pile targetPile;
    private final List<Card> movedCards;

    public MoveRecord(Pile sourcePile, Pile targetPile, List<Card> movedCards) {
        this.sourcePile = sourcePile;
        this.targetPile = targetPile;
        this.movedCards = movedCards;
    }

    /**
     * Checks if this move is the reverse of the given move.
     *
     * @param currentSource The current source pile.
     * @param currentTarget The current target pile.
     * @param currentCards The current set of cards being moved.
     * @return true if the current move is a reversal of this move.
     */
    public boolean isReversalOf(Pile currentSource, Pile currentTarget, List<Card> currentCards) {
        return sourcePile == currentTarget && targetPile == currentSource && movedCards.equals(currentCards);
    }
}
