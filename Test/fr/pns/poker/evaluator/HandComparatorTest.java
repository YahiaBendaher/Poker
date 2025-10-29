package fr.pns.poker.evaluator;

import fr.pns.poker.model.Card;
import fr.pns.poker.model.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandComparatorTest {

    @Test
    @DisplayName("Une paire devrait gagner contre une carte haute")
    void pairShouldBeatHighCard() {
        Hand hand1 = new Hand();
        hand1.addCard(new Card(10));
        hand1.addCard(new Card(10));
        hand1.addCard(new Card(5));
        hand1.addCard(new Card(4));
        hand1.addCard(new Card(2));

        Hand hand2 = new Hand();
        hand2.addCard(new Card(14)); // As
        hand2.addCard(new Card(13)); // Roi
        hand2.addCard(new Card(11));
        hand2.addCard(new Card(9));
        hand2.addCard(new Card(7));

        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Deux paires devraient gagner contre une paire")
    void twoPairsShouldBeatOnePair() {
        Hand hand1 = new Hand();
        hand1.addCard(new Card(10));
        hand1.addCard(new Card(10));
        hand1.addCard(new Card(7));
        hand1.addCard(new Card(7));
        hand1.addCard(new Card(2));

        Hand hand2 = new Hand();
        hand2.addCard(new Card(14)); // Paire d'As
        hand2.addCard(new Card(14));
        hand2.addCard(new Card(10));
        hand2.addCard(new Card(9));
        hand2.addCard(new Card(7));

        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Un brelan devrait gagner contre deux paires")
    void threeOfAKindShouldBeatTwoPairs() {
        Hand hand1 = new Hand();
        hand1.addCard(new Card(10));
        hand1.addCard(new Card(10));
        hand1.addCard(new Card(10));
        hand1.addCard(new Card(4));
        hand1.addCard(new Card(2));

        Hand hand2 = new Hand();
        hand2.addCard(new Card(14));
        hand2.addCard(new Card(14));
        hand2.addCard(new Card(13));
        hand2.addCard(new Card(13));
        hand2.addCard(new Card(7));

        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Entre deux paires identiques, le kicker devrait décider")
    void kickerShouldDecideBetweenEqualPairs() {
        Hand hand1 = new Hand();
        hand1.addCard(new Card(10));
        hand1.addCard(new Card(10));
        hand1.addCard(new Card(14)); // As kicker
        hand1.addCard(new Card(4));
        hand1.addCard(new Card(2));

        Hand hand2 = new Hand();
        hand2.addCard(new Card(10));
        hand2.addCard(new Card(10));
        hand2.addCard(new Card(13)); // Roi kicker
        hand2.addCard(new Card(4));
        hand2.addCard(new Card(2));

        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Entre deux mains identiques, devrait être égalité")
    void shouldBeEqualWithIdenticalHands() {
        Hand hand1 = new Hand();
        hand1.addCard(new Card(10));
        hand1.addCard(new Card(10));
        hand1.addCard(new Card(7));
        hand1.addCard(new Card(4));
        hand1.addCard(new Card(2));

        Hand hand2 = new Hand();
        hand2.addCard(new Card(10));
        hand2.addCard(new Card(10));
        hand2.addCard(new Card(7));
        hand2.addCard(new Card(4));
        hand2.addCard(new Card(2));

        assertEquals("Égalité parfaite", HandComparator.compareHands(hand1, hand2));
    }

    @Test
    @DisplayName("Entre deux paires différentes, la plus haute devrait gagner")
    void higherPairShouldWin() {
        Hand hand1 = new Hand();
        hand1.addCard(new Card(13)); // Paire de Rois
        hand1.addCard(new Card(13));
        hand1.addCard(new Card(7));
        hand1.addCard(new Card(4));
        hand1.addCard(new Card(2));

        Hand hand2 = new Hand();
        hand2.addCard(new Card(10)); // Paire de 10
        hand2.addCard(new Card(10));
        hand2.addCard(new Card(14)); // As kicker
        hand2.addCard(new Card(4));
        hand2.addCard(new Card(2));

        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Entre deux doubles paires, la plus haute paire devrait décider")
    void higherTwoPairShouldWin() {
        Hand hand1 = new Hand();
        hand1.addCard(new Card(13)); // Rois
        hand1.addCard(new Card(13));
        hand1.addCard(new Card(7));
        hand1.addCard(new Card(7));
        hand1.addCard(new Card(2));

        Hand hand2 = new Hand();
        hand2.addCard(new Card(12)); // Dames
        hand2.addCard(new Card(12));
        hand2.addCard(new Card(11));
        hand2.addCard(new Card(11));
        hand2.addCard(new Card(14)); // As kicker

        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

}