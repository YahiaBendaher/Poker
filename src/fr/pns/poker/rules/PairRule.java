package fr.pns.poker.rules;

import fr.pns.poker.model.Card;
import java.util.List;

public final class PairRule {


    public static int getPair(List<Card> cards) {
        int[] counts = new int[15];
        for (Card c : cards) {
            if (c.getValue() >= 0 && c.getValue() < counts.length) {
                counts[c.getValue()]++;
            }
        }
        for (int val = 14; val >= 2; val--) {
            if (counts[val] == 2) {
                return val;
            }
        }
        return 0;
    }


    public static boolean hasPair(List<Card> cards) {
        int[] counts = new int[15];
        for (Card c : cards) {
            if (c.getValue() >= 0 && c.getValue() < counts.length) {
                counts[c.getValue()]++;
            }
        }
        for (int val = 2; val <= 14; val++) {
            if (counts[val] == 2) {
                return true;
            }
        }
        return false;
    }
}
