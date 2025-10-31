package fr.pns.poker.rules;

import fr.pns.poker.model.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for StraightRule
 * Vérifie le bon fonctionnement de la détection des suites (Straights)
 */
class StraightRuleTest {

    @Test
    @DisplayName("Vérifie qu'une main de cinq cartes consécutives est reconnue comme une suite")
    void shouldReturnTrueForConsecutiveCards() {
        List<Card> hand = Arrays.asList(
                new Card(5), new Card(6), new Card(7), new Card(8), new Card(9)
        );
        assertTrue(StraightRule.getStraight(hand));
    }

    @Test
    @DisplayName("Vérifie qu'une main avec une carte non consécutive n'est pas reconnue comme une suite")
    void shouldReturnFalseForNonConsecutiveCards() {
        List<Card> hand = Arrays.asList(
                new Card(2), new Card(3), new Card(5), new Card(6), new Card(7)
        );
        assertFalse(StraightRule.getStraight(hand));
    }

    @Test
    @DisplayName("Vérifie qu'une main avec des doublons n'est pas considérée comme une suite")
    void shouldReturnFalseForDuplicateValues() {
        List<Card> hand = Arrays.asList(
                new Card(4), new Card(5), new Card(5), new Card(6), new Card(7)
        );
        assertFalse(StraightRule.getStraight(hand));
    }

    @Test
    @DisplayName("Vérifie qu'une suite en ordre décroissant est reconnue comme une suite ")
    void shouldReturnFalseForDescendingSequence() {
        List<Card> hand = Arrays.asList(
                new Card(9), new Card(8), new Card(7), new Card(6), new Card(5)
        );
        assertTrue(StraightRule.getStraight(hand));
    }

    @Test
    @DisplayName("Vérifie qu'une main désordonnée (non triée) est reconnue comme une suite  ")
    void shouldReturnFalseForUnsortedList() {
        List<Card> hand = Arrays.asList(
                new Card(7), new Card(5), new Card(9), new Card(6), new Card(8)
        );
        assertTrue(StraightRule.getStraight(hand));
    }

}
