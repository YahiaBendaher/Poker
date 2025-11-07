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
        Hand hand = Hand.createHand("ATr", "10Co", "8Pi", "7Ca", "2Tr");
        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.HIGH_CARD, evaluation.getRank());
        assertEquals(List.of(14, 10, 8, 7, 2), evaluation.getValues());
    }

    @Test
    @DisplayName("Devrait évaluer PAIR avec 3 kickers dans l'ordre")
    void shouldEvaluatePairHand() {
        Hand hand = Hand.createHand("10Tr", "10Co", "APi", "5Ca", "3Tr");
        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.PAIR, evaluation.getRank());
        assertEquals(List.of(10, 14, 5, 3), evaluation.getValues());
    }

    @Test
    @DisplayName("Devrait évaluer PAIR (As) avec 3 kickers dans l'ordre")
    void shouldEvaluateAcePairHand() {
        Hand hand = Hand.createHand("ATr", "ACo", "10Pi", "7Ca", "2Tr");
        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.PAIR, evaluation.getRank());
        assertEquals(List.of(14, 10, 7, 2), evaluation.getValues());
    }

    @Test
    @DisplayName("Devrait évaluer TWO_PAIR avec 1 kicker")
    void shouldEvaluateTwoPairHand() {
        Hand hand = Hand.createHand("10Tr", "10Co", "7Pi", "7Ca", "ATr");
        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.TWO_PAIR, evaluation.getRank());
        assertEquals(List.of(10, 7, 14), evaluation.getValues());
    }

    @Test
    @DisplayName("Devrait évaluer TWO_PAIR (As et Rois) avec 1 kicker")
    void shouldEvaluateHighTwoPairHand() {
        Hand hand = Hand.createHand("ATr", "ACo", "RPi", "RCa", "5Tr");
        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);

        assertEquals(HandRank.TWO_PAIR, evaluation.getRank());
        assertEquals(List.of(14, 13, 5), evaluation.getValues());
    }

    @Test
    @DisplayName("Devrait évaluer THREE_OF_A_KIND avec 2 kickers")
    void shouldEvaluateThreeOfAKindHand() {
        Hand hand = Hand.createHand("10Tr", "10Co", "10Pi", "7Ca", "ATr");
        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);

        assertEquals(HandRank.THREE_OF_A_KIND, evaluation.getRank());
        assertEquals(List.of(10, 14, 7), evaluation.getValues());
    }

    @Test
    @DisplayName("Devrait évaluer STRAIGHT (Suite)")
    void shouldEvaluateStraight() {
        Hand hand = Hand.createHand("5Tr", "6Co", "7Pi", "8Ca", "9Tr");
        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.STRAIGHT, evaluation.getRank());
        assertEquals(List.of(9, 8, 7, 6, 5), evaluation.getValues());
    }

    @Test
    void testFlushEvaluation() {
        Hand hand = Hand.createHand("2Tr", "5Tr", "8Tr", "RTr", "ATr");
        HandEvaluation eval = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.FLUSH, eval.getRank());
        assertEquals(List.of(14, 13, 8, 5, 2), eval.getValues());
        assertEquals(14, eval.getValues().get(0));
    }

    @Test
    void testNotFlushEvaluation() {
        Hand hand = Hand.createHand("2Tr", "5Tr", "8Tr", "RCo", "ATr");
        HandEvaluation eval = HandEvaluation.evaluateHand(hand);
        // Doit être une simple "HIGH_CARD"
        assertEquals(HandRank.HIGH_CARD, eval.getRank());
    }


}
