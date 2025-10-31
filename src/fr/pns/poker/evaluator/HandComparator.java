package fr.pns.poker.evaluator;

import fr.pns.poker.model.Hand;
import fr.pns.poker.model.HandRank; // Requis pour le switch

import java.util.List;

public class HandComparator {

    public static String compareHands(Hand hand1, Hand hand2) {
        HandEvaluation eval1 = HandEvaluation.evaluateHand(hand1);
        HandEvaluation eval2 = HandEvaluation.evaluateHand(hand2);

        // 1) Comparer le type de main
        if (eval1.getRank().getRankValue() > eval2.getRank().getRankValue()) {
            return buildResultString("La main 1", eval1);
        }
        if (eval2.getRank().getRankValue() > eval1.getRank().getRankValue()) {
            return buildResultString("La main 2", eval2);
        }

        // 2) Même type -> Comparer la liste des valeurs
        List<Integer> values1 = eval1.getValues();
        List<Integer> values2 = eval2.getValues();

        for (int i = 0; i < values1.size(); i++) {
            if (values1.get(i) > values2.get(i)) {
                return buildResultString("La main 1", eval1);
            }
            if (values2.get(i) > values1.get(i)) {
                return buildResultString("La main 2", eval2);
            }
        }

        return "Egalite";
    }

    private static String buildResultString(String winner, HandEvaluation eval) {
        HandRank rank = eval.getRank();
        List<Integer> values = eval.getValues();

        int highVal = values.get(0);

        switch (rank) {
            case HIGH_CARD:
                return winner + " gagne avec carte la plus élevée : " + highVal;
            case PAIR:
                return winner + " gagne avec paire de " + highVal;
            case TWO_PAIR:
                int lowVal = values.get(1);
                return winner + " gagne avec double paire de " + highVal + " et " + lowVal;
            case THREE_OF_A_KIND:
                return winner + " gagne avec brelan de " + highVal;
            default:
                return winner + " gagne (" + eval + ")";
        }
    }


}