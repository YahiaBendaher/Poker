package fr.pns.poker.evaluator;

import fr.pns.poker.model.Hand;
import fr.pns.poker.model.HandRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandEvaluationTest {

    @Test
    @DisplayName("Devrait évaluer HIGH_CARD avec les kickers dans l'ordre")
    void shouldEvaluateHighCardHand() {
        Hand hand = Hand.createHand(14, 10, 8, 7, 2);
        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.HIGH_CARD, evaluation.getRank());
        assertEquals(List.of(14, 10, 8, 7, 2), evaluation.getValues());
    }

    @Test
    @DisplayName("Devrait évaluer PAIR avec 3 kickers dans l'ordre")
    void shouldEvaluatePairHand() {
        Hand hand = Hand.createHand(10, 10, 14, 5, 3);
        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.PAIR, evaluation.getRank());
        assertEquals(List.of(10, 14, 5, 3), evaluation.getValues());
    }

    @Test
    @DisplayName("Devrait évaluer PAIR (As) avec 3 kickers dans l'ordre")
    void shouldEvaluateAcePairHand() {
        Hand hand = Hand.createHand(14, 14, 10, 7, 2);
        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.PAIR, evaluation.getRank());
        assertEquals(List.of(14, 10, 7, 2), evaluation.getValues());
    }

    @Test
    @DisplayName("Devrait évaluer TWO_PAIR avec 1 kicker")
    void shouldEvaluateTwoPairHand() {
        Hand hand = Hand.createHand(10, 10, 7, 7, 14);
        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.TWO_PAIR, evaluation.getRank());
        assertEquals(List.of(10, 7, 14), evaluation.getValues());
    }

    @Test
    @DisplayName("Devrait évaluer TWO_PAIR (As et Rois) avec 1 kicker")
    void shouldEvaluateHighTwoPairHand() {
        Hand hand = Hand.createHand(14, 14, 13, 13, 5);
        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);

        assertEquals(HandRank.TWO_PAIR, evaluation.getRank());
        assertEquals(List.of(14, 13, 5), evaluation.getValues());
    }

    @Test
    @DisplayName("Devrait évaluer THREE_OF_A_KIND avec 2 kickers")
    void shouldEvaluateThreeOfAKindHand() {
        Hand hand = Hand.createHand(10, 10, 10, 7, 14);
        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);

        assertEquals(HandRank.THREE_OF_A_KIND, evaluation.getRank());
        assertEquals(List.of(10, 14, 7), evaluation.getValues());
    }
}