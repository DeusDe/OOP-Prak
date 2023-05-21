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

        for (int zeile = 0; zeile < sudokuFeld.getGroesseGruppen(); zeile++) {
            for (int spalte = 0; spalte < sudokuFeld.getGroesseGruppen(); spalte++) {
                if (sudokuFeld.getWert(zeile,spalte) == 0) {
                    Set<Integer> moeglicheOptionen = sudokuFeld.moeglicheWerte(zeile, spalte);
                    if (moeglicheOptionen.size() == 1) {
                        int loesung = moeglicheOptionen.iterator().next();
                        try{
                            sudokuFeld.setWert(zeile,spalte,loesung);}
                        catch (Exception ignored){}
                        weitererFortschritt = true;
                    }
                }
            }
        }

        for (int zeile = 0; zeile < sudokuFeld.getGroesseGruppen(); zeile++) {
            for (int spalte = 0; spalte < sudokuFeld.getGroesseGruppen(); spalte++) {
                Feld aktuellesFeld = sudokuFeld.getFeld(zeile, spalte);
                if (aktuellesFeld.getWert() == 0) {
                    Set<Integer> moeglicheOptionen = sudokuFeld.moeglicheWerte(zeile, spalte);
                    if (moeglicheOptionen.size() == 1) {
                        int loesung = moeglicheOptionen.iterator().next();
                        try{
                            sudokuFeld.setWert(zeile, spalte, loesung);}
                        catch (Exception ignored){}
                        weitererFortschritt = true;
                    }
                }
            }
        }
        return weitererFortschritt;
    }

    private boolean loesenMitGruppen() {
        boolean weitererFortschritt = false;

        for (int zeile = 0; zeile < sudokuFeld.getGroesseGruppen(); zeile++) {
            for (int spalte = 0; spalte < sudokuFeld.getGroesseGruppen(); spalte++) {
                if (sudokuFeld.getWert(zeile,spalte) == 0) {
                    for(Feldgruppe gruppe: sudokuFeld.getFeld(zeile,spalte).getGruppen()){
                        int[] feldWerteZaehler = new int[getGroesseGruppen()];
                        for (Feld aktuellesFeld : gruppe.getFelder()) {
                            for (int i = 0; i < getGroesseGruppen(); i++) {
                                if (aktuellesFeld.wertIstSetzbar(i+1)) feldWerteZaehler[i]++;
                            }
                        }
                        for (int i = 0; i < getGroesseGruppen(); i++) {
                            if (feldWerteZaehler[i] == 3) {
                                try{
                                    sudokuFeld.setWert(zeile,spalte,i + 1);}
                                catch (Exception ignored){}
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