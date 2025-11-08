package fr.pns.poker.rules;

import fr.pns.poker.model.Hand;
import fr.pns.poker.model.HandRank;
import fr.pns.poker.evaluator.HandEvaluation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoyalFlushRuleTest {

    @Test
    public void testIsRoyalFlush() {
        // Quinte Flush Royale à Pique
        Hand hand = Hand.createHand("10Pi", "VPi", "DPi", "RPi", "APi");

        // Vérifie la détection via la règle directe
        assertTrue(RoyalFlushRule.isRoyalFlush(hand.getCards()),
                "Doit être détectée comme une Quinte Flush Royale");

        // Vérifie aussi via le moteur général d’évaluation
        HandEvaluation eval = HandEvaluation.evaluateHand(hand);
        assertEquals(HandRank.ROYAL_FLUSH, eval.getRank(),
                "La main doit être classée comme une Quinte Flush Royale");
    }

    @Test
    public void testNotRoyalFlush_DifferentColor() {
        // Même valeurs mais pas la même couleur
        Hand hand = Hand.createHand("10Pi", "VPi", "DPi", "RPi", "ACa");

        assertFalse(RoyalFlushRule.isRoyalFlush(hand.getCards()),
                "Doit échouer car les cartes ne sont pas toutes de la même couleur");
    }

    @Test
    public void testNotRoyalFlush_MissingAce() {
        // Suite de 9 à Roi → Quinte Flush simple
        Hand hand = Hand.createHand("9Pi", "10Pi", "VPi", "DPi", "RPi");

        assertFalse(RoyalFlushRule.isRoyalFlush(hand.getCards()),
                "Doit échouer car la main ne contient pas d’As");
    }
}
