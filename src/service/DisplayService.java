package service;

import model.Card;
import model.Foundation;
import model.dto.GameStateDTO;
import model.enums.Suite;

public class DisplayService {

    /**
     * Display current game state using GameState Data Transfer Object
     * @param gameStateDTO Data Transfer Object containing game state variables
     */
    public void displayCurrentState(GameStateDTO gameStateDTO) {
        System.out.println("\n======================================");
        System.out.println("SCORE: " + gameStateDTO.getScore() + " | MOVES: " + gameStateDTO.getMoveCount());
        System.out.println("--------------------------------------");

        // Display Waste Pile
        System.out.println("WASTE PILE:" + gameStateDTO.getWastePile());

        // Display Stock Pile
        System.out.println("STOCK PILE:");
        System.out.println(gameStateDTO.getStockPile().isEmpty() ? "Empty" : "Contains Cards (Total: " + gameStateDTO.getStockPile().getCards().size() + ")");
        System.out.println();

        // Display Lanes
        System.out.println("LANES:");
        for (int i = 0; i < gameStateDTO.getLanes().size(); i++) {
            System.out.print((i + 1) + ": ");
            for (Card card : gameStateDTO.getLanes().get(i).getCards()) {
                System.out.print(card + " ");
            }
            System.out.println(); // New line for each lane
        }
        System.out.println();

        // Display Foundations
        System.out.println("FOUNDATIONS:");
        for (Suite suite : Suite.values()) {
            Foundation foundation = gameStateDTO.getFoundations().get(suite);
            System.out.println(suite + ": " + foundation);
        }
        System.out.println("======================================\n");
    }
}
