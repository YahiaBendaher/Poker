package fr.pns.poker.evaluator;

import fr.pns.poker.model.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandComparatorTest {

    @Test
    @DisplayName("Une suite (Straight) bat un brelan (Three of a Kind)")
    void straightShouldBeatThreeOfAKind() {
        Hand straight = Hand.createHand("5Tr", "6Co", "7Pi", "8Ca", "9Tr");   // suite
        Hand threeKind = Hand.createHand("10Tr", "10Co", "10Pi", "3Ca", "2Tr"); // brelan
        String result = HandComparator.compareHands(straight, threeKind);

        assertTrue(result.startsWith("Main 1 gagne")); // car la suite doit battre le brelan
    }

    @Test
    @DisplayName("Brelan bat Double Paire")
    void threeOfAKindShouldBeatTwoPairs() {
        Hand hand1 = Hand.createHand("10Tr", "10Co", "10Pi", "4Ca", "2Tr");
        Hand hand2 = Hand.createHand("ATr", "ACo", "KPi", "KCa", "7Tr");
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Double Paire bat Paire")
    void twoPairsShouldBeatOnePair() {
        Hand hand1 = Hand.createHand("7Tr", "7Co", "2Pi", "2Ca", "10Tr");
        Hand hand2 = Hand.createHand("ATr", "ACo", "10Pi", "9Ca", "7Tr");
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Paire bat Carte Haute")
    void pairShouldBeatHighCard() {
        Hand hand1 = Hand.createHand("2Tr", "2Co", "5Pi", "4Ca", "7Tr");
        Hand hand2 = Hand.createHand("ATr", "KCo", "QPi", "9Ca", "8Tr");
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Paire plus haute gagne")
    void higherPairShouldWin() {
        Hand hand1 = Hand.createHand("KTr", "KCo", "7Pi", "4Ca", "2Tr");
        Hand hand2 = Hand.createHand("10Tr", "10Co", "APi", "QCa", "JTr");
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Double Paire plus haute gagne")
    void higherTwoPairShouldWin() {
        Hand hand1 = Hand.createHand("KTr", "KCo", "7Pi", "7Ca", "2Tr");
        Hand hand2 = Hand.createHand("QTr", "QCo", "JPi", "JCa", "ATr");
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Brelan plus haut gagne")
    void higherThreeOfAKindShouldWin() {
        Hand hand1 = Hand.createHand("5Tr", "5Co", "5Pi", "ACa", "KTr");
        Hand hand2 = Hand.createHand("4Tr", "4Co", "4Pi", "ACa", "KTr");
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Égalité parfaite")
    void shouldBeEqualWithIdenticalHands() {
        Hand hand1 = Hand.createHand("10Tr", "10Co", "7Pi", "4Ca", "2Tr");
        Hand hand2 = Hand.createHand("10Pi", "10Ca", "7Tr", "4Co", "2Pi");
        assertEquals("Egalite", HandComparator.compareHands(hand1, hand2));
    }

    @Test
    @DisplayName("Kicker 1 (Paire): Le premier kicker décide")
    void pairKicker1ShouldDecide() {
        Hand hand1 = Hand.createHand("10Tr", "10Co", "APi", "4Ca", "2Tr");
        Hand hand2 = Hand.createHand("10Pi", "10Ca", "KPi", "4Co", "2Pi");
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Kicker 3 (Paire): Le dernier kicker décide")
    void pairKicker3ShouldDecide() {
        Hand hand1 = Hand.createHand("10Tr", "10Co", "APi", "5Ca", "3Tr");
        Hand hand2 = Hand.createHand("10Pi", "10Ca", "APi", "5Co", "2Pi");
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Kicker (Double Paire): Le 5ème kicker décide")
    void twoPairKickerShouldDecide() {
        Hand hand1 = Hand.createHand("10Tr", "10Co", "8Pi", "8Ca", "ATr");
        Hand hand2 = Hand.createHand("10Pi", "10Ca", "8Tr", "8Co", "7Pi");
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Kicker 2 (Brelan): Le deuxième kicker décide")
    void threeOfAKindKicker2ShouldDecide() {
        Hand hand1 = Hand.createHand("9Tr", "9Co", "9Pi", "ACa", "10Tr");
        Hand hand2 = Hand.createHand("9Pi", "9Ca", "9Tr", "ACo", "8Pi");
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Kicker (Carte Haute): Le 5ème kicker décide")
    void highCardKicker5ShouldDecide() {
        Hand hand1 = Hand.createHand("ATr", "10Co", "8Pi", "7Ca", "3Tr");
        Hand hand2 = Hand.createHand("APi", "10Ca", "8Tr", "7Co", "2Pi");
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Kicker (Paire): Main 2 devrait gagner")
    void pairKickerShouldDecideForHand2() {
        Hand hand1 = Hand.createHand("10Tr", "10Co", "KPi", "4Ca", "2Tr");
        Hand hand2 = Hand.createHand("10Pi", "10Ca", "APi", "4Co", "2Pi");
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 2 gagne"));
    }

    // Nouveaux tests pour la couleur

    @Test
    @DisplayName("Couleur (Flush) bat une Suite (Straight)")
    void flushShouldBeatStraight() {
        Hand flush = Hand.createHand("ATr", "10Tr", "8Tr", "7Tr", "3Tr");
        Hand straight = Hand.createHand("APi", "KCa", "QTr", "JCo", "10Pi");
        String result = HandComparator.compareHands(flush, straight);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Deux Couleurs (Flush): la plus haute carte gagne")
    void higherFlushShouldWin() {
        Hand higherFlush = Hand.createHand("ATr", "10Tr", "8Tr", "7Tr", "3Tr");
        Hand lowerFlush = Hand.createHand("KCo", "10Co", "8Co", "7Co", "3Co");
        String result = HandComparator.compareHands(higherFlush, lowerFlush);
        assertTrue(result.startsWith("Main 1 gagne"));
    }
    
    @Test
    @DisplayName("Deux Couleurs (Flush) identiques: Egalite")
    void equalFlushShouldBeADraw() {
        Hand flush1 = Hand.createHand("ATr", "10Tr", "8Tr", "7Tr", "3Tr");
        Hand flush2 = Hand.createHand("ACo", "10Co", "8Co", "7Co", "3Co");
        String result = HandComparator.compareHands(flush1, flush2);
        assertEquals("Egalite", result);
    }

    @Test
    @DisplayName("Suite Couleur (Straight Flush) bat un Carré (Four of a Kind)")
    void straightFlushShouldBeatFourOfAKind() {
        Hand straightFlush = Hand.createHand("8Tr", "7Tr", "6Tr", "5Tr", "4Tr");
        Hand fourOfAKind = Hand.createHand("ACo", "APi", "ACa", "ATr", "KCo");
        String result = HandComparator.compareHands(straightFlush, fourOfAKind);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Deux Suites Couleurs (Straight Flush): la plus haute carte gagne")
    void higherStraightFlushShouldWin() {
        Hand higherStraightFlush = Hand.createHand("9Tr", "8Tr", "7Tr", "6Tr", "5Tr");
        Hand lowerStraightFlush = Hand.createHand("8Co", "7Co", "6Co", "5Co", "4Co");
        String result = HandComparator.compareHands(higherStraightFlush, lowerStraightFlush);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Suite Couleur Royale (Royal Flush) bat une Suite Couleur (Straight Flush)")
    void royalFlushShouldBeatStraightFlush() {
        Hand royalFlush = Hand.createHand("ATr", "KTr", "QTr", "JTr", "10Tr");
        Hand straightFlush = Hand.createHand("KCo", "QCo", "JCo", "10Co", "9Co");
        String result = HandComparator.compareHands(royalFlush, straightFlush);
        assertTrue(result.startsWith("Main 1 gagne"));
    }
}
