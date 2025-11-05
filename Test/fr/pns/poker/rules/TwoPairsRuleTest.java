package fr.pns.poker.rules;

import fr.pns.poker.model.Card;
import fr.pns.poker.model.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests pour la règle des deux paires (getDoublePairValues)")
class TwoPairsRuleTest {

    @Test
    @DisplayName("Devrait retourner une liste [PaireHaute, PaireBasse]")
    void shouldDetectTwoPairs() {
        List<Card> hand = Hand.createHand("10Tr", "10Co", "7Pi", "7Ca", "2Tr").getCards();
        List<Integer> result = TwoPairsRule.getDoublePairValues(hand);
        assertEquals(List.of(10, 7), result);
    }

    @Test
    @DisplayName("Devrait retourner une liste vide pour une seule paire")
    void shouldReturnEmptyListForSinglePair() {
        List<Card> hand = Hand.createHand("10Tr", "10Co", "7Pi", "5Ca", "2Tr").getCards();
        List<Integer> result = TwoPairsRule.getDoublePairValues(hand);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Devrait retourner une liste vide pour un brelan")
    void shouldReturnEmptyListForThreeOfAKind() {
        List<Card> hand = Hand.createHand("10Tr", "10Co", "10Pi", "7Ca", "2Tr").getCards();
        List<Integer> result = TwoPairsRule.getDoublePairValues(hand);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Devrait gérer les As et Rois")
    void shouldHandleAceKingPairs() {
        List<Card> hand = Hand.createHand("ATr", "ACo", "RPi", "RCa", "2Tr").getCards();
        List<Integer> result = TwoPairsRule.getDoublePairValues(hand);
        assertEquals(List.of(14, 13), result);
    }

    @Test
    @DisplayName("Devrait retourner les paires dans le bon ordre (haute > basse)")
    void shouldReturnPairsInOrder() {
        List<Card> hand = Hand.createHand("5Tr", "5Co", "RPi", "RCa", "2Tr").getCards();
        List<Integer> result = TwoPairsRule.getDoublePairValues(hand);
        assertEquals(List.of(13, 5), result);
    }
}