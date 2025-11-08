package fr.pns.poker.rules;

import fr.pns.poker.model.Card;

import java.util.List;

public class FourOfKindRule {

    public static int getFourOfAKind(List<Card> cards) {
        int[] counts = new int[15];
        for (Card c : cards) {
            int v = c.getValue().getCardNumber();
            counts[v]++;
        }
        for (int val = 14; val >= 2; val--) {
            if (counts[val] == 4) return val;
        }
        return 0;
    }
}

