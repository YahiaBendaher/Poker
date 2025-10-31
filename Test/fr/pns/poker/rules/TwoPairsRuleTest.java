package fr.pns.poker.rules;

import fr.pns.poker.model.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests pour la règle des deux paires (getDoublePairValues)")
class TwoPairsRuleTest {

    @Test
    @DisplayName("Devrait retourner une liste [PaireHaute, PaireBasse]")
    void shouldDetectTwoPairs() {
        List<Card> hand = Arrays.asList(
                new Card(10), new Card(10), new Card(7), new Card(7), new Card(2)
        );
        List<Integer> result = TwoPairsRule.getDoublePairValues(hand);
        assertEquals(List.of(10, 7), result);
    }

    @Test
    @DisplayName("Devrait retourner une liste vide pour une seule paire")
    void shouldReturnEmptyListForSinglePair() {
        List<Card> hand = Arrays.asList(
                new Card(10), new Card(10), new Card(7), new Card(5), new Card(2)
        );
        List<Integer> result = TwoPairsRule.getDoublePairValues(hand);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Devrait retourner une liste vide pour un brelan")
    void shouldReturnEmptyListForThreeOfAKind() {
        List<Card> hand = Arrays.asList(
                new Card(10), new Card(10), new Card(10), new Card(7), new Card(2)
        );
        List<Integer> result = TwoPairsRule.getDoublePairValues(hand);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Devrait gérer les As et Rois")
    void shouldHandleAceKingPairs() {
        List<Card> hand = Arrays.asList(
                new Card(14), new Card(14), new Card(13), new Card(13), new Card(2)
        );
        List<Integer> result = TwoPairsRule.getDoublePairValues(hand);
        assertEquals(List.of(14, 13), result);
    }

    @Test
    @DisplayName("Devrait retourner les paires dans le bon ordre (haute > basse)")
    void shouldReturnPairsInOrder() {
        List<Card> hand = Arrays.asList(
                new Card(5), new Card(5), new Card(13), new Card(13), new Card(2)
        );
        List<Integer> result = TwoPairsRule.getDoublePairValues(hand);
        assertEquals(List.of(13, 5), result);
    }
}