package Sudoku.Lösungen;

import Sudoku.Feld.Feld;
import Sudoku.Sudoku;

public class ProbierSudoku extends Sudoku {

    /**
     * Loest das Sudoku und gibt die Lösung aus.
     */
    @Override
    public void loesen() {
        loesenRekursiv(0, 0 );
        ueberpruefLoesung();
    }

    /**
     * Rekursiver Algorithmus zum Lösen des Sudoku
     * @param zeile
     * @param spalte
     * @return
     */
    private boolean loesenRekursiv(int zeile, int spalte) {
        schritte++;
        //Wenn das Sudoku in Zeile 9 ist, ist die Loesung abgeschlossen.
        if (zeile == 9) {
            return true;
        }
        if (spalte == 9) {
            //Wenn das Sudoku in Spalte 9 ist, wird eine Rekursiver aufruf in die nächste zeile in spalte 0 gestartet.
            return loesenRekursiv(zeile + 1, 0);
        }
        if (sudokuFeld.getZeilenWert(zeile,spalte) != 0) {
            //Falls schon ein Wert existiert, wird eine spalten weiter gegangen.
            return loesenRekursiv(zeile, spalte + 1);
        }
        for (int aktuellerWert = 1; aktuellerWert <= sudokuFeld.getGroesseGruppen(); aktuellerWert++) {
            //Überprüft, ob ein Wert gesetzt werden kann
            Feld aktuellesFeld = sudokuFeld.getFeld(zeile,spalte);
            try {
                if (!aktuellesFeld.wertInGruppen(aktuellerWert)) {
                    aktuellesFeld.setWert(aktuellerWert);
                    //Wenn ein Wert gesetzt werden konnte, wird eine spalten weiter gegangen.
                    if (loesenRekursiv(zeile, spalte + 1)) {
                        return true;
                    }

                    aktuellesFeld.setWert(0);
                }
            } catch (Exception ignore) {
            }
        }
        return false;
    }

}
