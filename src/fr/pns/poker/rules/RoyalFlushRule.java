package fr.pns.poker.rules;

import fr.pns.poker.model.Card;
import java.util.List;

public class RoyalFlushRule {

    public static boolean isRoyalFlush(List<Card> cards) {
        if (cards == null || cards.size() != 5) {
            return false;
        }

        int highCard = StraightFlush.getStraightFlush(cards);
        if (highCard == 0) {
            return false;
        }

        return highCard == 14;
    }
}
