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
    public void setWert(int wert) {
        if(wert < 0 || wert >9) return;
        this.wert = wert;
    }

    public Feldgruppe[] getGruppen(){
        return new Feldgruppe[] {zeile,spalte,quadrant};
    }

    public boolean wertIstSetzbar(int wert){
        return (wert > 0 && wert < 9) && !(quadrant.istVorhanden(wert) || spalte.istVorhanden(wert) || zeile.istVorhanden(wert));
    }


}