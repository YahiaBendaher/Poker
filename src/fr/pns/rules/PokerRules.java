package fr.pns.rules;

import fr.pns.poker.Card;
import fr.pns.poker.Hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokerRules {
    /**
     * Compare deux mains de 5 cartes sur la base de la carte la plus haute,
     * puis des kickers. (Logique de la Slice 5)
     */

    /**
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
                String reason = (i == 0) ? "carte la plus haute" : "Kicker";
                return "Main 1 gagne (" + reason + " : " + max1 + ")";

            } else if (max2 > max1) {
                String reason = (i == 0) ? "carte la plus haute" : "Kicker";
                return "Main 2 gagne (" + reason + " : " + max2 + ")";

            } else {
                if (max1 == 0) break;
                boolean removed1 = false;
                for (Card c : hand1Copy.getCards()) {
                    if (c.getValue() == max1 && !removed1) {
                        c.setValue(0);
                        removed1 = true;
                    }
                }
                boolean removed2 = false;
                for (Card c : hand2Copy.getCards()) {
                    if (c.getValue() == max2 && !removed2) {
                        c.setValue(0);
                        removed2 = true;
                    }
                }
            }
        }
        return "Égalité parfaite !";
    }
     /*


    /**
     * Détecte la paire la plus haute dans une main. (Logique de la Slice 7 améliorée)
     * @return La valeur de la paire la plus haute si trouvée, sinon 0.
     */
    public static int getPair(List<Card> cards) {
        int[] counts = new int[15];
        for (Card c : cards) {
            if (c.getValue() >= 0 && c.getValue() < counts.length) {
                counts[c.getValue()]++;
            }
        }
        for (int val = 14; val >= 2; val--) {
            if (counts[val] == 2) {
                return val;
            }
        }
        return 0;
    }
    /**
     * Vérifie si une main contient au moins une paire.
     */
    public static boolean hasPair(List<Card> cards) {
        return getPair(cards) > 0;
    }

    /**
     * Détecte si une main de 5 CARTES contient au moins deux paires.
     */
    public static boolean hasTwoPairs(List<Card> cards) {
        int[] counts = new int[15];
        for (Card c : cards) {
            counts[c.getValue()]++;
        }
        int pairCount = 0;
        for (int val = 2; val < counts.length; val++) {
            if (counts[val] == 2) {
                pairCount++;
            }
        }
        return pairCount >= 2;
    }

    /**
    public static String compareHands(Hand hand1, Hand hand2) {
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

            if (valPaire1 > valPaire2) {
                return "Main 1 gagne (Paire plus haute : " + valPaire1 + ")";
            }
            else if (valPaire1 < valPaire2) {
                return "Main 2 gagne (Paire plus haute : " + valPaire2 + ")";
            }
            else{
                return compareHighestCards(hand1, hand2);
            }

        }
        return compareHighestCards(hand1, hand2);
    }
     */
    /**
    public static String brelanIn3Cards(Hand hand){
        List<Card> cards = hand.getCards();
        int valeur = cards.get(0).getValue();
        for (Card c : cards) {
            if (c.getValue() != valeur) {
                return "cette main ne contient pas de brelan" ;
            }
        }
        return "Brelan de " +  valeur;
    }
     */


    /**
    public static String brelan (Hand hand){
        List<Card> cards = hand.getCards();
        List<Integer> tracker = new ArrayList<>(Collections.nCopies(13, 0)); // pour créer une ArrayList avec que des 0
        for (Card c : cards) {
            int index = c.getValue() - 2 ;
            tracker.set(index,tracker.get(index) + 1);
        }
        for (Integer i : tracker) {
            if (i >= 3) {
                return "Brelan de " + (tracker.indexOf(i) + 2);
            }
        }
        return "cette main ne contient pas de brelan" ;
    }
     /*

    /**
    public static String compareDoublePairs(Hand hand1, Hand hand2) {

        boolean twoPairs1 = hasTwoPairs(hand1.getCards());
        boolean twoPairs2 = hasTwoPairs(hand2.getCards());
        boolean pair1 = hasPair(hand1.getCards());
        boolean pair2 = hasPair(hand2.getCards());

        // une main a une double paire, l’autre une simple paire
        if (twoPairs1 && pair2 && !twoPairs2) return "Main 1 gagne (Double Paire)";
        if (twoPairs2 && pair1 && !twoPairs1) return "Main 2 gagne (Double Paire)";

        // les deux mains ont des doubles paires → comparaison
        if (twoPairs1 && twoPairs2) {

            int[] pairs1 = getDoublePairValues(hand1.getCards());
            int[] pairs2 = getDoublePairValues(hand2.getCards());

            // Compare la paire la plus haute
            if (pairs1[0] > pairs2[0]) return "Main 1 gagne (Meilleure double paire)";
            if (pairs2[0] > pairs1[0]) return "Main 2 gagne (Meilleure double paire)";

            // Compare la deuxième paire
            if (pairs1[1] > pairs2[1]) return "Main 1 gagne (Deuxième paire plus haute)";
            if (pairs2[1] > pairs1[1]) return "Main 2 gagne (Deuxième paire plus haute)";

            // Compare le kicker
            int kicker1 = getDoublePairKicker(hand1.getCards(), pairs1);
            int kicker2 = getDoublePairKicker(hand2.getCards(), pairs2);

            if (kicker1 > kicker2) return "Main 1 gagne (Kicker)";
            if (kicker2 > kicker1) return "Main 2 gagne (Kicker)";

            return "Égalité parfaite (Double Paire)";
        }

        // aucune double paire détectée
        return "Aucune double paire détectée";
    }

*/

    /**
     * Retourne les valeurs des deux paires présentes dans une main,
     * triées par ordre décroissant (ex: [10,10,8,8,4] → {10,8})
     */
    public static int[] getDoublePairValues(List<Card> cards) {
        int[] counts = new int[15];
        for (Card c : cards) {
            counts[c.getValue()]++;
        }

        List<Integer> pairs = new ArrayList<>();
        for (int val = 14; val >= 2; val--) {
            if (counts[val] >= 2) {
                pairs.add(val);
            }
        }

        if (pairs.size() >= 2) {
            return new int[]{pairs.get(0), pairs.get(1)};
        }
        return null;
    }

    public static boolean isBRELAN(List<Card> cards) {
        int[] counts = new int[15];
        for (Card c : cards) counts[c.getValue()]++;
        for (int val = 2; val < counts.length; val++) {
            if (counts[val] == 3) return true;
        }
        return false;
    }

    public static int getBRELAN(List<Card> cards) {
        int[] counts = new int[15];
        for (Card c : cards) counts[c.getValue()]++;
        for (int val = 14; val >= 2; val--) {
            if (counts[val] == 3) return val;
        }
        return 0;
    }


    public static HandEvaluation evaluateHand(Hand hand) {
        List<Card> cards = hand.getCards();

        if (hasTwoPairs(cards)) {
            int[] pairs = getDoublePairValues(cards);
            return new HandEvaluation(HandRank.TWO_PAIR, pairs[0], pairs[1]);
        }

        if (hasPair(cards)) {
            int valPair = getPair(cards);
            return new HandEvaluation(HandRank.PAIR, valPair, hand.getMax());
        }

        if (isBRELAN(cards)) {
            int valBrelan = getBRELAN(cards);
            return new HandEvaluation(HandRank.BRELAN, valBrelan, hand.getMax());
        }

        return new HandEvaluation(HandRank.HIGH_CARD, hand.getMax(), 0);
    }

    // --- Méthode de comparaison des deux mains ---
    public static String compareHands(Hand hand1, Hand hand2) {
        HandEvaluation eval1 = evaluateHand(hand1);
        HandEvaluation eval2 = evaluateHand(hand2);

        // Compare les types de main
        if (eval1.getRank().getRankValue() > eval2.getRank().getRankValue()) {
            return "Main 1 gagne (" + eval1 + ")";
        }
        if (eval2.getRank().getRankValue() > eval1.getRank().getRankValue()) {
            return "Main 2 gagne (" + eval2 + ")";
        }

        // Même type -> compare les valeurs principales
        if (eval1.getMainValue() > eval2.getMainValue()) {
            return "Main 1 gagne (" + eval1 + ")";
        }
        if (eval2.getMainValue() > eval1.getMainValue()) {
            return "Main 2 gagne (" + eval2 + ")";
        }

        // Égalité sur kicker
        if (eval1.getKicker() > eval2.getKicker()) {
            return "Main 1 gagne (kicker)";
        }
        if (eval2.getKicker() > eval1.getKicker()) {
            return "Main 2 gagne (kicker)";
        }

        return "Égalité parfaite";
    }
}


