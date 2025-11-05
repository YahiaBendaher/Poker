package fr.pns.poker.rules;

import fr.pns.poker.model.Card;
import fr.pns.poker.model.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests pour la règle du Three of a Kind (getThreeOfAKind)")
class ThreeOfKindRuleTest {

    @Test
    @DisplayName("Devrait retourner la valeur correcte du Brelan")
    void shouldReturnCorrectThreeOfAKindValue() {
        List<Card> hand = Hand.createHand("10Tr", "10Co", "10Pi", "7Ca", "2Tr").getCards();
        assertEquals(10, ThreeOfKindRule.getThreeOfAKind(hand));
    }

    @Test
    @DisplayName("Devrait retourner 0 pour une simple paire")
    void shouldReturnZeroForPair() {
        List<Card> hand = Hand.createHand("10Tr", "10Co", "7Pi", "5Ca", "2Tr").getCards();
        assertEquals(0, ThreeOfKindRule.getThreeOfAKind(hand));
    }

    @Test
    @DisplayName("Devrait retourner 0 pour un Carré (Four of a kind)")
    void shouldReturnZeroForFourOfAKind() {
        List<Card> hand = Hand.createHand("10Tr", "10Co", "10Pi", "10Ca", "2Tr").getCards();
        assertEquals(0, ThreeOfKindRule.getThreeOfAKind(hand));
    }

    @Test
    @DisplayName("Devrait retourner 0 quand il n'y a pas de Brelan")
    void shouldReturnZeroWhenNoThreeOfAKind() {
        List<Card> hand = Hand.createHand("10Tr", "8Co", "7Pi", "5Ca", "2Tr").getCards();
        assertEquals(0, ThreeOfKindRule.getThreeOfAKind(hand));
    }

    @Test
    @DisplayName("Devrait gérer correctement un Brelan d'As")
    void shouldHandleAceThreeOfAKind() {
        List<Card> hand = Hand.createHand("ATr", "ACo", "APi", "7Ca", "2Tr").getCards();
        assertEquals(14, ThreeOfKindRule.getThreeOfAKind(hand));
    }
}