package fr.pns.rules;
import fr.pns.poker.Card;
import fr.pns.poker.Hand;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

// Import standard
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de test JUnit 5 pour PokerRules et la logique de Hand.
 */
public class PokerRulesTest {




    @Test
    @DisplayName("Test: Doit retourner la valeur la plus haute (8)")
    void testGetHighestValue() {
        int resultat = PokerRules.getHighestValue(5, 8);
        assertEquals(8, resultat, "Echec: Devrait retourner 8");
    }

    @Test
    @DisplayName("Test: Doit retourner 'Égalité' pour 7 et 7")
    void testHandleEquality() {
        String resultat = PokerRules.compareTwoValues(7, 7);
        assertEquals("Égalité", resultat, "Echec: Devrait retourner 'Égalité'");
    }

    @Test
    @DisplayName("Test 2 cartes (List): Main 2 gagne (9 > 8)")
    void testCompareTwoCardHands() {
        // Formatage mis à jour
        List<Card> h1 = new ArrayList<>();
        h1.add(new Card(5));
        h1.add(new Card(8));

        List<Card> h2 = new ArrayList<>();
        h2.add(new Card(3));
        h2.add(new Card(9));

        String resultat = PokerRules.compareTwoCardHands(h1, h2);
        assertEquals("Main 2 gagne", resultat, "Echec: (9 > 8)");
    }

    @Test
    @DisplayName("Test 2 cartes (List): Main 2 gagne au kicker (7 > 5)")
    void testCompareWithKicker() {
        // Formatage mis à jour
        List<Card> h1 = new ArrayList<>();
        h1.add(new Card(5));
        h1.add(new Card(9));

        List<Card> h2 = new ArrayList<>();
        h2.add(new Card(7));
        h2.add(new Card(9));

        String resultat = PokerRules.compareTwoCardHandsWithKicker(h1, h2);
        assertEquals("Main 2 gagne", resultat, "Echec: (kicker 7 > 5)");
    }

    @Test
    @DisplayName("Test 5 cartes (Hand): Main 1 gagne à la carte haute (14 > 13)")
    void testCompare5Cards_Main1GagneCarteHaute() {
        // Formatage mis à jour
        Hand h1 = new Hand();
        h1.addCard(new Card(2));
        h1.addCard(new Card(5));
        h1.addCard(new Card(8));
        h1.addCard(new Card(10));
        h1.addCard(new Card(14));

        Hand h2 = new Hand();
        h2.addCard(new Card(3));
        h2.addCard(new Card(6));
        h2.addCard(new Card(7));
        h2.addCard(new Card(11));
        h2.addCard(new Card(13));

        String resultat = PokerRules.compareHighestCards(h1, h2);
        assertEquals("Main 1 gagne (carte la plus haute : 14)", resultat, "Echec: (14 > 13)");
    }

    @Test
    @DisplayName("Test 5 cartes (Hand): Main 2 gagne au kicker (7 > 5)")
    void testCompare5Cards_Main2GagneKicker() {
        // Formatage mis à jour
        Hand h1 = new Hand();
        h1.addCard(new Card(2));
        h1.addCard(new Card(3));
        h1.addCard(new Card(4));
        h1.addCard(new Card(5));
        h1.addCard(new Card(9));

        Hand h2 = new Hand();
        h2.addCard(new Card(2));
        h2.addCard(new Card(3));
        h2.addCard(new Card(4));
        h2.addCard(new Card(7));
        h2.addCard(new Card(9));

        String resultat = PokerRules.compareHighestCards(h1, h2);
        assertEquals("Main 2 gagne (Kicker : 7)", resultat, "Echec: Kicker (7 > 5)");
    }

    @Test
    @DisplayName("Test 5 cartes (Hand): Égalité parfaite")
    void testCompare5Cards_EgaliteParfaite() {
        // Formatage mis à jour
        Hand h1 = new Hand();
        h1.addCard(new Card(2));
        h1.addCard(new Card(5));
        h1.addCard(new Card(8));
        h1.addCard(new Card(10));
        h1.addCard(new Card(14));

        Hand h2 = new Hand();
        h2.addCard(new Card(14));
        h2.addCard(new Card(10));
        h2.addCard(new Card(8));
        h2.addCard(new Card(5));
        h2.addCard(new Card(2));

        String resultat = PokerRules.compareHighestCards(h1, h2);
        assertEquals("Égalité parfaite !", resultat, "Echec: Egalité");
    }


    // --- Tests pour Hand.hasPair() (Logique Slices 6 & 7) ---

    @Test
    @DisplayName("Test Hand.hasPair (2 cartes): Paire de 7 trouvée")
    void testHasPair_2Cartes_PaireTrouvee() {
        // Formatage mis à jour
        Hand main = new Hand();
        main.addCard(new Card(7));
        main.addCard(new Card(7));
        assertEquals(7,PokerRules.getPair(main.getCards()));
    }

    @Test
    @DisplayName("Test Hand.hasPair (2 cartes): Pas de paire")
    void testHasPair_2Cartes_PasDePaire() {
        // Formatage mis à jour
        Hand main = new Hand();
        main.addCard(new Card(5));
        main.addCard(new Card(8));
        assertEquals(0,PokerRules.getPair(main.getCards()));
    }

    @Test
    @DisplayName("Test Hand.hasPair (5 cartes): Paire de 4 trouvée")
    void testHasPair_5Cartes_PaireTrouvee() {
        // Formatage mis à jour
        Hand main = new Hand();
        main.addCard(new Card(9));
        main.addCard(new Card(4));
        main.addCard(new Card(11));
        main.addCard(new Card(4));
        main.addCard(new Card(13));
        assertEquals(4,PokerRules.getPair(main.getCards()));
    }

    @Test
    @DisplayName("Test Hand.hasPair (5 cartes): Pas de paire")
    void testHasPair_5Cartes_PasDePaire() {
        // Formatage mis à jour
        Hand main = new Hand();
        main.addCard(new Card(2));
        main.addCard(new Card(5));
        main.addCard(new Card(8));
        main.addCard(new Card(10));
        main.addCard(new Card(14));
        assertEquals(0,PokerRules.getPair(main.getCards()));
    }

    // --- Tests pour PokerRules.detectPairInTwoCards (Ancienne logique Slice 6) ---

    @Test
    @DisplayName("Test PokerRules.detectPairInTwoCards: Paire de 7 trouvée")
    void testDetectPairInTwoCards_PaireTrouvee() {
        // Formatage mis à jour
        List<Card> main = new ArrayList<>();
        main.add(new Card(7));
        main.add(new Card(7));
        int resultat = PokerRules.detectPairInTwoCards(main);
        assertEquals(7, resultat, "Echec: Devrait trouver une Paire de 7");
    }

    @Test
    @DisplayName("Test PokerRules.detectPairInTwoCards: Pas de paire")
    void testDetectPairInTwoCards_PasDePaire() {
        // Formatage mis à jour
        List<Card> main = new ArrayList<>();
        main.add(new Card(5));
        main.add(new Card(8));
        int resultat = PokerRules.detectPairInTwoCards(main);
        assertEquals(0, resultat, "Echec: Ne devrait pas trouver de paire");
    }


    @Test
    @DisplayName("compareWith: Paire vs Carte Haute → Paire gagne")
    void testCompareWith_PaireVsCarteHaute() {
        Hand main1 = PokerRules.createHand(5, 5, 2, 3, 4);  // Paire de 5
        Hand main2 = PokerRules.createHand(14, 12, 9, 6, 8); // Carte haute As

        assertEquals("Main 1 gagne (Paire : 5)", PokerRules.compareWith(main1, main2));
    }

    @Test
    @DisplayName("compareWith: Carte Haute vs Paire → Paire gagne")
    void testCompareWith_CarteHauteVsPaire() {
        Hand main1 = PokerRules.createHand(14, 12, 9, 6, 8); // Carte haute As
        Hand main2 = PokerRules.createHand(10, 10, 2, 3, 4); // Paire de 10

        assertEquals("Main 2 gagne (Paire : 10)", PokerRules.compareWith(main1, main2));
    }

    @Test
    @DisplayName("compareWith: Deux paires, paire plus haute gagne")
    void testCompareWith_DeuxPaires_PairePlusHauteGagne() {
        Hand main1 = PokerRules.createHand(10, 10, 2, 3, 4); // Paire de 10
        Hand main2 = PokerRules.createHand(8, 8, 12, 13, 14); // Paire de 8

        assertEquals("Main 1 gagne (Paire plus haute : 10)", PokerRules.compareWith(main1, main2));
    }

    @Test
    @DisplayName("compareWith: Même paire → Égalité")
    void testCompareWith_MemePaire_Egalite() {
        Hand main1 = PokerRules.createHand(7, 7, 2, 3, 4); // Paire de 7
        Hand main2 = PokerRules.createHand(7, 7, 5, 6, 8); // Paire de 7

        assertEquals("Égalité (Paires identiques)", PokerRules.compareWith(main1, main2));
    }

    @Test
    @DisplayName("compareWith: Aucune paire → compare les cartes hautes")
    void testCompareWith_AucunePaire_CarteHauteGagne() {
        Hand main1 = PokerRules.createHand(14, 12, 9, 6, 8); // Carte haute As
        Hand main2 = PokerRules.createHand(13, 12, 9, 6, 8); // Carte haute Roi

        assertEquals("Main 1 gagne (carte la plus haute : 14)", PokerRules.compareWith(main1, main2));
    }





}