package service;

import exceptions.InvalidMoveException;
import model.Lane;
import model.Pile;
import model.WastePile;

/**
 * Move Validator class to validate moves or throw appropriate exceptions in case of invalid moves
 */
public class MoveValidator {
    private final WastePile wastePile;

    public MoveValidator(WastePile wastePile) {
        this.wastePile = wastePile;
    }

    /**
     * Validates the move and throws exceptions
     * @param sourcePile the pile from which cards are supposed to be moved
     * @param targetPile the pile to which cards are supposed to be moved
     * @param numberOfCards number of cards to be moved
     */
    public void validate(Pile sourcePile, Pile targetPile, int numberOfCards) {
        if(null == sourcePile || null == targetPile || numberOfCards < 1) {
            throw new InvalidMoveException("Either you've given invalid Pile location or number of cards is less than 1.");
        }

        if(targetPile == wastePile) {
            throw new InvalidMoveException("Cannot move cards to waste pile");
        }

        if(sourcePile == wastePile && wastePile.isEmpty()) {
            throw new InvalidMoveException("No cards on the waste pile to draw. Draw a card from stock pile or move between lanes and foundations.");
        }

        if (sourcePile == wastePile && numberOfCards > 1) {
            throw new InvalidMoveException("You can only move one card from the waste pile at a time.");
        }

        if(numberOfCards > sourcePile.getCards().size()){
            throw new InvalidMoveException("Not enough cards to move. Try again.");
        }

        // If the source pile is a Lane, check numberOfCards against face-up cards
        if (sourcePile instanceof Lane laneSource) {
            int availableFaceUpCards = laneSource.getFaceUpCards().size();
            if (numberOfCards > availableFaceUpCards) {
                throw new InvalidMoveException("Not enough face-up cards to move. You can only move " + availableFaceUpCards + " cards.");
            }
        }
    }
}
