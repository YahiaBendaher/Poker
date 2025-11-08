package fr.pns.poker.rules;

import fr.pns.poker.model.Card;
import fr.pns.poker.model.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests pour la règle du Four of a Kind (getFourOfAKind)")
class FourOfKindRuleTest {

    @Test
    @DisplayName("Devrait détecter un Carré de 7")
    void shouldDetectFourOfAKindOfSevens() {
        List<Card> hand = Hand.createHand("7Pi", "7Co", "7Tr", "7Ca", "2Co").getCards();
        assertEquals(7, FourOfKindRule.getFourOfAKind(hand));
    }

    @Test
    @DisplayName("Devrait retourner la valeur correcte du Carré")
    void shouldReturnCorrectFourOfAKindValue() {
        List<Card> hand = Hand.createHand("10Tr", "10Co", "10Pi", "10Ca", "2Tr").getCards();
        assertEquals(10, FourOfKindRule.getFourOfAKind(hand));
    }

    @Test
    @DisplayName("Devrait retourner 0 pour un Brelan")
    void shouldReturnZeroForThreeOfAKind() {
        List<Card> hand = Hand.createHand("10Tr", "10Co", "10Pi", "7Ca", "2Tr").getCards();
        assertEquals(0, FourOfKindRule.getFourOfAKind(hand));
    }

    @Test
    @DisplayName("Devrait retourner 0 pour une simple paire")
    void shouldReturnZeroForPair() {
        List<Card> hand = Hand.createHand("10Tr", "10Co", "7Pi", "5Ca", "2Tr").getCards();
        assertEquals(0, FourOfKindRule.getFourOfAKind(hand));
    }

    @Test
    @DisplayName("Devrait retourner 0 quand il n'y a pas de Carré")
    void shouldReturnZeroWhenNoFourOfAKind() {
        List<Card> hand = Hand.createHand("10Tr", "8Co", "7Pi", "5Ca", "2Tr").getCards();
        assertEquals(0, FourOfKindRule.getFourOfAKind(hand));
    }

    @Test
    @DisplayName("Devrait gérer correctement un Carré d'As")
    void shouldHandleAceFourOfAKind() {
        List<Card> hand = Hand.createHand("ATr", "ACo", "APi", "ACa", "2Tr").getCards();
        assertEquals(14, FourOfKindRule.getFourOfAKind(hand));
    }

    @Test
    @DisplayName("Devrait gérer correctement un Carré de 2")
    void shouldHandleTwoFourOfAKind() {
        List<Card> hand = Hand.createHand("2Tr", "2Co", "2Pi", "2Ca", "ATr").getCards();
        assertEquals(2, FourOfKindRule.getFourOfAKind(hand));
    }
}

