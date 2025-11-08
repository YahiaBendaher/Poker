package fr.pns.poker.rules;

import fr.pns.poker.model.Hand;
import fr.pns.poker.evaluator.HandEvaluation;
import fr.pns.poker.model.HandRank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests Full House (brelan + paire)")
class FullHouseRuleTest {

    @Test
    @DisplayName("Détection d'un Full House (Brelan de 7 et Paire de 2)")
    void shouldDetectFullHouse_7_over_2() {
        var hand = Hand.createHand("7Pi","7Co","7Tr","2Ca","2Co");
        int[] full = FullHouseRule.getFullHouse(hand.getCards());
        assertNotNull(full);
        assertEquals(7, full[0]);
        assertEquals(2, full[1]);

        var eval = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.FULL, eval.getRank());
        assertEquals(7, eval.getValues().get(0));
        assertEquals(2, eval.getValues().get(1));
    }

    @Test
    @DisplayName("Ne doit pas détecter Full House pour seulement un Brelan")
    void shouldNotDetectFullHouseWithOnlyThreeOfAKind() {
        var hand = Hand.createHand("7Pi","7Co","7Tr","9Ca","2Co");
        assertEquals(0,FullHouseRule.getFullHouse(hand.getCards()).length);
        var eval = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.THREE_OF_A_KIND, eval.getRank());
    }

    @Test
    @DisplayName("Ne doit pas détecter Full House pour Deux Paires")
    void shouldNotDetectFullHouseWithTwoPairs() {
        var hand = Hand.createHand("7Pi","7Co","9Tr","9Ca","2Co");
        assertEquals(0,FullHouseRule.getFullHouse(hand.getCards()).length);
        var eval = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.TWO_PAIR, eval.getRank());
    }

    @Test
    @DisplayName("Ne doit pas détecter Full House pour une main haute")
    void shouldNotDetectFullHouseHighCard() {
        var hand = Hand.createHand("7Pi","3Co","9Tr","ACa","2Co");
        assertEquals(0,FullHouseRule.getFullHouse(hand.getCards()).length);
        var eval = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.HIGH_CARD, eval.getRank());
    }

    @Test
    @DisplayName("Full House avec Brelan d'As et paire de 10")
    void shouldDetectFullHouse_Aces_over_Tens() {
        var hand = Hand.createHand("APi","ACo","ATr","10Ca","10Co");
        int[] full = FullHouseRule.getFullHouse(hand.getCards());
        assertNotNull(full);
        assertEquals(14, full[0]);
        assertEquals(10, full[1]);
        var eval = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.FULL, eval.getRank());
    }
}