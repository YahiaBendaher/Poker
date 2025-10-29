package fr.pns.poker.rules;

import fr.pns.poker.model.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests pour la règle du Three of a Kind")
class ThreeOfKindRuleTest {

    @Test
    @DisplayName("Une main avec un brelan devrait être validée")
    void shouldDetectThreeOfAKind() {
        List<Card> hand = Arrays.asList(
            new Card(10),
            new Card(10),
            new Card(10),
            new Card(7),
            new Card(2)
        );
        assertTrue(ThreeOfKindRule.hasThreeOfAKind(hand));
    }

    @Test
    @DisplayName("Une main avec une paire ne devrait pas être validée comme brelan")
    void shouldNotDetectPairAsThreeOfAKind() {
        List<Card> hand = Arrays.asList(
            new Card(10),
            new Card(10),
            new Card(7),
            new Card(5),
            new Card(2)
        );
        assertFalse(ThreeOfKindRule.hasThreeOfAKind(hand));
    }

    @Test
    @DisplayName("Une main avec quatre cartes identiques ne devrait pas être validée comme brelan")
    void shouldNotDetectFourOfAKindAsThreeOfAKind() {
        List<Card> hand = Arrays.asList(
            new Card(10),
            new Card(10),
            new Card(10),
            new Card(10),
            new Card(2)
        );
        assertFalse(ThreeOfKindRule.hasThreeOfAKind(hand));
    }

    @Test
    @DisplayName("Une main sans combinaison ne devrait pas être validée comme brelan")
    void shouldNotDetectHighCardAsThreeOfAKind() {
        List<Card> hand = Arrays.asList(
            new Card(10),
            new Card(8),
            new Card(7),
            new Card(5),
            new Card(2)
        );
        assertFalse(ThreeOfKindRule.hasThreeOfAKind(hand));
    }

    @Test
    @DisplayName("Devrait retourner la valeur correcte du Three of a Kind")
    void shouldReturnCorrectThreeOfAKindValue() {
        List<Card> hand = Arrays.asList(
            new Card(10),
            new Card(10),
            new Card(10),
            new Card(7),
            new Card(2)
        );
        assertEquals(10, ThreeOfKindRule.getThreeOfAKind(hand));
    }

    @Test
    @DisplayName("Devrait retourner 0 quand il n'y a pas de Three of a Kind")
    void shouldReturnZeroWhenNoThreeOfAKind() {
        List<Card> hand = Arrays.asList(
            new Card(10),
            new Card(8),
            new Card(7),
            new Card(5),
            new Card(2)
        );
        assertEquals(0, ThreeOfKindRule.getThreeOfAKind(hand));
    }

    @Test
    @DisplayName("Devrait gérer correctement un Three of a Kind d'As")
    void shouldHandleAceThreeOfAKind() {
        List<Card> hand = Arrays.asList(
            new Card(14),
            new Card(14),
            new Card(14),
            new Card(7),
            new Card(2)
        );
        assertTrue(ThreeOfKindRule.hasThreeOfAKind(hand));
        assertEquals(14, ThreeOfKindRule.getThreeOfAKind(hand));
    }

    @Test
    @DisplayName("Devrait gérer une main de plus de 5 cartes avec un Three of a Kind")
    void shouldHandleLargerHand() {
        List<Card> hand = Arrays.asList(
            new Card(10),
            new Card(10),
            new Card(10),
            new Card(7),
            new Card(2),
            new Card(3)
        );
        assertTrue(ThreeOfKindRule.hasThreeOfAKind(hand));
        assertEquals(10, ThreeOfKindRule.getThreeOfAKind(hand));
    }
}