package fr.pns.poker.rules;

import fr.pns.poker.model.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StraightRule {

    public static int getStraight(List<Card> cards) {
        if (cards == null || cards.size() != 5) {
            return 0;
        }

        List<Integer> values = new ArrayList<>();
        for (Card card : cards) {
            values.add(card.getValue().getCardNumber());
        }

        Collections.sort(values);

        List<Integer> uniqueValues = new ArrayList<>();
        if (!values.isEmpty()) {
            uniqueValues.add(values.get(0));
            for (int i = 1; i < values.size(); i++) {
                if (!values.get(i).equals(values.get(i - 1))) {
                    uniqueValues.add(values.get(i));
                }
            }
        }

        if (uniqueValues.size() != 5) {
            return 0;
        }

        // Check un straight standard
        boolean isStandardStraight = true;
        for (int i = 0; i < 4; i++) {
            if (uniqueValues.get(i + 1) != uniqueValues.get(i) + 1) {
                isStandardStraight = false;
                break;
            }
        }

        if (isStandardStraight) {
            return uniqueValues.get(4); // Return the highest card in the straight
        }

        // Si ce n'est pas un straight standard, On regarde si c'est A-2-3-4-5
        // Dans ce cas la valeur la plus haute est 5
        boolean isAceLowStraight = uniqueValues.get(0) == 2 &&
                                   uniqueValues.get(1) == 3 &&
                                   uniqueValues.get(2) == 4 &&
                                   uniqueValues.get(3) == 5 &&
                                   uniqueValues.get(4) == 14;

        if (isAceLowStraight) {
            return 5;
        }

        return 0;
    }
}
