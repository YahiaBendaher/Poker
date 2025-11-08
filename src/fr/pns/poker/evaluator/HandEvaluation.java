package fr.pns.poker.evaluator;

import fr.pns.poker.model.Color;
import fr.pns.poker.model.Hand;
import fr.pns.poker.model.HandRank;
import fr.pns.poker.model.Card;
import fr.pns.poker.rules.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HandEvaluation {
    private HandRank rank;
    private List<Integer> values;
    private Color color;

    public HandEvaluation(HandRank rank, List<Integer> values) {
        this.rank = rank;
        this.values = values;
    }

    public Color getColor() {return color;}
    public void setColor(Color color) {this.color = color;}
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

        HandEvaluation royalFlush = evaluateRoyalFlush(cards);
        if (royalFlush != null) return royalFlush;

        HandEvaluation straightFlush = evaluateStraightFlush(cards, sortedValues);
        if (straightFlush != null) return straightFlush;

        HandEvaluation fourOfKind = evaluateFourOfKind(cards, sortedValues);
        if (fourOfKind != null) return fourOfKind;

        HandEvaluation fullHouse = evaluateFullHouse(cards);
        if(fullHouse != null) return fullHouse;

        HandEvaluation flush = evaluateFlush(cards, sortedValues);
        if (flush != null) return flush;

        HandEvaluation straight = evaluateStraight(cards, sortedValues);
        if (straight != null) return straight;

        HandEvaluation threeOfKind = evaluateThreeOfKind(cards, sortedValues);
        if (threeOfKind != null) return threeOfKind;

        HandEvaluation twoPair = evaluateTwoPair(cards, sortedValues);
        if (twoPair != null) return twoPair;

        HandEvaluation pair = evaluatePair(cards, sortedValues);
        if (pair != null) return pair;

        return new HandEvaluation(HandRank.HIGH_CARD, sortedValues);
    }

    // Quinte Flush Royale (Royal Flush)
    public static HandEvaluation evaluateRoyalFlush(List<Card> cards) {
        if (fr.pns.poker.rules.RoyalFlushRule.isRoyalFlush(cards)) {
            Color color = cards.get(0).getColor();
            List<Integer> royalValues = List.of(14, 13, 12, 11, 10);
            HandEvaluation eval = new HandEvaluation(HandRank.ROYAL_FLUSH, royalValues);
            eval.setColor(color);
            return eval;
        }
        return null;
    }

    public static HandEvaluation evaluateStraightFlush(List<Card> cards, List<Integer> sortedValues) {
        int straightFlushHigh = StraightFlush.getStraightFlush(cards);
        if (straightFlushHigh > 0) {
            List<Integer> values = buildStraightValues(straightFlushHigh, sortedValues);
            Color color = cards.get(0).getColor();
            HandEvaluation eval = new HandEvaluation(HandRank.STRAIGHT_FLUSH, values);
            eval.setColor(color);
            return eval;
        }
        return null;
    }

    // Carré (Four of a Kind)
    public static HandEvaluation evaluateFourOfKind(List<Card> cards, List<Integer> sortedValues) {
        int fourValue = FourOfKindRule.getFourOfAKind(cards);
        if (fourValue > 0) {
            List<Integer> values = new ArrayList<>();
            values.add(fourValue);
            List<Integer> comboCards = List.of(fourValue, fourValue, fourValue, fourValue);
            values.addAll(getKickers(sortedValues, comboCards));
            return new HandEvaluation(HandRank.FOUR_OF_A_KIND, values);
        }
        return null;
    }

    private static HandEvaluation evaluateFullHouse(List<Card> cards) {
        int[] fullHouse = FullHouseRule.getFullHouse(cards);
        if (fullHouse.length > 0){
            List<Integer> values = new ArrayList<>(fullHouse.length);
            values.add(fullHouse[0]);
            values.add(fullHouse[1]);
            return new HandEvaluation(HandRank.FULL, values);
        }
        return null;
    }
    // Couleur (Flush)
    public static HandEvaluation evaluateFlush(List<Card> cards, List<Integer> sortedValues) {
        if (FlushRule.getCouleur(cards)) {
            Color color = cards.get(0).getColor();
            HandEvaluation eval = new HandEvaluation(HandRank.FLUSH, sortedValues);
            eval.setColor(color);
            return eval;
        }
        return null;
    }

    // Suite (Straight)
    public static HandEvaluation evaluateStraight(List<Card> cards, List<Integer> sortedValues) {
        int straightHigh = StraightRule.getStraight(cards);
        if (straightHigh > 0) {
            List<Integer> straightValues = buildStraightValues(straightHigh, sortedValues);
            return new HandEvaluation(HandRank.STRAIGHT, straightValues);
        }
        return null;
    }

    public static List<Integer> buildStraightValues(int straightHigh, List<Integer> sortedValues) {
        List<Integer> straightValues = new ArrayList<>();
        if (straightHigh == 5 && sortedValues.contains(14)) {
            // Cas spécial pour la suite A-2-3-4-5
            straightValues.add(5);
            straightValues.add(4);
            straightValues.add(3);
            straightValues.add(2);
            straightValues.add(1);
        } else {
            for (int i = 0; i < 5; i++) {
                straightValues.add(straightHigh - i);
            }
        }
        return straightValues;
    }
    // Brelan (Three of a Kind)
    public static HandEvaluation evaluateThreeOfKind(List<Card> cards, List<Integer> sortedValues) {
        int threeOfKindValue = ThreeOfKindRule.getThreeOfAKind(cards);
        if (threeOfKindValue > 0) {
            List<Integer> values = new ArrayList<>();
            values.add(threeOfKindValue);
            List<Integer> comboCards = List.of(threeOfKindValue, threeOfKindValue, threeOfKindValue);
            values.addAll(getKickers(sortedValues, comboCards));
            return new HandEvaluation(HandRank.THREE_OF_A_KIND, values);
        }
        return null;
    }
    // Double Paire
    private static HandEvaluation evaluateTwoPair(List<Card> cards, List<Integer> sortedValues) {
        List<Integer> pairsList = TwoPairsRule.getDoublePairValues(cards);
        if (!pairsList.isEmpty()) {
            List<Integer> values = new ArrayList<>();
            int pairHaute = pairsList.get(0);
            int pairBasse = pairsList.get(1);
            values.add(pairHaute);
            values.add(pairBasse);
            List<Integer> comboCards = List.of(pairHaute, pairHaute, pairBasse, pairBasse);
            values.addAll(getKickers(sortedValues, comboCards));
            return new HandEvaluation(HandRank.TWO_PAIR, values);
        }
        return null;
    }
    // Paire simple
    public static HandEvaluation evaluatePair(List<Card> cards, List<Integer> sortedValues) {
        int pairValue = PairRule.getPair(cards);
        if (pairValue > 0) {
            List<Integer> values = new ArrayList<>();
            values.add(pairValue);
            List<Integer> comboCards = List.of(pairValue, pairValue);
            values.addAll(getKickers(sortedValues, comboCards));
            return new HandEvaluation(HandRank.PAIR, values);
        }
        return null;
    }

    //  Utilitaires
    /** Renvoie les valeurs 2..14 triées décroissantes. */
    public static List<Integer> getSortedCardValues(List<Card> cards) {
        List<Integer> values = new ArrayList<>();
        for (Card c : cards) {
            values.add(c.getValue().getCardNumber()); // <-- IMPORTANT
        }
        Collections.sort(values, Collections.reverseOrder());
        return values;
    }

    /** Enlève des valeurs triées les cartes déjà utilisées par la combinaison, et renvoie les kickers restants. */
    public static List<Integer> getKickers(List<Integer> allSortedValues, List<Integer> comboValues) {
        List<Integer> kickers = new ArrayList<>(allSortedValues);
        for (Integer comboVal : comboValues) {
            kickers.remove(comboVal);
        }
        return kickers;
    }
}
