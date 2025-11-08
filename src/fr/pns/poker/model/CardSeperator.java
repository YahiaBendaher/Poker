package fr.pns.poker.model;

import fr.pns.poker.exception.InvalidCardFormatException;
import fr.pns.poker.exception.InvalidValueException;

public class CardSeperator {

    private CardSeperator(){}

    public static Card seperateCard(String cardString){
        if (cardString == null || cardString.isBlank()) {
            throw new InvalidCardFormatException("Carte vide : Format attendu (ex : 2Tr, 10Ca, VCo, RPi, ATr)");
        }
        String cs = cardString.trim();

        //séparer valeur et couleur
        String valuePart;
        String colorPart;
        if(cs.startsWith("10")){
            valuePart = "10";
            colorPart = cs.substring(2).trim();
        }else{
            valuePart = cs.substring(0,1);
            colorPart = cs.substring(1).trim();
        }

        // Vérification de complétude
        if (valuePart.isEmpty() || colorPart.isEmpty()) {
            throw new InvalidCardFormatException("Carte incomplète : valeur ou couleur manquante (ex : 10Ca, VCo, RPi, ATr)");
        }

        String lowerCs = cs.toLowerCase();
        if (lowerCs.equals("tr") || lowerCs.equals("ca") || lowerCs.equals("co") || lowerCs.equals("pi")) {
            throw new InvalidValueException();
        }
        Value v = Value.getValueFromSymbol(valuePart);
        Color c = Color.getColorFromCode(colorPart);
        return new Card(v,c);
    }
}
