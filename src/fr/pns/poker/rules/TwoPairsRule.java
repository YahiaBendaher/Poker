package fr.pns.poker.rules;

import fr.pns.poker.model.Card;
import fr.pns.poker.model.Hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TwoPairsRule {

    public static boolean hasTwoPairs(List<Card> cards) {
        int[] counts = new int[15];
        for (Card c : cards) {
            if (c.getValue() >= 0 && c.getValue() < counts.length) {
                counts[c.getValue()]++;
            }
        }
        int pairCount = 0;
        for (int val = 2; val <= 14; val++) {
            if (counts[val] == 2) {
                pairCount++;
            }
        }
        return pairCount == 2;
    }

    public static int[] getDoublePairValues(List<Card> cards) {
        int[] counts = new int[15];
        for (Card c : cards) {
            if (c.getValue() >= 0 && c.getValue() < counts.length) {
                counts[c.getValue()]++;
            }
        }
        List<Integer> pairs = new ArrayList<>();
        for (int val = 2; val <= 14; val++) {
            if (counts[val] == 2) {
                pairs.add(val);
            }
        }
        Collections.sort(pairs, Collections.reverseOrder());
        if (pairs.size() == 2) {
            return new int[]{pairs.get(0), pairs.get(1)};
        }
        return new int[]{0, 0};
    }
}

