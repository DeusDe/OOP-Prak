package Sudoku.Lösungen;

import Sudoku.Feld.Feld;
import Sudoku.Feld.Feldgruppe;
import Sudoku.Sudoku;

import java.util.Set;

public class Strategie implements LösungsStrategie {
    @Override
    public void lösen(Sudoku sudoku) {
        sudoku.setZustand(SudokuZustand.Loesungsversuch);
        while (!sudoku.loesungAbgeschlossen()) {
            sudoku.schritte++;
            boolean weitererFortschritt;
            weitererFortschritt = loesenMitEinzelnerOption(sudoku);
            weitererFortschritt |= loesenMitGruppen(sudoku);
            sudoku.ueberpruefLoesung();
            if (!weitererFortschritt) {
                if (sudoku.getZustand() == SudokuZustand.Loesungsversuch) {
                    sudoku.setZustand(SudokuZustand.Unloesbar);
                    return;
                }
            }
        }
    }

    private boolean loesenMitEinzelnerOption(Sudoku sudoku) {
        boolean weitererFortschritt = false;

        for (int zeile = 0; zeile < sudoku.getSudokuFeld().getGroesseGruppen(); zeile++) {
            for (int spalte = 0; spalte < sudoku.getSudokuFeld().getGroesseGruppen(); spalte++) {
                if (sudoku.getSudokuFeld().getWert(zeile,spalte) == 0) {
                    Set<Integer> moeglicheOptionen = sudoku.getSudokuFeld().moeglicheWerte(zeile, spalte);
                    if (moeglicheOptionen.size() == 1) {
                        int loesung = moeglicheOptionen.iterator().next();
                        try{
                            sudoku.getSudokuFeld().setWert(zeile,spalte,loesung);}
                        catch (Exception ignored){}
                        weitererFortschritt = true;
                    }
                }
            }
        }

        for (int zeile = 0; zeile < sudoku.getSudokuFeld().getGroesseGruppen(); zeile++) {
            for (int spalte = 0; spalte < sudoku.getSudokuFeld().getGroesseGruppen(); spalte++) {
                Feld aktuellesFeld = sudoku.getSudokuFeld().getFeld(zeile, spalte);
                if (aktuellesFeld.getWert() == 0) {
                    Set<Integer> moeglicheOptionen = sudoku.getSudokuFeld().moeglicheWerte(zeile, spalte);
                    if (moeglicheOptionen.size() == 1) {
                        int loesung = moeglicheOptionen.iterator().next();
                        try{
                            sudoku.getSudokuFeld().setWert(zeile, spalte, loesung);}
                        catch (Exception ignored){}
                        weitererFortschritt = true;
                    }
                }
            }
        }
        return weitererFortschritt;
    }

    private boolean loesenMitGruppen(Sudoku sudoku) {
        boolean weitererFortschritt = false;

        for (int zeile = 0; zeile < sudoku.getSudokuFeld().getGroesseGruppen(); zeile++) {
            for (int spalte = 0; spalte < sudoku.getSudokuFeld().getGroesseGruppen(); spalte++) {
                if (sudoku.getSudokuFeld().getWert(zeile,spalte) == 0) {
                    for(Feldgruppe gruppe: sudoku.getSudokuFeld().getFeld(zeile,spalte).getGruppen()){
                        int[] feldWerteZaehler = new int[sudoku.getGroesseGruppen()];
                        for (Feld aktuellesFeld : gruppe.getFelder()) {
                            for (int i = 0; i < sudoku.getGroesseGruppen(); i++) {
                                if (aktuellesFeld.wertIstSetzbar(i+1)) feldWerteZaehler[i]++;
                            }
                        }
                        for (int i = 0; i < sudoku.getGroesseGruppen(); i++) {
                            if (feldWerteZaehler[i] == 3) {
                                try{
                                    sudoku.getSudokuFeld().setWert(zeile,spalte,i + 1);}
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
