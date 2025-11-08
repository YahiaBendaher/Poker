package fr.pns.poker.rules;

import fr.pns.poker.exception.DuplicateCardException;
import fr.pns.poker.exception.InvalidCardFormatException;
import fr.pns.poker.model.Hand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StraightFlushTest {

    // 🔹 CAS 1 : VRAI Straight Flush (classique)
    @Test
    @DisplayName("StraightFlush : main valide (9→R même couleur)")
    void testStraightFlush_Valid() {
        Hand h = Hand.createHand("9Co", "10Co", "VCo", "DCo", "RCo");
        assertEquals(13, StraightFlush.getStraightFlush(h.getCards())); // R = 13
    }

    // 🔹 CAS 2 : A-2-3-4-5 (Straight Flush “basse” avec As)
    @Test
    @DisplayName("StraightFlush : main valide (A-2-3-4-5 même couleur)")
    void testStraightFlush_LowAce() {
        Hand h = Hand.createHand("ACo", "2Co", "3Co", "4Co", "5Co");
        assertEquals(5, StraightFlush.getStraightFlush(h.getCards())); // 5 = carte haute
    }

    // 🔹 CAS 3 : Même couleur mais pas en suite → Flush simple
    @Test
    @DisplayName("StraightFlush : flush sans suite → retour 0")
    void testStraightFlush_NotStraight() {
        Hand h = Hand.createHand("2Co", "5Co", "8Co", "10Co", "RCo");
        assertEquals(0, StraightFlush.getStraightFlush(h.getCards()));
    }

    // 🔹 CAS 4 : Suite correcte mais couleurs différentes → Straight simple
    @Test
    @DisplayName("StraightFlush : suite mais couleurs différentes → retour 0")
    void testStraightFlush_NotFlush() {
        Hand h = Hand.createHand("9Co", "10Ca", "VCo", "DCo", "RCo");
        assertEquals(0, StraightFlush.getStraightFlush(h.getCards()));
    }

    // 🔹 CAS 5 : Pas 5 cartes → invalide
    @Test
    @DisplayName("StraightFlush : moins de 5 cartes → retour 0")
    void testStraightFlush_InvalidCardCount() {
        assertThrows(InvalidCardFormatException.class, () -> {
            // Hand.createHand doit déjà lever l’exception
            Hand h = Hand.createHand("9Co", "10Co", "VCo", "DCo");
            StraightFlush.getStraightFlush(h.getCards());
        });
    }

    // 🔹 CAS 6 : Doublon dans la main
    @Test
    @DisplayName("StraightFlush : doublon dans les cartes → erreur attendue")
    void testStraightFlush_DuplicateCard() {
        assertThrows(DuplicateCardException.class, () -> {
            Hand h = Hand.createHand("9Co", "9Co", "10Co", "VCo", "DCo");
            StraightFlush.getStraightFlush(h.getCards());
        });
    }

    // 🔹 CAS 7 : Suite interrompue (ex : 8-9-10-D-R sans V)
    @Test
    @DisplayName("StraightFlush : suite interrompue → retour 0")
    void testStraightFlush_BrokenSequence() {
        Hand h = Hand.createHand("8Co", "9Co", "10Co", "DCo", "RCo");
        assertEquals(0, StraightFlush.getStraightFlush(h.getCards()));
    }

    // 🔹 CAS 8 : Suite dans un autre ordre (ex : R, D, V, 10, 9)
    @Test
    @DisplayName("StraightFlush : ordre mélangé mais valide → reconnu")
    void testStraightFlush_ShuffledOrder() {
        Hand h = Hand.createHand("RCo", "DCo", "VCo", "10Co", "9Co");
        assertEquals(13, StraightFlush.getStraightFlush(h.getCards()));
    }

    // 🔹 CAS 9 : Suite basse mais couleur différente → doit échouer
    @Test
    @DisplayName("StraightFlush : A-2-3-4-5 couleurs différentes → pas valide")
    void testStraightFlush_LowAceWrongColor() {
        Hand h = Hand.createHand("ACo", "2Ca", "3Co", "4Co", "5Co");
        assertEquals(0, StraightFlush.getStraightFlush(h.getCards()));
    }


    // 🔹 CAS 10 : Main vide
    @Test
    @DisplayName("StraightFlush : main vide → retour 0")
    void testStraightFlush_EmptyHand() {
        Hand h = new Hand();
        assertEquals(0, StraightFlush.getStraightFlush(h.getCards()));
    }


}