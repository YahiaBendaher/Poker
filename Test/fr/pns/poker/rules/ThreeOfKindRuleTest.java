package fr.pns.poker.rules;

import fr.pns.poker.model.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests pour la règle du Three of a Kind (getThreeOfAKind)")
class ThreeOfKindRuleTest {

    @Test
    @DisplayName("Devrait retourner la valeur correcte du Brelan")
    void shouldReturnCorrectThreeOfAKindValue() {
        List<Card> hand = Arrays.asList(
                new Card(10), new Card(10), new Card(10), new Card(7), new Card(2)
        );
        assertEquals(10, ThreeOfKindRule.getThreeOfAKind(hand));
    }

    @Test
    @DisplayName("Devrait retourner 0 pour une simple paire")
    void shouldReturnZeroForPair() {
        List<Card> hand = Arrays.asList(
                new Card(10), new Card(10), new Card(7), new Card(5), new Card(2)
        );
        assertEquals(0, ThreeOfKindRule.getThreeOfAKind(hand));
    }

    @Test
    @DisplayName("Devrait retourner 0 pour un Carré (Four of a kind)")
    void shouldReturnZeroForFourOfAKind() {
        List<Card> hand = Arrays.asList(
                new Card(10), new Card(10), new Card(10), new Card(10), new Card(2)
        );
        assertEquals(0, ThreeOfKindRule.getThreeOfAKind(hand));
    }

    @Test
    @DisplayName("Devrait retourner 0 quand il n'y a pas de Brelan")
    void shouldReturnZeroWhenNoThreeOfAKind() {
        List<Card> hand = Arrays.asList(
                new Card(10), new Card(8), new Card(7), new Card(5), new Card(2)
        );
        assertEquals(0, ThreeOfKindRule.getThreeOfAKind(hand));
    }

    @Test
    @DisplayName("Devrait gérer correctement un Brelan d'As")
    void shouldHandleAceThreeOfAKind() {
        List<Card> hand = Arrays.asList(
                new Card(14), new Card(14), new Card(14), new Card(7), new Card(2)
        );
        assertEquals(14, ThreeOfKindRule.getThreeOfAKind(hand));
    }
}