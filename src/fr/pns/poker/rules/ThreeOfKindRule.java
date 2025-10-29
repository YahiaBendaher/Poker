package fr.pns.poker.rules;

import fr.pns.poker.model.Card;
import fr.pns.poker.model.Hand;

import java.util.List;

public final class ThreeOfKindRule {

    public static boolean hasThreeOfAKind(List<Card> cards) {
        int[] counts = new int[15];
        for (Card c : cards) {
            if (c.getValue() >= 0 && c.getValue() < counts.length) {
                counts[c.getValue()]++;
            }
        }
        for (int val = 2; val <= 14; val++) {
            if (counts[val] == 3) {
                return true;
            }
        }
        return false;
    }

    public static int getThreeOfAKind(List<Card> cards) {
        int[] counts = new int[15];
        for (Card c : cards) {
            if (c.getValue() >= 0 && c.getValue() < counts.length) {
                counts[c.getValue()]++;
            }
        }
        for (int val = 14; val >= 2; val--) {
            if (counts[val] == 3) {
                return val;
            }
        }
        return 0;
    }
}
