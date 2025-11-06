package fr.pns.poker.model;

public class CardSeperator {

    private CardSeperator(){}
    private static final String INVALID_FORMAT_MSG = "Types de Cartes invalides! (Format attendu: Valeur+Couleur, ex: 2Tr, 10Ca, VCo, RPi, ATr)";

    public static Card seperateCard(String cardString){
        if(cardString==null) throw new IllegalArgumentException(INVALID_FORMAT_MSG);
        String cs = cardString.trim();
        if(cs.isEmpty()) throw new IllegalArgumentException(INVALID_FORMAT_MSG);

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
        Value v = Value.getValueFromSymbol(valuePart);
        Color c = Color.getColorFromCode(colorPart);
        return new Card(v,c);
    }
}
