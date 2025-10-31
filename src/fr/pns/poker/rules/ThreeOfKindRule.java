package fr.pns.poker.rules;

import fr.pns.poker.model.Card;
import fr.pns.poker.model.Hand;

import java.util.List;

public class ThreeOfKindRule {


    public static int getThreeOfAKind(List<Card> cards) {
        int[] counts = new int[15];
        for (Card c : cards) {
                counts[c.getValue()]++;
        }
        for (int val = 14; val >= 2; val--) {
            if (counts[val] == 3) {
                return val;
            }
        }
        return 0;
    }
}
