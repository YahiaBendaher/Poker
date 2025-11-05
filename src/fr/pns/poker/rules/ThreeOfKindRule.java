package fr.pns.poker.rules;

import fr.pns.poker.model.Card;
import fr.pns.poker.model.Hand;

import java.util.List;

public class ThreeOfKindRule {


    public static int getThreeOfAKind(List<Card> cards) {
        int[] counts = new int[15];
        for (Card c : cards) {
                counts[c.getValue().getCardNumber()]++;
        }
        for (int i = 14; i >= 2; i--) {
            if (counts[i] == 3) {
                return i;
            }
        }
        return 0;
    }
}
