package fr.pns.poker.rules;

import fr.pns.poker.model.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests pour la règle des deux paires")
class TwoPairsRuleTest {

    @Test
    @DisplayName("Une main avec deux paires devrait être validée")
    void shouldDetectTwoPairs() {
        List<Card> hand = Arrays.asList(
            new Card(10),
            new Card(10),
            new Card(7),
            new Card(7),
            new Card(2)
        );
        assertTrue(TwoPairsRule.hasTwoPairs(hand));
    }

    @Test
    @DisplayName("Une main avec une seule paire ne devrait pas être validée")
    void shouldNotDetectSinglePair() {
        List<Card> hand = Arrays.asList(
            new Card(10),
            new Card(10),
            new Card(7),
            new Card(5),
            new Card(2)
        );
        assertFalse(TwoPairsRule.hasTwoPairs(hand));
    }

    @Test
    @DisplayName("Une main avec trois paires ne devrait pas être validée")
    void shouldDetectThreePairs() {
        List<Card> hand = Arrays.asList(
            new Card(10),
            new Card(10),
            new Card(7),
            new Card(7),
            new Card(2),
            new Card(2)
        );
        assertFalse(TwoPairsRule.hasTwoPairs(hand));
    }

    @Test
    @DisplayName("Une main avec un brelan ne devrait pas être validée comme deux paires")
    void shouldNotDetectThreeOfAKindAsTwoPairs() {
        List<Card> hand = Arrays.asList(
            new Card(10),
            new Card(10),
            new Card(10),
            new Card(7),
            new Card(2)
        );
        assertFalse(TwoPairsRule.hasTwoPairs(hand));
    }

    @Test
    @DisplayName("Devrait retourner les bonnes valeurs des deux paires")
    void shouldReturnCorrectPairValues() {
        List<Card> hand = Arrays.asList(
            new Card(10),
            new Card(10),
            new Card(7),
            new Card(7),
            new Card(2)
        );
        int[] pairValues = TwoPairsRule.getDoublePairValues(hand);
        assertEquals(10, pairValues[0]);
        assertEquals(7, pairValues[1]);
    }

    @Test
    @DisplayName("Devrait retourner [0,0] quand il n'y a pas deux paires")
    void shouldReturnZeroArrayWhenNoTwoPairs() {
        List<Card> hand = Arrays.asList(
            new Card(10),
            new Card(10),
            new Card(7),
            new Card(5),
            new Card(2)
        );
        int[] pairValues = TwoPairsRule.getDoublePairValues(hand);
        assertArrayEquals(new int[]{0, 0}, pairValues);
    }

    @Test
    @DisplayName("Devrait gérer correctement deux paires avec As")
    void shouldHandleAcePairs() {
        List<Card> hand = Arrays.asList(
            new Card(14),
            new Card(14),
            new Card(13),
            new Card(13),
            new Card(2)
        );
        assertTrue(TwoPairsRule.hasTwoPairs(hand));
        int[] pairValues = TwoPairsRule.getDoublePairValues(hand);
        assertEquals(14, pairValues[0]);
        assertEquals(13, pairValues[1]);
    }

    @Test
    @DisplayName("Devrait gérer une main de plus de 5 cartes avec deux paires")
    void shouldHandleLargerHand() {
        List<Card> hand = Arrays.asList(
            new Card(10),
            new Card(10),
            new Card(7),
            new Card(7),
            new Card(2),
            new Card(3)
        );
        assertTrue(TwoPairsRule.hasTwoPairs(hand));
        int[] pairValues = TwoPairsRule.getDoublePairValues(hand);
        assertEquals(10, pairValues[0]);
        assertEquals(7, pairValues[1]);
    }
}