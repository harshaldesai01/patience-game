package model;

/**
 * WastePile to keep cards drawn from StockPile
 */
public class WastePile extends Pile {
    @Override
    public boolean canAddCard(Card card) {
        return false; // No direct move into the waste pile
    }
}
