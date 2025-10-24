package fr.pns.rules;

import fr.pns.poker.Card;
import fr.pns.poker.Hand;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class PokerRulesTest {



    @Test
    @DisplayName("Test 5 Cartes (Main): Gagne à la carte haute (14 > 13)")
    void testHighCard_Hand1Wins() {
        Hand h1 = Hand.createHand(2, 5, 8, 10, 14);
        Hand h2 = Hand.createHand(3, 6, 7, 11, 13);
        String result = PokerRules.compareHighestCards(h1, h2);
        assertEquals("Main 1 gagne (carte la plus haute : 14)", result);
    }

    @Test
    @DisplayName("Test 5 Cartes (Main): Gagne au 3ème kicker (9 > 8)")
    void testHighCard_WinsOnThirdKicker() {
        Hand h1 = Hand.createHand(14, 12, 10, 8, 2);
        Hand h2 = Hand.createHand(14, 12, 10, 9, 3);
        String result = PokerRules.compareHighestCards(h1, h2);
        assertEquals("Main 2 gagne (Kicker : 9)", result);

        Hand h3 = Hand.createHand(14, 12, 10, 8, 7);
        Hand h4 = Hand.createHand(14, 12, 10, 8, 5);
        String result2 = PokerRules.compareHighestCards(h3, h4);
        assertEquals("Main 1 gagne (Kicker : 7)", result2);
    }

    @Test
    @DisplayName("Test 5 Cartes (Main): Égalité parfaite (via compareHighestCards)")
    void testHighCard_PerfectTie() {
        Hand h1 = Hand.createHand(14, 10, 8, 5, 2);
        Hand h2 = Hand.createHand(2, 5, 8, 10, 14);
        String result = PokerRules.compareHighestCards(h1, h2);
        assertEquals("Égalité parfaite !", result);
    }



    @Test
    @DisplayName("Test getHighestPairValue: Détecter la paire la plus haute")
    void testPair_DetectHighestPairIn5Cards() {

        Hand h_pair4 = Hand.createHand(9, 4, 11, 4, 13);
        assertEquals(4, PokerRules.getPair(h_pair4.getCards()));

        Hand h_no_pair = Hand.createHand(2, 3, 5, 8, 14);
        assertEquals(0, PokerRules.getPair(h_no_pair.getCards()));

        Hand h_pair_ace = Hand.createHand(14, 14, 5, 8, 2);
        assertEquals(14, PokerRules.getPair(h_pair_ace.getCards()));
    }

    @Test
    @DisplayName("Test compareHands (Slice 8): Une Paire bat une Carte Haute")
    void testCompareHands_PairBeatsHighCard() {
        Hand h_pair = Hand.createHand(5, 5, 2, 3, 4);
        Hand h_high = Hand.createHand(14, 12, 9, 6, 8);
        String result1 = PokerRules.compareHands(h_pair, h_high);

        assertEquals("Main 1 gagne (Paire : 5)", result1);

        String result2 = PokerRules.compareHands(h_high, h_pair);

        assertEquals("Main 2 gagne (Paire : 5)", result2);
    }

    @Test
    @DisplayName("Test compareHands (Slice 9): Comparer deux Paires différentes")
    void testCompareHands_CompareDifferentPairs() {
        Hand h_pair8 = Hand.createHand(8, 8, 2, 3, 5);
        Hand h_pair6 = Hand.createHand(6, 6, 12, 9, 4);
        String result1 = PokerRules.compareHands(h_pair8, h_pair6);
        assertEquals("Main 1 gagne (Paire plus haute : 8)", result1);

        String result2 = PokerRules.compareHands(h_pair6, h_pair8);
        assertEquals("Main 2 gagne (Paire plus haute : 8)", result2);
    }

    @Test
    @DisplayName("Test compareHands (Slice 10): Comparer les kickers de deux Paires identiques")
    void testCompareHands_IdenticalPairs_Kickers() {
        Hand h1 = Hand.createHand(8, 8, 12, 3, 2);
        Hand h2 = Hand.createHand(8, 8, 10, 9, 4);

        String result = PokerRules.compareHands(h1, h2);

        assertEquals("Main 1 gagne (carte la plus haute : 12)", result);

        h1 = Hand.createHand(5, 5, 9, 8, 2);
        h2 = Hand.createHand(5, 5, 9, 7, 3);

        result = PokerRules.compareHands(h1, h2);
        assertEquals("Main 1 gagne (Kicker : 8)", result);

        h1 = Hand.createHand(10, 10, 14, 9, 5);
        h2 = Hand.createHand(10, 10, 14, 9, 3);

        result = PokerRules.compareHands(h1, h2);
        assertEquals("Main 1 gagne (Kicker : 5)", result);

        h1 = Hand.createHand(8, 8, 12, 3, 2);
        h2 = Hand.createHand(8, 8, 12, 3, 2);

        result = PokerRules.compareHands(h1, h2);
        assertEquals("Égalité parfaite !", result);
    }



    @Test
    @DisplayName("Test (Slice 11): Détecter deux paires dans 4 cartes")
    void testTwoPairs_DetectTwoPairsIn4Cards() {
        Hand h1 = Hand.createHand(5, 5, 8, 8);
        assertTrue(PokerRules.hasTwoPairs4Cards(h1.getCards()));

        Hand h2 = Hand.createHand(5, 5, 7, 8);
        assertFalse(PokerRules.hasTwoPairs4Cards(h2.getCards()));
        Hand h3 = Hand.createHand(5, 6, 7, 8);
        assertFalse(PokerRules.hasTwoPairs4Cards(h3.getCards()));
        Hand h4 = Hand.createHand(5, 5, 5, 8);
        assertFalse(PokerRules.hasTwoPairs4Cards(h4.getCards()));
        Hand h5 = Hand.createHand(5, 5, 5, 5);
        assertFalse(PokerRules.hasTwoPairs4Cards(h5.getCards()));
        Hand h6 = Hand.createHand(5, 5, 8, 8, 2);
        assertFalse(PokerRules.hasTwoPairs4Cards(h6.getCards()));
    }
}