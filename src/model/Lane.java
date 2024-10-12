package model;

import model.enums.Rank;

import java.util.ArrayList;
import java.util.List;

/**
 * Lane class to represent each lane in the Tableau
 */
public class Lane extends Pile {

    /**
     * Method checks if card is in one lesser than previous & different color or King if lane is empty
     * @param card card to be added
     * @return true if condition is satisfied
     */
    @Override
    public boolean canAddCard(Card card) {
        if(cards.isEmpty()) {
            return card.getRank() == Rank.KING;
        }
        Card topCard = peekCard();
        return !card.isRed() == topCard.isRed() && card.getRank().getValue() == topCard.getRank().getValue() -1;
    }

    /**
     * Util method to get all face-up cards
     * @return list of all face-up cards
     */
    public List<Card> getFaceUpCards() {
        List<Card> faceUpCards = new ArrayList<>();
        for (Card card : cards) {
            if (card.isFaceUp()) { //
                faceUpCards.add(card);
            }
        }
        return faceUpCards;
    }

    /**
     * Method to make top card face-up
     */
    public void faceUpTopCard() {
        if(!cards.isEmpty()) {
            if(!cards.peek().isFaceUp()){
                cards.peek().flip();
            }
        }
    }
}
