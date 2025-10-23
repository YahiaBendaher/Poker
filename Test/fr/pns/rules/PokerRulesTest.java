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
    @DisplayName("Test: Doit retourner la valeur la plus haute")
    void testGetHighestValue() {
        assertEquals(8, PokerRules.getHighestValue(5, 8));
        assertEquals(10, PokerRules.getHighestValue(10, 3));
        assertEquals(7, PokerRules.getHighestValue(7, 7), "Doit gérer l'égalité");
    }

    @Test
    @DisplayName("Test: Doit gérer l'égalité et l'inégalité")
    void testHandleEquality() {
        assertEquals("Égalité", PokerRules.compareTwoValues(7, 7));
        assertEquals("9", PokerRules.compareTwoValues(4, 9), "Cas M2 gagne");
        assertEquals("11", PokerRules.compareTwoValues(11, 5), "Cas M1 gagne");
    }

    @Test
    @DisplayName("Test 2 Cartes (List): Comparaison carte haute")
    void testCompareTwoCardHands() {
        // M2 gagne (9 > 8)
        List<Card> h1 = new ArrayList<>();
        h1.add(new Card(5));
        h1.add(new Card(8));
        List<Card> h2 = new ArrayList<>();
        h2.add(new Card(3));
        h2.add(new Card(9));
        assertEquals("Main 2 gagne", PokerRules.compareTwoCardHands(h1, h2));

        // M1 gagne (10 > 9)
        List<Card> h3 = new ArrayList<>();
        h3.add(new Card(10));
        h3.add(new Card(2));
        List<Card> h4 = new ArrayList<>();
        h4.add(new Card(9));
        h4.add(new Card(5));
        assertEquals("Main 1 gagne", PokerRules.compareTwoCardHands(h3, h4));

        // Égalité (pas de kicker pour cette slice)
        List<Card> h5 = new ArrayList<>();
        h5.add(new Card(10));
        h5.add(new Card(2));
        List<Card> h6 = new ArrayList<>();
        h6.add(new Card(10));
        h6.add(new Card(5));
        assertEquals("Égalité", PokerRules.compareTwoCardHands(h5, h6));
    }

    @Test
    @DisplayName("Test 2 Cartes (List): Comparaison au kicker")
    void testCompareTwoCardHands_Hand2WinsOnKicker() {
        // M2 gagne (kicker 7 > 5)
        List<Card> h1 = new ArrayList<>();
        h1.add(new Card(5));
        h1.add(new Card(9));
        List<Card> h2 = new ArrayList<>();
        h2.add(new Card(7));
        h2.add(new Card(9));
        assertEquals("Main 2 gagne", PokerRules.compareTwoCardHandsWithKicker(h1, h2));

        // M1 gagne (kicker 4 > 2)
        List<Card> h3 = new ArrayList<>();
        h3.add(new Card(11));
        h3.add(new Card(4));
        List<Card> h4 = new ArrayList<>();
        h4.add(new Card(11));
        h4.add(new Card(2));
        assertEquals("Main 1 gagne", PokerRules.compareTwoCardHandsWithKicker(h3, h4));

        // Égalité
        List<Card> h5 = new ArrayList<>();
        h5.add(new Card(13));
        h5.add(new Card(8));
        List<Card> h6 = new ArrayList<>();
        h6.add(new Card(8));
        h6.add(new Card(13));
        assertEquals("Égalité", PokerRules.compareTwoCardHandsWithKicker(h5, h6));
    }

    @Test
    @DisplayName("Test 5 Cartes (Main): Gagne à la carte haute (14 > 13)")
    void testHighCard_Hand1Wins() {
        Hand h1 = PokerRules.createHand(2, 5, 8, 10, 14);
        Hand h2 = PokerRules.createHand(3, 6, 7, 11, 13);
        String result = PokerRules.compareHighestCards(h1, h2);
        assertEquals("Main 1 gagne (carte la plus haute : 14)", result);
    }

    @Test
    @DisplayName("Test 5 Cartes (Main): Gagne au 3ème kicker (9 > 8)")
    void testHighCard_WinsOnThirdKicker() {
        // Cartes hautes 14, 12, 10 sont égales. M2 gagne (9 > 8).
        Hand h1 = PokerRules.createHand(14, 12, 10, 8, 2);
        Hand h2 = PokerRules.createHand(14, 12, 10, 9, 3);
        String result = PokerRules.compareHighestCards(h1, h2);
        assertEquals("Main 2 gagne (Kicker : 9)", result);

        // M1 gagne au dernier kicker (7 > 5)
        Hand h3 = PokerRules.createHand(14, 12, 10, 8, 7);
        Hand h4 = PokerRules.createHand(14, 12, 10, 8, 5);
        String result2 = PokerRules.compareHighestCards(h3, h4);
        assertEquals("Main 1 gagne (Kicker : 7)", result2);
    }

    @Test
    @DisplayName("Test 5 Cartes (Main): Égalité parfaite")
    void testHighCard_PerfectTie() {
        Hand h1 = PokerRules.createHand(14, 10, 8, 5, 2);
        Hand h2 = PokerRules.createHand(2, 5, 8, 10, 14);
        String result = PokerRules.compareHighestCards(h1, h2);
        assertEquals("Égalité parfaite !", result);
    }


    @Test
    @DisplayName("Test detectPairInTwoCards: Paire trouvée / non trouvée")
    void testDetectPairIn2Cards() {
        List<Card> pair = new ArrayList<>();
        pair.add(new Card(7));
        pair.add(new Card(7));
        assertEquals(7, PokerRules.detectPairInTwoCards(pair), "Doit trouver Paire de 7");

        List<Card> noPair = new ArrayList<>();
        noPair.add(new Card(5));
        noPair.add(new Card(8));
        assertEquals(0, PokerRules.detectPairInTwoCards(noPair), "Ne doit pas trouver de paire");
    }

    @Test
    @DisplayName("Test getPair (Slice 7): Détecter une paire dans 5 cartes")
    void testPair_DetectPairIn5Cards() {
        // [9, 4, 11, 4, 13] -> Paire de 4
        Hand h_pair = PokerRules.createHand(9, 4, 11, 4, 13);
        assertEquals(4, PokerRules.getPair(h_pair.getCards()), "Doit trouver Paire de 4");

        // [2, 3, 5, 8, 14] -> Pas de Paire
        Hand h_no_pair = PokerRules.createHand(2, 3, 5, 8, 14);
        assertEquals(0, PokerRules.getPair(h_no_pair.getCards()), "Ne doit pas trouver de paire");

        // Paire au début
        Hand h_pair_start = PokerRules.createHand(10, 10, 5, 8, 14);
        assertEquals(10, PokerRules.getPair(h_pair_start.getCards()), "Doit trouver Paire de 10");

        // Paire à la fin
        Hand h_pair_end = PokerRules.createHand(2, 3, 5, 14, 14);
        assertEquals(14, PokerRules.getPair(h_pair_end.getCards()), "Doit trouver Paire de 14");
    }

    @Test
    @DisplayName("Test compareWith (Slice 8): Une Paire bat une Carte Haute")
    void testPair_PairBeatsHighCard() {
        Hand h_pair = PokerRules.createHand(5, 5, 2, 3, 4);
        Hand h_high = PokerRules.createHand(14, 12, 9, 6, 8);
        String result1 = PokerRules.compareWith(h_pair, h_high);
        assertEquals("Main 1 gagne (Paire : 5)", result1, "La Paire de 5 doit gagner");

        // Test inversé
        String result2 = PokerRules.compareWith(h_high, h_pair);
        assertEquals("Main 2 gagne (Paire : 5)", result2, "La Paire de 5 doit gagner (inversé)");

        // Paire basse vs As haut
        Hand h_pair_low = PokerRules.createHand(2, 2, 3, 4, 5);
        Hand h_ace_high = PokerRules.createHand(14, 13, 12, 11, 9);
        String result3 = PokerRules.compareWith(h_pair_low, h_ace_high);
        assertEquals("Main 1 gagne (Paire : 2)", result3, "La Paire de 2 bat la carte haute As");
    }

    @Test
    @DisplayName("Test compareWith (Slice 9): Comparer deux Paires différentes")
    void testPair_CompareDifferentPairs() {
        Hand h_pair8 = PokerRules.createHand(8, 8, 2, 3, 5);
        Hand h_pair6 = PokerRules.createHand(6, 6, 12, 9, 4);
        String result1 = PokerRules.compareWith(h_pair8, h_pair6);
        assertEquals("Main 1 gagne (Paire plus haute : 8)", result1, "Paire de 8 > Paire de 6");

        // Test inversé
        String result2 = PokerRules.compareWith(h_pair6, h_pair8);
        assertEquals("Main 2 gagne (Paire plus haute : 8)", result2, "Paire de 8 > Paire de 6 (inversé)");

        // Paire d'As vs Paire de Rois
        Hand h_pair_ace = PokerRules.createHand(14, 14, 2, 3, 5);
        Hand h_pair_king = PokerRules.createHand(13, 13, 10, 9, 4);
        String result3 = PokerRules.compareWith(h_pair_ace, h_pair_king);
        assertEquals("Main 1 gagne (Paire plus haute : 14)", result3, "Paire de 14 > Paire de 13");
    }

    @Test
    @DisplayName("Test compareWith (Slice 9): Paires identiques (Égalité)")
    void testPair_IdenticalPairsShouldTie() {
        // Test pour la Slice 9 : deux paires identiques SANS kicker doivent être égales
        Hand h1 = PokerRules.createHand(8, 8, 12, 3, 2);
        Hand h2 = PokerRules.createHand(8, 8, 10, 9, 4);
        String result = PokerRules.compareWith(h1, h2);
        assertEquals("Égalité (Paires identiques)", result, "Slice 9: Doit retourner Égalité (kicker non implémenté)");
    }
}