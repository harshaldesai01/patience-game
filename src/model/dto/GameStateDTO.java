package model.dto;

import model.Foundation;
import model.Lane;
import model.StockPile;
import model.WastePile;
import model.enums.Suite;

import java.util.List;
import java.util.Map;

/**
 * GameState Data Transfer Object to represent the current state of the game
 */
public class GameStateDTO {
    private final int score;
    private final int moveCount;
    private final WastePile wastePile;
    private final StockPile stockPile;
    private final List<Lane> lanes;
    private final Map<Suite, Foundation> foundations;

    public GameStateDTO(int score, int moveCount, WastePile wastePile, StockPile stockPile,
                        List<Lane> lanes, Map<Suite, Foundation> foundations) {
        this.score = score;
        this.moveCount = moveCount;
        this.wastePile = wastePile;
        this.stockPile = stockPile;
        this.lanes = lanes;
        this.foundations = foundations;
    }

    public int getScore() {
        return score;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public WastePile getWastePile() {
        return wastePile;
    }

    public StockPile getStockPile() {
        return stockPile;
    }

    public List<Lane> getLanes() {
        return lanes;
    }

    public Map<Suite, Foundation> getFoundations() {
        return foundations;
    }
}
