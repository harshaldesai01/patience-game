package service;

import model.Foundation;
import model.Lane;
import model.Pile;
import model.WastePile;

import static util.CommonConstants.*;

public class ScoreManager {
    private int score;

    public ScoreManager() {
        this.score = 0;
    }

    /**
     * Update the score based on the source and target pile of the move
     * @param sourcePile The pile from which the card was moved
     * @param targetPile The pile to which the card was moved
     */
    public void updateScore(Pile sourcePile, Pile targetPile) {
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
