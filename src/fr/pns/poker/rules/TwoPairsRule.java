package fr.pns.poker.rules;

import fr.pns.poker.model.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TwoPairsRule {

    // La méthode retourne maintenant List<Integer>
    public static List<Integer> getDoublePairValues(List<Card> cards) {
        int[] counts = new int[15];
        for (Card c : cards) {
            counts[c.getValue()]++;
        }
        List<Integer> pairs = new ArrayList<>();
        for (int val = 2; val <= 14; val++) {
            if (counts[val] == 2) {
                pairs.add(val);
            }
        }

        if (pairs.size() != 2) {
            return new ArrayList<>();
        }


        Collections.sort(pairs, Collections.reverseOrder());
        return pairs;
    }
}