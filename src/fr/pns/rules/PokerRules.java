package fr.pns.rules;

import fr.pns.poker.Card;
import fr.pns.poker.Hand;

import java.util.List;


public class PokerRules {

    /**
     * Prend 2 valeurs et retourne la plus haute.
     */
    public static int getHighestValue(int val1, int val2) {
        return Math.max(val1, val2);
    }

    /**
     * Compare 2 valeurs et retourne la plus haute, ou "Égalité".
     */
    public static String compareTwoValues(int val1, int val2) {
        if (val1 > val2) return String.valueOf(val1);
        else if (val2 > val1) return String.valueOf(val2);
        else return "Égalité";
    }

    /**
     * Compare deux mains de 2 cartes (en List) sur leur MEILLEURE carte (sans kicker).
     */
    public static String compareTwoCardHands(List<Card> hand1, List<Card> hand2) {
        int max1 = Math.max(hand1.get(0).getValue(), hand1.get(1).getValue());
        int max2 = Math.max(hand2.get(0).getValue(), hand2.get(1).getValue());
        if (max1 > max2) return "Main 1 gagne";
        else if (max2 > max1) return "Main 2 gagne";
        else return "Égalité";
    }

    /**
     * Départage deux mains de 2 cartes (en List) avec le "kicker".
     */
    public static String compareTwoCardHandsWithKicker(List<Card> hand1, List<Card> hand2) {
        int max1 = Math.max(hand1.get(0).getValue(), hand1.get(1).getValue());
        int min1 = Math.min(hand1.get(0).getValue(), hand1.get(1).getValue());
        int max2 = Math.max(hand2.get(0).getValue(), hand2.get(1).getValue());
        int min2 = Math.min(hand2.get(0).getValue(), hand2.get(1).getValue());

        if (max1 > max2) return "Main 1 gagne";
        else if (max2 > max1) return "Main 2 gagne";
        else {
            if (min1 > min2) return "Main 1 gagne";
            else if (min2 > min1) return "Main 2 gagne";
            else return "Égalité";
        }
    }


    /**
     * Compare deux mains de 5 cartes (objets Hand) sur la base de la carte la plus haute,
     * puis des kickers.
     */
    public static String compareHighestCards(Hand hand1, Hand hand2) {

        Hand hand1Copy = new Hand();
        for (Card c : hand1.getCards()) {
            hand1Copy.addCard(new Card(c.getValue()));
        }

        Hand hand2Copy = new Hand();
        for (Card c : hand2.getCards()) {
            hand2Copy.addCard(new Card(c.getValue()));
        }

        for (int i = 0; i < 5; i++) {
            int max1 = hand1Copy.getMax();
            int max2 = hand2Copy.getMax();

            if (max1 > max2) {
                return (i == 0) ? "Main 1 gagne (carte la plus haute : " + max1 + ")" : "Main 1 gagne (Kicker : " + max1 + ")";
            } else if (max2 > max1) {
                return (i == 0) ? "Main 2 gagne (carte la plus haute : " + max2 + ")" : "Main 2 gagne (Kicker : " + max2 + ")";
            } else {
                if (max1 == 0) break;
                for (Card c : hand1Copy.getCards()) { if (c.getValue() == max1) { c.setValue(0); break; } }
                for (Card c : hand2Copy.getCards()) { if (c.getValue() == max2) { c.setValue(0); break; } }
            }
        }
        return "Égalité parfaite !";
    }

    /**
     * Détecte une paire dans une liste de 2 cartes.
     * @return La valeur de la paire si trouvée, sinon 0.
     */
    public static int detectPairInTwoCards(List<Card> cartes) {
        if (cartes.size() != 2) return 0;
        // Utilise getValue() pour correspondre à tes changements
        if (cartes.get(0).getValue() == cartes.get(1).getValue()) {
            return cartes.get(0).getValue();
        } else {
            return 0;
        }
    }


    public static  int getPair(List<Card> cards) {
        boolean[] alreadySeen = new boolean[15]; // 0-14
        for (Card c : cards) {
            int val = c.getValue();
            if (alreadySeen[val]) {
                return val;
            }
            alreadySeen[val] = true;
        }
        return 0;
    }

    public static boolean hasPair(List<Card> cards) {
        return getPair(cards) > 0;
    }


    public static Hand createHand(int... values ) {
        Hand hand = new Hand();
        for (int val : values) {
            hand.addCard(new Card(val));
        }
        return hand;
    }

    public static String compareWith(Hand hand1, Hand hand2) {
        boolean hasPair1 = hasPair(hand1.getCards());
        boolean hasPair2 = hasPair(hand2.getCards());

        if (hasPair1 && !hasPair2) {
            int valPaire1 = getPair(hand1.getCards());
            return "Main 1 gagne (Paire : " + valPaire1 + ")";
        }
        if (!hasPair1 && hasPair2) {
            int valPaire2 = getPair(hand2.getCards());
            return "Main 2 gagne (Paire : " + valPaire2 + ")";
        }
        if (hasPair1 && hasPair2) {
            int valPaire1 = getPair(hand1.getCards());
            int valPaire2 = getPair(hand2.getCards());

            if (valPaire1 > valPaire2)
                return "Main 1 gagne (Paire plus haute : " + valPaire1 + ")";
            if (valPaire2 > valPaire1)
                return "Main 2 gagne (Paire plus haute : " + valPaire2 + ")";
            return "Égalité (Paires identiques)";
        }
        return compareHighestCards(hand1, hand2);
    }


}