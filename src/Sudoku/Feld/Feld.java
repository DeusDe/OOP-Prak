package Sudoku.Feld;

import Sudoku.Exceptions.*;

public class Feld {
    private int wert;
    private final Feldgruppe zeile;
    private final Feldgruppe spalte;
    private final Feldgruppe quadrant;

    private static final FeldBelegtException feldBelegtException = new FeldBelegtException();
    private static final WertInQuadrantVorhandenException wertInQuadrantVorhandenException = new WertInQuadrantVorhandenException();
    private static final WertInSpalteVorhandenException wertInSpalteVorhandenException = new WertInSpalteVorhandenException();
    private static final WertInZeileVorhandenException wertInZeileVorhandenException = new WertInZeileVorhandenException();
    private static final WertebereichUngueltigException wertebereichUngueltigException = new WertebereichUngueltigException();

    /**
     * Erzeugt eine neue Feld-Instanz und weist ihm seine entsprechenden Koordinaten
     * in Form von Zeile, Spalte und Quadrant zu
     * @param spalte Die Spalte, in der das Feld liegt.
     * @param zeile Die Zeile, in der das Feld liegt.
     * @param quadrant Der Quadrant, in dem das Feld liegt.
     */
    public Feld(Feldgruppe spalte, Feldgruppe zeile, Feldgruppe quadrant) {
        this.zeile = zeile;
        this.spalte = spalte;
        this.quadrant = quadrant;
    }

    /**
     * Ermittelt den aktuellen Wert des Feldes und gibt ihn zurück.
     * @return Der aktuelle Wert des Feldes.
     */
    public int getWert() {
        return this.wert;
    }

    private void validateFeldWert(int wert) throws WertVorhandenException {
        if(feldBelegt()) throw feldBelegtException;
        if(wertInQuadrant(wert))throw wertInQuadrantVorhandenException;
        if(wertInSpalte(wert))throw wertInSpalteVorhandenException;
        if(wertInZeile(wert))throw wertInZeileVorhandenException;
    }

    /**
     * Weist dem Feld einen ganzzahligen Wert zu.
     * @param wert Der Wert, der dem Feld zugewiesen wird.
     */
    public boolean setWert(int wert) throws Exception {
        if(wert < 0 || wert >9) throw wertebereichUngueltigException;
        if(wert != 0) validateFeldWert(wert);
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
        return wert > 0 && wert <= 9;
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