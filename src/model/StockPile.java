package model;

import java.util.Stack;

/**
 * StockPile initialized after dealing lanes. Cards can be drawn from this pile.
 */
public class StockPile extends Pile {
    public StockPile(Stack<Card> cards) {
        super();
        this.cards.addAll(cards);
    }

    /**
     * No specific move validation needed here, since cards are drawn from the stock to the waste pile
     * @param card card to be moved
     * @return always returns false
     */
    @Override
    public boolean canAddCard(Card card) {
        return false;
    }

    /**
     * Draw a card from the stock
     * @return last from the stock
     */
    public Card drawCard() {
        if(!cards.isEmpty()) {
            return cards.pop();
        } else {
            return null;
        }
    }

    /**
     * Method to move all cards from wastePile back to stockPile
     * @param wastePile wastePile instance
     */
    public void replenishFromWastePile(WastePile wastePile) {
        if(wastePile.isEmpty()) {
            System.out.println("No cards in the Waste Pile to replenish the Stock Pile");
            return;
        }

        while(!wastePile.isEmpty()) {
            Card card = wastePile.removeCard();
            // Flip card as it is face-up in waste pile
            card.flip();
            cards.push(card);
        }
    }
}
