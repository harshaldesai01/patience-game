package model;

import model.enums.Rank;
import model.enums.Suite;

import static util.CommonConstants.FOUNDATION_CARD_COUNT;

/**
 * Class to represent each of the Foundation piles, initiated with a suite
 */
public class Foundation extends Pile {
    private final Suite suite;

    public Foundation(Suite suite) {
        super();
        this.suite = suite;
    }

    /**
     * Card of correct suite, in ascending order and ACE should be the first
     * @param card card to be added
     * @return true if condition is satisfied
     */
    @Override
    public boolean canAddCard(Card card) {
        if(!card.getSuite().equals(this.suite)) {
            return false;
        }
        if(isEmpty() && card.getRank() == Rank.ACE) {
            return true;
        }
        if(!isEmpty()) {
            Card topCard = peekCard();
            return isNextRank(card, topCard);
        }
        return false;
    }

    /**
     * Method to check if the given card is of the next rank
     * @param card given card
     * @param topCard top card in the Pile
     * @return true if it is next rank
     */
    private boolean isNextRank(Card card, Card topCard) {
        return card.getRank().getValue() == topCard.getRank().getValue() + 1;
    }

    /**
     * Method checks if foundation is complete using current size
     * @return true if size is 13
     */
    public boolean isComplete() {
        return cards.size() == FOUNDATION_CARD_COUNT;
    }
}
