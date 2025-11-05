package fr.pns.poker.model;


public enum Color {
    TREFLE("Tr"),
    CARREAU("Ca"),
    COEUR("Co"),
    PIQUE("Pi");

    private final String code;

    Color(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }
    public static Color getColorFromCode(String code){
        String lowerCaseCode = code.toLowerCase();
        switch(lowerCaseCode){
            case "tr": return TREFLE;
            case "ca": return CARREAU;
            case "co": return COEUR;
            case "pi": return PIQUE;
            default:
                throw new IllegalArgumentException(code + " est une couleur invalide (attendu Tr, Ca, Co, Pi)");
        }
    }
}
