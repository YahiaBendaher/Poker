package fr.pns.poker.evaluator;

import fr.pns.poker.model.Color;
import fr.pns.poker.model.Hand;
import fr.pns.poker.model.HandRank;
import fr.pns.poker.model.Value;

import java.util.List;

public class HandComparator {

    public static String compareHands(Hand hand1, Hand hand2) {
        HandEvaluation eval1 = HandEvaluation.evaluateHand(hand1);
        HandEvaluation eval2 = HandEvaluation.evaluateHand(hand2);

        // 1) Comparer le type de main
        if (eval1.getRank().getRankValue() > eval2.getRank().getRankValue()) {
            return buildResultString("Main 1", eval1);
        }
        if (eval2.getRank().getRankValue() > eval1.getRank().getRankValue()) {
            return buildResultString("Main 2", eval2);
        }

        // 2) Même type -> Comparer la liste des valeurs
        List<Integer> values1 = eval1.getValues();
        List<Integer> values2 = eval2.getValues();

        for (int i = 0; i < values1.size(); i++) {
            if (values1.get(i) > values2.get(i)) {
                return buildResultString("Main 1", eval1);
            }
            if (values2.get(i) > values1.get(i)) {
                return buildResultString("Main 2", eval2);
            }
        }
        return "Egalite";
    }

    private static String buildResultString(String winner, HandEvaluation eval) {
        HandRank rank = eval.getRank();
        List<Integer> values = eval.getValues();

        int highVal = values.get(0);
        String symbol = Value.getNameFromNumber(highVal);

        switch (rank) {
            case HIGH_CARD:
                return winner + " gagne avec carte la plus élevée : " + symbol;
            case PAIR:
                return winner + " gagne avec paire de " + symbol;
            case TWO_PAIR:
                int lowVal = values.get(1);
                String lowSymbol = Value.getNameFromNumber(lowVal);
                return winner + " gagne avec double paire de " + symbol + " et " + lowSymbol;
            case THREE_OF_A_KIND:
                return winner + " gagne avec brelan de " + symbol;
            case STRAIGHT:
                return winner + " gagne avec une suite, carte la plus haute : " + symbol;
            case FLUSH:
                Color couleur = eval.getColor();
                return winner + " gagne avec une couleur à "
                        + couleur.getNomFrancais()
                        + " (carte la plus haute : " + symbol + ")";
            case FULL:
                int lowValFull = values.get(1);
                String lowSymbolFull = Value.getNameFromNumber(lowValFull);
                if (lowValFull > 10 && lowValFull < 14) {
                    lowSymbolFull = lowSymbolFull + "s";
                }
                if (highVal > 10 && highVal < 14) {
                    symbol = symbol + "s";
                }
                return winner + " gagne avec un FULL, (Brelan de "+symbol+", Paire de "+lowSymbolFull+")";
            default:
                return winner + " gagne (" + eval + ")";
        }
    }


}