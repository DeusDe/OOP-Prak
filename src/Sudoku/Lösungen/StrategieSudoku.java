package Sudoku.Lösungen;

import Sudoku.Feld.Feld;
import Sudoku.Feld.Feldgruppe;
import Sudoku.Sudoku;

import java.util.Set;

public class StrategieSudoku extends Sudoku {
    /**
     * Löst das Sudoku mit einer vorgegebenen Strategie
     */
    @Override
    public void loesen() {
        this.zustand = SudokuZustand.Loesungsversuch;
        while (!loesungAbgeschlossen()) {
            schritte++;
            boolean weitererFortschritt;
            weitererFortschritt = loesenMitEinzelnerOption();
            weitererFortschritt |= loesenMitGruppen();
            this.ueberpruefLoesung();
            if (!weitererFortschritt) {
                if (getZustand() == SudokuZustand.Loesungsversuch) {
                    setZustand(SudokuZustand.Unloesbar);
                    return;
                }
            }
        }
    }

    private boolean loesenMitEinzelnerOption() {
        boolean weitererFortschritt = false;

        for (int zeile = 0; zeile < feld.getGroesseGruppen(); zeile++) {
            for (int spalte = 0; spalte < feld.getGroesseGruppen(); spalte++) {
                if (feld.getWert(zeile,spalte) == 0) {
                    Set<Integer> moeglicheOptionen = feld.moeglicheWerte(zeile, spalte);
                    if (moeglicheOptionen.size() == 1) {
                        int loesung = moeglicheOptionen.iterator().next();
                        feld.setWert(zeile,spalte,loesung);
                        weitererFortschritt = true;
                    }
                }
            }
        }

        for (int zeile = 0; zeile < feld.getGroesseGruppen(); zeile++) {
            for (int spalte = 0; spalte < feld.getGroesseGruppen(); spalte++) {
                Feld aktuellesFeld = feld.getFeld(zeile, spalte);
                if (aktuellesFeld.getWert() == 0) {
                    Set<Integer> moeglicheOptionen = feld.moeglicheWerte(zeile, spalte);
                    if (moeglicheOptionen.size() == 1) {
                        int loesung = moeglicheOptionen.iterator().next();
                        feld.setWert(zeile, spalte, loesung);
                        weitererFortschritt = true;
                    }
                }
            }
        }
        return weitererFortschritt;
    }

    private boolean loesenMitGruppen() {
        boolean weitererFortschritt = false;

        for (int zeile = 0; zeile < feld.getGroesseGruppen(); zeile++) {
            for (int spalte = 0; spalte < feld.getGroesseGruppen(); spalte++) {
                if (feld.getWert(zeile,spalte) == 0) {
                    for(Feldgruppe gruppe:feld.getFeld(zeile,spalte).getGruppen()){
                        int[] feldWerteZaehler = new int[getGroesseGruppen()];
                        for (Feld aktuellesFeld : gruppe.getFelder()) {
                            for (int i = 0; i < getGroesseGruppen(); i++) {
                                if (aktuellesFeld.wertIstSetzbar(i+1)) feldWerteZaehler[i]++;
                            }
                        }
                        for (int i = 0; i < getGroesseGruppen(); i++) {
                            if (feldWerteZaehler[i] == 3) {
                                feld.setWert(zeile,spalte,i + 1);
                                weitererFortschritt = true;
                            }
                        }
                    }
                }
            }
        }
        return weitererFortschritt;
    }
}