package fr.pns.poker.rules;

import fr.pns.poker.model.Card;
import java.util.List;

public class SameColorRule {

    /**
     * Vérifie si toutes les cartes d'une liste ont la même couleur.
     * @param cards liste de cartes
     * @return true si elles ont la même couleur, false sinon
     */
    public static boolean haveSameColor(List<Card> cards) {
        if (cards.isEmpty()) return false;
        String firstColor = cards.get(0).getColor().name();
        for (Card c : cards) {
            if (!c.getColor().name().equals(firstColor)) {
                return false;
            }
        }
        return true;
    }
}
