package Sudoku.Feld;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuFeld {
    private Feldgruppe[] quadranten;
    private Feldgruppe[] zeilen;
    private Feldgruppe[] spalten;
    private int groesse;
    private int groesseGruppen;

    public SudokuFeld(int groesse){

        this.groesse = groesse;
        this.groesseGruppen = this.groesse * this.groesse;

        quadranten = new Feldgruppe[this.groesseGruppen];
        zeilen = new Feldgruppe[this.groesseGruppen];
        spalten = new Feldgruppe[this.groesseGruppen];

        for(int i = 0; i < this.groesseGruppen ; i ++){
            quadranten[i] = new Feldgruppe(groesseGruppen);
            zeilen[i] = new Feldgruppe(groesseGruppen);
            spalten[i] = new Feldgruppe(groesseGruppen);
        }

        for(int zeilenIndex = 0; zeilenIndex < this.groesseGruppen; zeilenIndex++){
            for(int spaltenIndex = 0; spaltenIndex < this.groesseGruppen; spaltenIndex++){
                int quadrantenIndex = berechneQuadrantenIndex(zeilenIndex,spaltenIndex);
                Feld aktuellesFeld = new Feld(
                        spalten[spaltenIndex],
                        zeilen[zeilenIndex],
                        quadranten[quadrantenIndex]
                );

                spalten[spaltenIndex].setFeld(zeilenIndex,aktuellesFeld);
                zeilen[zeilenIndex].setFeld(spaltenIndex,aktuellesFeld);

                quadranten[quadrantenIndex].setFeld(quadranten[quadrantenIndex].getNr(),aktuellesFeld);
                quadranten[quadrantenIndex].setNr(quadranten[quadrantenIndex].getNr()+1);

                try {
                    aktuellesFeld.setWert(0);
                } catch (Exception ignore) {
                }
            }
        }

    }

    public boolean setWert(int zeile, int spalte, int wert) throws Exception{
        if(!istKoordinateGueltig(zeile, spalte)) {
            throw new Exception("UngueltigeKoordinatenException");
        }
        getFeld(zeile,spalte).setWert(wert);
        return true;
    }

    public static int berechneQuadrantenIndex(int zeile,int spalte){
        return (((zeile)/3)*3 + ((spalte)/3));
    }

    public boolean istKoordinateGueltig(int zeile, int spalte){
        return zeile >= 0 && spalte >= 0 && zeile <= getGroesseGruppen() && spalte <= getGroesseGruppen();
    }

    public static boolean istWertGueltig(int wert){
        return wert >= 0 && wert <= 9;
    }

    public int getGroesseGruppen() {
        return groesseGruppen;
    }

    public Feldgruppe[] getZeilen() {
        return zeilen;
    }

    public int getZeilenWert(int zeile, int spalte){
        return zeilen[zeile].getFeld(spalte).getWert();
    }

    public int getWert(int zeile, int spalte){
        return zeilen[zeile].getFeld(spalte).getWert();
    }

    public Feldgruppe[] getQuadranten() {
        return quadranten;
    }

    public Feldgruppe[] getSpalten() {
        return spalten;
    }

    public int getGroesse(){
        return groesse;
    }

    public Feld getFeld(int zeile, int spalte){
        return zeilen[zeile].getFeld(spalte);
    }

    public Set<Integer> moeglicheWerte(int zeile, int spalte){
        Set<Integer> optionenZeile = zeilen[zeile].moeglicheWerte();
        Set<Integer> optionenSpalte = spalten[spalte].moeglicheWerte();
        Set<Integer> optionenQuadrant = quadranten[berechneQuadrantenIndex(zeile,spalte)].moeglicheWerte();

        Set<Integer> finaleOptionen = new HashSet<>();

        for(int i = 0; i < getGroesseGruppen(); i++){
            if(optionenZeile.contains(i) && optionenQuadrant.contains(i) && optionenSpalte.contains(i)){
                finaleOptionen.add(i);
            }
        }

        //TODO Duplikate Entfernen

        return finaleOptionen;
    }
}
