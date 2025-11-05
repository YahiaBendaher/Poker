package fr.pns.poker.rules;

import fr.pns.poker.model.Card;
import fr.pns.poker.model.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests pour la règle de la paire (getPair)")
class PairRuleTest {

    @Test
    @DisplayName("Devrait retourner 0 pour un brelan")
    void shouldReturnZeroForThreeOfAKind() {
        List<Card> hand = Hand.createHand("10Tr", "10Co", "10Pi", "5Ca", "2Tr").getCards();
        assertEquals(0, PairRule.getPair(hand));
    }

    @Test
    @DisplayName("Devrait retourner la plus haute paire en cas de deux paires")
    void shouldReturnHighestPairValue() {
        List<Card> hand = Hand.createHand("10Tr", "10Co", "7Pi", "7Ca", "2Tr").getCards();
        assertEquals(10, PairRule.getPair(hand));
    }

    @Test
    @DisplayName("Devrait retourner la paire la plus haute (As)")
    void shouldReturnHighestPairEvenWithLowerPair() {
        List<Card> hand = Hand.createHand("ATr", "ACo", "5Pi", "5Ca", "2Tr").getCards();
        assertEquals(14, PairRule.getPair(hand));
    }

    @Test
    @DisplayName("Devrait retourner la valeur correcte de la paire")
    void shouldReturnCorrectPairValue() {
        List<Card> hand = Hand.createHand("10Tr", "10Co", "7Pi", "5Ca", "2Tr").getCards();
        assertEquals(10, PairRule.getPair(hand));
    }

    @Test
    @DisplayName("Devrait retourner 0 quand il n'y a pas de paire")
    void shouldReturnZeroWhenNoPair() {
        List<Card> hand = Hand.createHand("10Tr", "8Co", "7Pi", "5Ca", "2Tr").getCards();
        assertEquals(0, PairRule.getPair(hand));
    }
}