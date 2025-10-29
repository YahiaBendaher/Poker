package fr.pns.poker.rules;

import fr.pns.poker.model.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests pour la règle de la paire")
class PairRuleTest {

    @Test
    @DisplayName("Une main avec une paire devrait être validée")
    void shouldDetectPair() {
        List<Card> hand = Arrays.asList(
                new Card(10),
                new Card(10),
                new Card(7),
                new Card(5),
                new Card(2)
        );
        assertTrue(PairRule.hasPair(hand));
    }

    @Test
    @DisplayName("Une main sans paire ne devrait pas être validée")
    void shouldNotDetectNoPair() {
        List<Card> hand = Arrays.asList(
                new Card(10),
                new Card(8),
                new Card(7),
                new Card(5),
                new Card(2)
        );
        assertFalse(PairRule.hasPair(hand));
    }

    @Test
    @DisplayName("Une main avec un brelan ne devrait pas être validée comme contenant une paire")
    void shouldNotDetectThreeOfAKindAsPair() {
        List<Card> hand = Arrays.asList(
                new Card(10),
                new Card(10),
                new Card(10),
                new Card(5),
                new Card(2)
        );
        assertFalse(PairRule.hasPair(hand));
    }

    @Test
    @DisplayName("Une main avec deux paires devrait être validée comme contenant une paire")
    void shouldDetectTwoPairsAsPair() {
        List<Card> hand = Arrays.asList(
                new Card(10),
                new Card(10),
                new Card(7),
                new Card(7),
                new Card(2)
        );
        assertTrue(PairRule.hasPair(hand));
    }

    @Test
    @DisplayName("Devrait retourner la valeur correcte de la paire")
    void shouldReturnCorrectPairValue() {
        List<Card> hand = Arrays.asList(
                new Card(10),
                new Card(10),
                new Card(7),
                new Card(5),
                new Card(2)
        );
        assertEquals(10, PairRule.getPair(hand));
    }

    @Test
    @DisplayName("Devrait retourner la plus haute paire en cas de deux paires")
    void shouldReturnHighestPairValue() {
        List<Card> hand = Arrays.asList(
                new Card(10),
                new Card(10),
                new Card(7),
                new Card(7),
                new Card(2)
        );
        assertEquals(10, PairRule.getPair(hand));
    }

    @Test
    @DisplayName("Devrait retourner 0 quand il n'y a pas de paire")
    void shouldReturnZeroWhenNoPair() {
        List<Card> hand = Arrays.asList(
                new Card(10),
                new Card(8),
                new Card(7),
                new Card(5),
                new Card(2)
        );
        assertEquals(0, PairRule.getPair(hand));
    }
}