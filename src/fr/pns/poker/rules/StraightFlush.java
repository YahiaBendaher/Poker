package fr.pns.poker.rules;

import fr.pns.poker.model.Card;

import java.util.List;


public class StraightFlush {

    public static int getStraightFlush(List<Card> cards) {
        if (cards == null || cards.size() != 5) {
            return 0;
        }
        // Vérifie d'abord que toutes les cartes sont de la même couleur
        if (!FlushRule.getCouleur(cards)){
            return 0;
        }
        // Vérifie ensuite si c’est une suite
        int highStraight = StraightRule.getStraight(cards);
        if (highStraight > 0) {
            return highStraight;
        }
        return 0;
}
}
