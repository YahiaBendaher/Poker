package fr.pns.poker.evaluator;

import fr.pns.poker.model.Hand;
import fr.pns.poker.model.HandRank;
import fr.pns.poker.model.Card;
import fr.pns.poker.rules.PairRule;
import fr.pns.poker.rules.TwoPairsRule;
import fr.pns.poker.rules.ThreeOfKindRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class HandEvaluation {
    private HandRank rank;
    private List<Integer> values;

    public HandEvaluation(HandRank rank, List<Integer> values) {
        this.rank = rank;
        this.values = values;
    }

    public HandRank getRank() { return rank; }
    public void setRank(HandRank rank) { this.rank = rank; }

    public List<Integer> getValues() {
        return values;
    }

    @Override
    public String toString() {
        return rank + " (Valeurs: " + values + ")";
    }

    public static HandEvaluation evaluateHand(Hand hand) {
        List<Card> cards = hand.getCards();
        List<Integer> sortedValues = getSortedCardValues(cards);

        List<Integer> values = new ArrayList<>();

        // Brelan
        int threeOfKindValue = ThreeOfKindRule.getThreeOfAKind(cards);

        if (threeOfKindValue > 0) {

            values.add(threeOfKindValue);
            List<Integer> comboCards = List.of(threeOfKindValue, threeOfKindValue, threeOfKindValue);
            values.addAll(getKickers(sortedValues, comboCards));
            return new HandEvaluation(HandRank.THREE_OF_A_KIND, values);
        }

        // Double Paire
        List<Integer> pairsList = TwoPairsRule.getDoublePairValues(cards);
        if (!pairsList.isEmpty()) {
            int pairHaute = pairsList.get(0);
            int pairBasse = pairsList.get(1);

            values.add(pairHaute);
            values.add(pairBasse);

            List<Integer> comboCards = List.of(pairHaute, pairHaute, pairBasse, pairBasse);
            values.addAll(getKickers(sortedValues, comboCards));
            return new HandEvaluation(HandRank.TWO_PAIR, values);
        }

        // Paire
        int pairValue = PairRule.getPair(cards);
        if (pairValue > 0) {

            values.add(pairValue);
            List<Integer> comboCards = List.of(pairValue, pairValue);
            values.addAll(getKickers(sortedValues, comboCards));
            return new HandEvaluation(HandRank.PAIR, values);
        }

        return new HandEvaluation(HandRank.HIGH_CARD, sortedValues);
    }


    private static List<Integer> getSortedCardValues(List<Card> cards) {

        List<Integer> values = new ArrayList<>();
        for (Card c : cards) {
            values.add(c.getValue());
        }

        Collections.sort(values, Collections.reverseOrder());
        return values;
    }

    private static List<Integer> getKickers(List<Integer> allSortedValues, List<Integer> comboValues) {


        List<Integer> kickers = new ArrayList<>(allSortedValues);


        for (Integer comboVal : comboValues) {
            kickers.remove(comboVal);
        }


        return kickers;
    }



}
