package fr.pns.poker.rules;

import fr.pns.poker.model.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StraightRule {

    public static int getStraight(List<Card> cards) {
        if (cards == null || cards.size() != 5) return 0;


        List<Integer> values = new ArrayList<Integer>(5);
        for (int i = 0; i < cards.size(); i++) {
            values.add(cards.get(i).getValue().getCardNumber());
        }

        Collections.sort(values);


        List<Integer> uniq = new ArrayList<Integer>(5);
        for (int i = 0; i < values.size(); i++) {
            if (uniq.isEmpty() || !values.get(i).equals(uniq.get(uniq.size() - 1))) {
                uniq.add(values.get(i));
            }
        }

        if (uniq.size() != 5) return 0;

        // On vérifie que c'est une suite (chaque valeur suivante = précédente + 1)
        for (int i = 0; i < 4; i++) {
            if (uniq.get(i + 1) != uniq.get(i) + 1) {
                return 0;
            }
        }


        return uniq.get(4);
    }
}
