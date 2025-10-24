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
    public static String compareHighestCards(Hand hand1, Hand hand2) {


        Hand hand1Copy = new Hand();
        for (Card c : hand1.getCards()) {
            hand1Copy.addCard(new Card(c.getValue()));
        }

        while (hand1Copy.getCards().size() < 5) {
            hand1Copy.addCard(new Card(0));
        }

        Hand hand2Copy = new Hand();
        for (Card c : hand2.getCards()) {
            hand2Copy.addCard(new Card(c.getValue()));
        }

        while (hand2Copy.getCards().size() < 5) {
            hand2Copy.addCard(new Card(0));
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
     * Détecte si une main de 4 CARTES contient exactement deux paires. (Logique de la Slice 11)
     */
    public static boolean hasTwoPairs4Cards(List<Card> cards) {
        if (cards.size() != 4) {
            return false;
        }
        int[] counts = new int[15];
        for (Card c : cards) {
            if (c.getValue() >= 0 && c.getValue() < counts.length) {
                counts[c.getValue()]++;
            }
        }
        int pairCount = 0;
        for (int count : counts) {
            if (count == 2) {
                pairCount++;
            }
        }
        return pairCount == 2;
    }

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



}