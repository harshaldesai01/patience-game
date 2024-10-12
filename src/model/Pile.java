package model;

import java.util.Stack;

/**
 * Abstract class to represent a Pile. Will be inherited by Foundation, Lane, StockPile and WastePile
 */
public abstract class Pile {
    protected final Stack<Card> cards;

    public Pile() {
        cards = new Stack<>();
    }

    public void addCard(Card card) {
        cards.push(card);
    }

    public Card removeCard() {
        return cards.isEmpty()? null: cards.pop();
    }

    public Card peekCard() {
        return cards.isEmpty()? null: cards.peek();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /**
     * Abstract method to check if a card can be added to a pile, each pile will have it's specific implementation
     * @param card card to be added
     * @return true if card can be added
     */
    public abstract boolean canAddCard(Card card);

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Card card: cards) {
            sb.append(card).append(" ");
        }
        return sb.toString();
    }

    public Stack<Card> getCards() {
        return cards;
    }
}
