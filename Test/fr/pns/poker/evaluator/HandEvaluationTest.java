package fr.pns.poker.evaluator;

import fr.pns.poker.model.Card;
import fr.pns.poker.model.Hand;
import fr.pns.poker.model.HandRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandEvaluationTest {

    @Test
    @DisplayName("Création d'une évaluation de main")
    void shouldCreateHandEvaluation() {
        HandEvaluation evaluation = new HandEvaluation(HandRank.PAIR, 10, 14);
        assertEquals(HandRank.PAIR, evaluation.getRank());
        assertEquals(10, evaluation.getMainValue());
        assertEquals(14, evaluation.getKicker());
    }

    @Test
    @DisplayName("Modification des valeurs d'une évaluation")
    void shouldModifyHandEvaluation() {
        HandEvaluation evaluation = new HandEvaluation(HandRank.PAIR, 10, 14);
        evaluation.setRank(HandRank.TWO_PAIR);
        evaluation.setMainValue(12);
        evaluation.setKicker(8);

        assertEquals(HandRank.TWO_PAIR, evaluation.getRank());
        assertEquals(12, evaluation.getMainValue());
        assertEquals(8, evaluation.getKicker());
    }

    @Test
    @DisplayName("Devrait évaluer une main avec une paire")
    void shouldEvaluatePairHand() {
        Hand hand = new Hand();
        hand.addCard(new Card(10));
        hand.addCard(new Card(10));
        hand.addCard(new Card(14)); // As kicker
        hand.addCard(new Card(5));
        hand.addCard(new Card(3));

        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.PAIR, evaluation.getRank());
        assertEquals(10, evaluation.getMainValue());
        assertEquals(14, evaluation.getKicker());
    }

    @Test
    @DisplayName("Devrait évaluer une main avec deux paires")
    void shouldEvaluateTwoPairHand() {
        Hand hand = new Hand();
        hand.addCard(new Card(10));
        hand.addCard(new Card(10));
        hand.addCard(new Card(7));
        hand.addCard(new Card(7));
        hand.addCard(new Card(14)); // As kicker

        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.TWO_PAIR, evaluation.getRank());
        assertEquals(10, evaluation.getMainValue());
        assertEquals(7, evaluation.getKicker());
    }

    @Test
    @DisplayName("Devrait évaluer une main avec un Three of a Kind")
    void shouldEvaluateThreeOfAKindHand() {
        Hand hand = new Hand();
        hand.addCard(new Card(10));
        hand.addCard(new Card(10));
        hand.addCard(new Card(10));
        hand.addCard(new Card(7));
        hand.addCard(new Card(14)); // As kicker

        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.THREE_OF_A_KIND, evaluation.getRank());
        assertEquals(10, evaluation.getMainValue());
        assertEquals(14, evaluation.getKicker());
    }

    @Test
    @DisplayName("Devrait évaluer une main avec carte haute")
    void shouldEvaluateHighCardHand() {
        Hand hand = new Hand();
        hand.addCard(new Card(14)); // As
        hand.addCard(new Card(10));
        hand.addCard(new Card(8));
        hand.addCard(new Card(7));
        hand.addCard(new Card(2));

        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.HIGH_CARD, evaluation.getRank());
        assertEquals(14, evaluation.getMainValue());
    }

    @Test
    @DisplayName("Devrait gérer une main avec une paire d'As")
    void shouldEvaluateAcePairHand() {
        Hand hand = new Hand();
        hand.addCard(new Card(14)); // As
        hand.addCard(new Card(14)); // As
        hand.addCard(new Card(10));
        hand.addCard(new Card(7));
        hand.addCard(new Card(2));

        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.PAIR, evaluation.getRank());
        assertEquals(14, evaluation.getMainValue());
        assertEquals(10, evaluation.getKicker());
    }

    @Test
    @DisplayName("Devrait gérer une main vide ou incomplète")
    void shouldHandleEmptyOrIncompleteHand() {
        Hand hand = new Hand();
        hand.addCard(new Card(14)); // Juste un As

        HandEvaluation evaluation = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.HIGH_CARD, evaluation.getRank());
        assertEquals(14, evaluation.getMainValue());
    }
}