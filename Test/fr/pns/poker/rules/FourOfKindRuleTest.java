package fr.pns.poker.rules;

import fr.pns.poker.model.Card;
import fr.pns.poker.model.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests pour la règle du Four of a Kind (getFourOfAKind)")
class FourOfKindRuleTest {

    // Test 1: Détection d'un carré (valeur intermédiaire)
    @Test
    @DisplayName("Carré détecté (7)")
    void shouldDetectFourOfAKindOfSevens() {
        List<Card> hand = Hand.createHand("7Pi", "7Co", "7Tr", "7Ca", "2Co").getCards();
        assertEquals(7, FourOfKindRule.getFourOfAKind(hand));
    }

    // Test 2: Cas négatif (aucun carré) -> doit retourner 0
    @Test
    @DisplayName("Retourne 0 s'il n'y a pas de carré")
    void shouldReturnZeroWhenNoFourOfAKind() {
        List<Card> hand = Hand.createHand("10Tr", "10Co", "10Pi", "7Ca", "2Tr").getCards(); // brelan
        assertEquals(0, FourOfKindRule.getFourOfAKind(hand));
    }

    // Test 3: Borne haute (Carré d'As)
    @Test
    @DisplayName("Carré d'As (valeur 14)")
    void shouldHandleAceFourOfAKind() {
        List<Card> hand = Hand.createHand("ATr", "ACo", "APi", "ACa", "2Tr").getCards();
        assertEquals(14, FourOfKindRule.getFourOfAKind(hand));
    }

    // Test 4: Borne basse (Carré de 2)
    @Test
    @DisplayName("Carré de 2 (valeur 2)")
    void shouldHandleTwoFourOfAKind() {
        List<Card> hand = Hand.createHand("2Tr", "2Co", "2Pi", "2Ca", "ATr").getCards();
        assertEquals(2, FourOfKindRule.getFourOfAKind(hand));
    }
}
