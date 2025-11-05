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
        Hand hand = Hand.createHand("ATr", "ACo", "KPi", "KCa", "5Tr");
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
    @DisplayName("Devrait évaluer FLUSH (Couleur)")
    void shouldEvaluateFlush() {
        Hand hand = Hand.createHand("ATr", "10Tr", "8Tr", "7Tr", "3Tr");
        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.FLUSH, evaluation.getRank());
        assertEquals(List.of(14, 10, 8, 7, 3), evaluation.getValues());
    }

    @Test
    @DisplayName("Devrait évaluer FULL_HOUSE")
    void shouldEvaluateFullHouse() {
        Hand hand = Hand.createHand("10Tr", "10Co", "10Pi", "7Ca", "7Tr");
        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.FULL_HOUSE, evaluation.getRank());
        assertEquals(List.of(10, 7), evaluation.getValues());
    }

    @Test
    @DisplayName("Devrait évaluer FOUR_OF_A_KIND (Carré)")
    void shouldEvaluateFourOfAKind() {
        Hand hand = Hand.createHand("ATr", "ACo", "APi", "ACa", "KTr");
        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.FOUR_OF_A_KIND, evaluation.getRank());
        assertEquals(List.of(14, 13), evaluation.getValues());
    }

    @Test
    @DisplayName("Devrait évaluer STRAIGHT_FLUSH (Suite Couleur)")
    void shouldEvaluateStraightFlush() {
        Hand hand = Hand.createHand("8Tr", "7Tr", "6Tr", "5Tr", "4Tr");
        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.STRAIGHT_FLUSH, evaluation.getRank());
        assertEquals(List.of(8, 7, 6, 5, 4), evaluation.getValues());
    }

    @Test
    @DisplayName("Devrait évaluer ROYAL_FLUSH (Suite Couleur Royale)")
    void shouldEvaluateRoyalFlush() {
        Hand hand = Hand.createHand("ATr", "KTr", "QTr", "JTr", "10Tr");
        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.ROYAL_FLUSH, evaluation.getRank());
        assertEquals(List.of(14, 13, 12, 11, 10), evaluation.getValues());
    }
}
