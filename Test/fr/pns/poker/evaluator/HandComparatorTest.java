package fr.pns.poker.evaluator;

import fr.pns.poker.model.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandComparatorTest {

    @Test
    @DisplayName("Couleur : Main 1 gagne avec la carte la plus haute")
    void flushHighCard_Main1Wins() {
        // Main 1 : Couleur (Pi) avec As en carte haute
        Hand hand1 = Hand.createHand("2Pi", "5Pi", "9Pi", "10Pi", "APi");
        // Main 2 : Couleur (Co) avec Roi en carte haute
        Hand hand2 = Hand.createHand("2Co", "5Co", "8Co", "9Co", "RCo");

        String result = HandComparator.compareHands(hand1, hand2);

        assertTrue(result.startsWith("Main 1 gagne"),
                "La main 1 (As haut) devrait battre la main 2 (Roi haut)");
    }

    @Test
    @DisplayName("Couleur : Égalité parfaite entre deux mains identiques")
    void flushHighCard_PerfectTie() {
        // Deux mains identiques : même couleur et mêmes valeurs
        Hand hand1 = Hand.createHand("2Pi", "5Pi", "8Pi", "10Pi", "RPi");
        Hand hand2 = Hand.createHand("2Pi", "5Pi", "8Pi", "10Pi", "RPi");

        String result = HandComparator.compareHands(hand1, hand2);

        assertTrue(result.toLowerCase().contains("egalite"),
                "Deux couleurs identiques doivent donner une égalité parfaite");
    }

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
        Hand hand2 = Hand.createHand("ATr", "ACo", "RPi", "RCa", "7Tr");
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
        Hand hand2 = Hand.createHand("ATr", "RCo", "DPi", "9Ca", "8Tr");
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Paire plus haute gagne")
    void higherPairShouldWin() {
        Hand hand1 = Hand.createHand("RTr", "RCo", "7Pi", "4Ca", "2Tr");
        Hand hand2 = Hand.createHand("10Tr", "10Co", "APi", "DCa", "VTr");
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Double Paire plus haute gagne")
    void higherTwoPairShouldWin() {
        Hand hand1 = Hand.createHand("RTr", "RCo", "7Pi", "7Ca", "2Tr");
        Hand hand2 = Hand.createHand("DTr", "DCo", "VPi", "VCa", "ATr");
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 1 gagne"));
    }

    @Test
    @DisplayName("Brelan plus haut gagne")
    void higherThreeOfAKindShouldWin() {
        Hand hand1 = Hand.createHand("5Tr", "5Co", "5Pi", "ACa", "RTr");
        Hand hand2 = Hand.createHand("4Tr", "4Co", "4Pi", "ACa", "RTr");
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
        Hand hand2 = Hand.createHand("10Pi", "10Ca", "RPi", "4Co", "2Pi");
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
        Hand hand1 = Hand.createHand("10Tr", "10Co", "RPi", "4Ca", "2Tr");
        Hand hand2 = Hand.createHand("10Pi", "10Ca", "APi", "4Co", "2Pi");
        String result = HandComparator.compareHands(hand1, hand2);
        assertTrue(result.startsWith("Main 2 gagne"));
    }
    @Test
    @DisplayName("Full House bat une Couleur")
    void testFullHouseBeatsFlush() {
        Hand full = Hand.createHand("10Tr", "10Co", "10Pi", "8Ca", "8Tr");
        Hand flush = Hand.createHand("10Pi", "8Pi", "APi", "5Pi", "2Pi");

        String res = HandComparator.compareHands(full, flush);
        assertEquals("Main 1 gagne avec un FULL, (Brelan de 10, Paire de 8)", res);
    }
}
