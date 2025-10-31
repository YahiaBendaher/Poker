package fr.pns.poker.evaluator;

import fr.pns.poker.model.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandComparatorTest {



    @Test
    @DisplayName("Brelan bat Double Paire")
    void threeOfAKindShouldBeatTwoPairs() {
        Hand hand1 = Hand.createHand(10, 10, 10, 4, 2);
        Hand hand2 = Hand.createHand(14, 14, 13, 13, 7);
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Double Paire bat Paire")
    void twoPairsShouldBeatOnePair() {
        Hand hand1 = Hand.createHand(7, 7, 2, 2, 10);
        Hand hand2 = Hand.createHand(14, 14, 10, 9, 7);
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Paire bat Carte Haute")
    void pairShouldBeatHighCard() {
        Hand hand1 = Hand.createHand(2, 2, 5, 4, 7);
        Hand hand2 = Hand.createHand(14, 13, 11, 9, 8);
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Paire plus haute gagne")
    void higherPairShouldWin() {
        Hand hand1 = Hand.createHand(13, 13, 7, 4, 2);
        Hand hand2 = Hand.createHand(10, 10, 14, 12, 11);
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Double Paire plus haute gagne")
    void higherTwoPairShouldWin() {
        Hand hand1 = Hand.createHand(13, 13, 7, 7, 2);
        Hand hand2 = Hand.createHand(12, 12, 11, 11, 14);
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Brelan plus haut gagne")
    void higherThreeOfAKindShouldWin() {
        Hand hand1 = Hand.createHand(5, 5, 5, 14, 13);
        Hand hand2 = Hand.createHand(4, 4, 4, 14, 13);
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }


    @Test
    @DisplayName("Égalité parfaite")
    void shouldBeEqualWithIdenticalHands() {
        Hand hand1 = Hand.createHand(10, 10, 7, 4, 2);
        Hand hand2 = Hand.createHand(10, 10, 7, 4, 2);
        assertEquals("Égalité parfaite", HandComparator.compareHands(hand1, hand2));
    }

    @Test
    @DisplayName("Kicker 1 (Paire): Le premier kicker décide")
    void pairKicker1ShouldDecide() {
        Hand hand1 = Hand.createHand(10, 10, 14, 4, 2);
        Hand hand2 = Hand.createHand(10, 10, 13, 4, 2);
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Kicker 3 (Paire): Le dernier kicker décide")
    void pairKicker3ShouldDecide() {
        Hand hand1 = Hand.createHand(10, 10, 14, 5, 3);
        Hand hand2 = Hand.createHand(10, 10, 14, 5, 2);
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Kicker (Double Paire): Le 5ème kicker décide")
    void twoPairKickerShouldDecide() {
        Hand hand1 = Hand.createHand(10, 10, 8, 8, 14);
        Hand hand2 = Hand.createHand(10, 10, 8, 8, 7);
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Kicker 2 (Brelan): Le deuxième kicker décide")
    void threeOfAKindKicker2ShouldDecide() {
        Hand hand1 = Hand.createHand(9, 9, 9, 14, 10);
        Hand hand2 = Hand.createHand(9, 9, 9, 14, 8);
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Kicker (Carte Haute): Le 5ème kicker décide")
    void highCardKicker5ShouldDecide() {
        Hand hand1 = Hand.createHand(14, 10, 8, 7, 3);
        Hand hand2 = Hand.createHand(14, 10, 8, 7, 2);
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }



    @Test
    @DisplayName("Kicker (Paire): Main 2 devrait gagner")
    void pairKickerShouldDecideForHand2() {
        Hand hand1 = Hand.createHand(10, 10, 13, 4, 2);
        Hand hand2 = Hand.createHand(10, 10, 14, 4, 2);
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 2 gagne"));
    }
}