package fr.pns.poker.rules;

import fr.pns.poker.model.Card;
import java.util.List;

public class Flush {

    /**
     * Vérifie si une main de 5 cartes est une couleur (Flush) :
     * toutes les cartes ont la même couleur.
     * @param cards liste de 5 cartes
     * @return true si toutes les cartes ont la même couleur
     */
    public static boolean getCouleur(List<Card> cards) {
        if (cards == null || cards.size() != 5) {
            return false;
        }

        // On récupère la couleur de la première carte
        var firstColor = cards.get(0).getColor();

        // On vérifie que toutes les autres cartes ont la même couleur
        for (Card c : cards) {
            if (c.getColor() != firstColor) {
                return false;
            }
        }

        return true;
    }
}
