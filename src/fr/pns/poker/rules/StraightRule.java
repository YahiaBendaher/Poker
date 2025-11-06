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
        // Extraire les valeurs numériques
        List<Integer> values = new ArrayList<>();
        for (Card card : cards) {
            values.add(card.getValue().getCardNumber());
        }

        // Trier et supprimer les doublons
        Collections.sort(values);
        List<Integer> uniqueValues = new ArrayList<>();
        uniqueValues.add(values.get(0));
        for (int i = 1; i < values.size(); i++) {
            if (!values.get(i).equals(values.get(i - 1))) {
                uniqueValues.add(values.get(i));
            }
        }

        // Doit contenir 5 valeurs distinctes
        if (uniqueValues.size() != 5) return 0;


        // Cas spécial A-2-3-4-5
        if (uniqueValues.equals(List.of(2, 3, 4, 5, 14))) return 5;

        // Vérifie si les cartes sont consécutives (ex: 5-6-7-8-9)
        for (int i = 0; i < 4; i++) {
            if (uniqueValues.get(i+1) != uniqueValues.get(i) + 1 ) {
                return 0;
            }
        }
        // Retourne la carte la plus haute
        return uniqueValues.get(4);

    }
}
