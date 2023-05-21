package Sudoku.Feld;

public class Feld {
    private int wert;
    private final Feldgruppe zeile;
    private final Feldgruppe spalte;
    private Feldgruppe quadrant;


    /**
     * Erzeugt eine neue Feld-Instanz und weist ihm seine entsprechenden Koordinaten
     * in Form von Zeile, Spalte und Quadrant zu
     * @param neueSpalte Die Spalte, in der das Feld liegt.
     * @param neueZeile Die Zeile, in der das Feld liegt.
     * @param neuerQuadrant Der Quadrant, in dem das Feld liegt.
     */
    public Feld(Feldgruppe neueSpalte, Feldgruppe neueZeile, Feldgruppe neuerQuadrant) {
        this.zeile = neueZeile;
        this.spalte = neueSpalte;
        this.quadrant = neuerQuadrant;
    }

    /**
     * Ermittelt den aktuellen Wert des Feldes und gibt ihn zurück.
     * @return Der aktuelle Wert des Feldes.
     */
    public int getWert() {
        return this.wert;
    }

    /**
     * Weist dem Feld einen ganzzahligen Wert zu.
     * @param wert Der Wert, der dem Feld zugewiesen wird.
     */
    public boolean setWert(int wert) throws Exception {
        if(wert < 0 || wert >9) throw new Exception("WertebereichUngueltigException");
        if(wert != 0){
            if(wertInQuadrant(wert)) throw new Exception("WertInQuadrantVorhandenException");
            if(wertInSpalte(wert)) throw new Exception("WertInSpalteVorhandenException");
            if(wertInZeile(wert)) throw new Exception("WertInZeileVorhandenException");
            if(feldBelegt())  throw new Exception("FeldBelegtException");
        }
        this.wert = wert;
        return true;
    }

    public boolean wertInQuadrant(int wert){
        return quadrant.istVorhanden(wert);
    }

    public boolean wertInZeile(int wert){
        return zeile.istVorhanden(wert);
    }

    public boolean wertInSpalte(int wert){
        return spalte.istVorhanden(wert);
    }

    public boolean wertInGruppen(int wert){
        return  wertInSpalte(wert) || wertInQuadrant(wert) || wertInZeile(wert);
    }

    public boolean wertInBereich(int wert){
        return wert > 0 && wert < 9;
    }

    public boolean feldBelegt(){
        return getWert() > 0;
    }


    public Feldgruppe[] getGruppen(){
        return new Feldgruppe[] {zeile,spalte,quadrant};
    }

    public boolean wertIstSetzbar(int wert){
        return (wertInBereich(wert)) && (!wertInGruppen(wert));
    }


}