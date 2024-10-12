package service;

import model.Card;
import model.Deck;
import model.Foundation;
import model.Lane;
import model.enums.Suite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static util.CommonConstants.NUMBER_OF_LANES;

/**
 * Class containing methods to set up the initial state of the game
 */
public class GameInitializer {
    /**
     * Initialize the lanes (Tableau) for the game
     * @return a list of initialized lanes
     */
    public ArrayList<Lane> initializeLanes() {
        ArrayList<Lane> lanes = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_LANES; i++) {
            lanes.add(new Lane());
        }
        return lanes;
    }

    /**
     * Initializes the foundation piles for each suite.
     * @return an unmodifiable map of foundation piles keyed by suite.
     */
    public Map<Suite, Foundation> initializeFoundations() {
        Map<Suite, Foundation> foundations = new HashMap<>();

        // Initialize foundations for each suite
        for (Suite suite : Suite.values()) {
            foundations.put(suite, new Foundation(suite)); // Create a new Foundation for each suite
        }

        return foundations;
    }

    /**
     * Deals initial cards to the lanes
     * @param deck the deck from which to draw cards
     * @param lanes the lanes to which cards are dealt
     */
    public void dealInitialCards(Deck deck, ArrayList<Lane> lanes) {
        for (int i = 0; i < lanes.size(); i++) {
            for (int j = 0; j <= i; j++) {
                if (!deck.isEmpty()) {
                    Card card = deck.drawCard();
                    lanes.get(i).addCard(card);
                    if (j == i) {
                        card.flip(); // Last card in each lane is face-up
                    }
                }
            }
        }
    }
}
