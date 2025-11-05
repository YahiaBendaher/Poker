package fr.pns.poker.rules;

import fr.pns.poker.model.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for StraightRule
 * Vérifie le bon fonctionnement de la détection des suites (Straights)
 */
class StraightRuleTest {

    @Test
    @DisplayName("Vérifie qu'une main de cinq cartes consécutives est reconnue comme une suite")
    void shouldReturnHighCardForStraight() {
        Hand hand = Hand.createHand("5Tr", "6Co", "7Pi", "8Ca", "9Tr");
        assertEquals(9, StraightRule.getStraight(hand.getCards()));
    }

    @Test
    @DisplayName("Vérifie qu'une main avec une carte non consécutive n'est pas reconnue comme une suite")
    void shouldReturnZeroForNonConsecutiveCards() {
        Hand hand = Hand.createHand("2Tr", "3Co", "5Pi", "6Ca", "7Tr");
        assertEquals(0, StraightRule.getStraight(hand.getCards()));
    }

    @Test
    @DisplayName("Vérifie qu'une main avec des doublons n'est pas considérée comme une suite")
    void shouldReturnZeroForDuplicateValues() {
        Hand hand = Hand.createHand("4Tr", "5Co", "5Pi", "6Ca", "7Tr");
        assertEquals(0, StraightRule.getStraight(hand.getCards()));
    }

    @Test
    @DisplayName("Vérifie qu'une suite en ordre décroissant est reconnue comme une suite")
    void shouldReturnHighCardForDescendingSequence() {
        Hand hand = Hand.createHand("9Tr", "8Co", "7Pi", "6Ca", "5Tr");
        assertEquals(9, StraightRule.getStraight(hand.getCards()));
    }

    @Test
    @DisplayName("Vérifie qu'une main désordonnée (non triée) est reconnue comme une suite")
    void shouldReturnHighCardForUnsortedList() {
        Hand hand = Hand.createHand("7Tr", "5Co", "9Pi", "6Ca", "8Tr");
        assertEquals(9, StraightRule.getStraight(hand.getCards()));
    }

    @Test
    @DisplayName("Vérifie la suite basse avec l'As (As, 2, 3, 4, 5)")
    void shouldHandleAceLowStraight() {
        Hand hand = Hand.createHand("ATr", "2Co", "3Pi", "4Ca", "5Tr");
        assertEquals(5, StraightRule.getStraight(hand.getCards()));
    }

    @Test
    @DisplayName("Vérifie la suite haute avec l'As (10, V, D, R, A)")
    void shouldHandleAceHighStraight() {
        Hand hand = Hand.createHand("10Tr", "VCo", "DPi", "RCa", "ATr");
        assertEquals(14, StraightRule.getStraight(hand.getCards()));
    }
}
