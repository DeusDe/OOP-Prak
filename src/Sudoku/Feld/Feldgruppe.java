package Sudoku.Feld;

import java.util.HashSet;
import java.util.Set;

public class Feldgruppe {
    private int nr;
    private final Feld[] felder;
    private final int groesse;

    /**
     * Erzeugt eine neue Feldgruppen-Instanz mit einer größe von neun Feldern.
     */
    public Feldgruppe (int groesse){
        this.groesse = groesse;
        this.felder = new Feld[groesse];
    }

    /**
     * Weist der Feldgruppe eine Nummer zwischen eins und neun zu.
     * @param nr Die zugewiesene Nummer.
     */
    public void setNr(int nr){
        this.nr = nr;
    }

    /**
     * Ermittelt die Nummer der Feldgruppe und gibt sie zurück.
     * @return Die Nummer der Feldgruppe.
     */
    public int getNr() {
        return nr;
    }

    public Feld getFeld(int index) {
        if (index < 0 || index >= groesse) {
            return null;
        }
        return felder[index];
    }

    public void setFeld(int index, Feld feld) {
        if (index < 0 || index >= groesse) {
            return;
        }
        this.felder[index] = feld;
    }

    public Feld[] getFelder() {
        return felder;
    }

    public boolean istVorhanden(int wert) {
        for(int feldIndex = 0; feldIndex < groesse; feldIndex++){
            if(getFeld(feldIndex).getWert() == wert){
                //Falls der Wert gefunden wurde return True,
                return true;
            }
        }
        //Ansonsten False.
        return false;
    }

    public Set<Integer> moeglicheWerte(){
        Set<Integer> optionen = new HashSet<>();
        for (int i = 1; i <= groesse; i++){
            if (!istVorhanden(i)){
                optionen.add(i);
            }
        }
        return optionen;
    }



}