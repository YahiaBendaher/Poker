package fr.pns.poker.rules;

import fr.pns.poker.model.Card;
import java.util.List;

public class FullHouseRule {

    public static int[] getFullHouse(List<Card> cards) {
        if (cards == null || cards.size() != 5) {
            return new int[0];
        }
        int[] counts = new int[15]; // index de 2 à 14
        for (Card c :  cards) {
            counts[c.getValue().getCardNumber()]++;
        }
        int brelanVal = 0 , pairVal = 0;

        for (int i=2 ; i<15 ; i++) {
            if (counts[i] == 3) {
                brelanVal = i;
            } else if (counts[i] == 2) {
                pairVal = i;
            }
        }
        if (brelanVal > 0 && pairVal > 0) {
            return new int[]{brelanVal, pairVal};
        }
        return new int[0]; // pas de fullHouse donc retourne un tableau vide
    }
}
