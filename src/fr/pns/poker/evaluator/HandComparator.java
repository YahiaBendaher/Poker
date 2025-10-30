package fr.pns.poker.evaluator;

import fr.pns.poker.model.Hand;

/**
 * Compare deux mains en s'appuyant sur HandEvaluation.evaluateHand.
 */
public class HandComparator {

    public static String compareHands(Hand hand1, Hand hand2) {
        HandEvaluation eval1 = HandEvaluation.evaluateHand(hand1);
        HandEvaluation eval2 = HandEvaluation.evaluateHand(hand2);

        // 1) Comparer le type de main
        if (eval1.getRank().getRankValue() > eval2.getRank().getRankValue()) {
            return "Main 1 gagne (" + eval1 + ")";
        }
        if (eval2.getRank().getRankValue() > eval1.getRank().getRankValue()) {
            return "Main 2 gagne (" + eval2 + ")";
        }

        // 2) Même type -> comparer la valeur principale
        if (eval1.getMainValue() > eval2.getMainValue()) {
            return "Main 1 gagne (" + eval1 + ")";
        }
        if (eval2.getMainValue() > eval1.getMainValue()) {
            return "Main 2 gagne (" + eval2 + ")";
        }

        // 3) Égalité -> comparer le kicker
        if (eval1.getKicker() > eval2.getKicker()) {
            return "Main 1 gagne (kicker)";
        }
        if (eval2.getKicker() > eval1.getKicker()) {
            return "Main 2 gagne (kicker)";
        }

        return "Égalité parfaite";
    }
}
