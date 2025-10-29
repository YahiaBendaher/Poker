package fr.pns.poker.evaluator;

import fr.pns.poker.model.Hand;
import fr.pns.poker.model.HandRank;
import fr.pns.poker.model.Card;
import fr.pns.poker.rules.PairRule;
import fr.pns.poker.rules.TwoPairsRule;
import fr.pns.poker.rules.ThreeOfKindRule;
import java.util.List;


public final class HandEvaluation {
    private HandRank rank;
    private int mainValue;  // valeur principale de la combinaison (ex: valeur de la paire, du brelan)
    private int kicker;     // carte la plus haute restante (utile en cas d'égalité)

    public HandEvaluation(HandRank rank, int mainValue, int kicker) {
        this.rank = rank;
        this.mainValue = mainValue;
        this.kicker = kicker;
    }

    public HandRank getRank() { return rank; }
    public void setRank(HandRank rank) { this.rank = rank; }

    public int getMainValue() { return mainValue; }
    public void setMainValue(int mainValue) { this.mainValue = mainValue; }

    public int getKicker() { return kicker; }
    public void setKicker(int kicker) { this.kicker = kicker; }

    @Override
    public String toString() {
        return rank + " (valeur: " + mainValue + ", kicker: " + kicker + ")";
    }

    // ===== Logique d'évaluation (ex-HandEvaluator.evaluateHand) =====
    public static HandEvaluation evaluateHand(Hand hand) {
        List<Card> cards = hand.getCards();

        // Two Pairs
        if (TwoPairsRule.hasTwoPairs(cards)) {
            int[] pairs = TwoPairsRule.getDoublePairValues(cards);
            // mainValue = highest pair, kicker = second pair
            return new HandEvaluation(HandRank.TWO_PAIR, pairs[0], pairs[1]);
        }

        // Pair
        if (PairRule.hasPair(cards)) {
            int pairValue = PairRule.getPair(cards);
            // Find highest kicker not in the pair
            int highestKicker = 0;
            for (Card card : cards) {
                if (card.getValue() != pairValue && card.getValue() > highestKicker) {
                    highestKicker = card.getValue();
                }
            }
            return new HandEvaluation(HandRank.PAIR, pairValue, highestKicker);
        }

        // Three of a kind
        if (ThreeOfKindRule.hasThreeOfAKind(cards)) {
            int threeOfKindValue = ThreeOfKindRule.getThreeOfAKind(cards);
            return new HandEvaluation(HandRank.THREE_OF_A_KIND, threeOfKindValue, hand.getMax());
        }

        // High card (fallback)
        return new HandEvaluation(HandRank.HIGH_CARD, hand.getMax(), 0);
    }
}
