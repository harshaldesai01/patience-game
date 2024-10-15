package service;

import model.Card;
import model.Foundation;
import model.Lane;
import model.Pile;
import model.WastePile;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static util.CommonConstants.*;

public class ScoreManager {
    private int score;
    private final Deque<MoveRecord> moveHistory;  // Track recent moves (as a history)


    public ScoreManager() {
        this.score = 0;
        this.moveHistory = new ArrayDeque<>();
    }

    /**
     * Update the score based on the source and target pile of the move
     * @param sourcePile The pile from which the cards were moved
     * @param targetPile The pile to which the card were moved
     * @param cardsMoved The cards that were moved
     */
    public void updateScore(Pile sourcePile, Pile targetPile, List<Card> cardsMoved) {
        // Check if the move is a reversal of the last move
        if (isReversalMove(sourcePile, targetPile, cardsMoved)) {
            System.out.println("No score for reversing the same set of cards.");
            return;
        }

        // Check if moving from the uncovered pile (waste pile) to a foundation (suit)
        if (sourcePile instanceof WastePile && targetPile instanceof Foundation) {
            score += SCORE_MOVE_WASTE_TO_FOUNDATION;
            System.out.println("Score: +10 points for moving from the uncovered pile to a foundation.");
        }
        // Check if moving from a lane (Tableau) to a foundation (suit)
        else if (sourcePile instanceof Lane && targetPile instanceof Foundation) {
            score += SCORE_MOVE_LANE_TO_FOUNDATION;
            System.out.println("Score: +20 points for moving from a lane to a foundation.");
        }
        // Check if moving between lanes
        else if (sourcePile instanceof Lane && targetPile instanceof Lane) {
            score += SCORE_MOVE_BETWEEN_LANES;
            System.out.println("Score: +5 points for moving between lanes.");
        }
        // Record the move in the history
        addMoveToHistory(sourcePile, targetPile, cardsMoved);
    }

    /**
     * Checks if the current move is a reversal of any move in the history.
     *
     * @param sourcePile The current source pile.
     * @param targetPile The current target pile.
     * @param cardsMoved The current set of cards being moved.
     * @return true if the current move undoes a previous move.
     */
    private boolean isReversalMove(Pile sourcePile, Pile targetPile, List<Card> cardsMoved) {
        for (MoveRecord record : moveHistory) {
            if (record.isReversalOf(sourcePile, targetPile, cardsMoved)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds the current move to the history, maintaining a limited history size.
     */
    private void addMoveToHistory(Pile sourcePile, Pile targetPile, List<Card> cardsMoved) {
        if (moveHistory.size() >= MAX_HISTORY_SIZE) {
            moveHistory.removeFirst();  // Remove the oldest move if history exceeds the limit
        }
        moveHistory.addLast(new MoveRecord(sourcePile, targetPile, new ArrayList<>(cardsMoved)));
    }

    /**
     * Get the current score
     * @return the current score
     */
    public int getScore() {
        return score;
    }

    /**
     * Reset the score (in case of a new game)
     */
    public void resetScore() {
        this.score = 0;
    }
}
