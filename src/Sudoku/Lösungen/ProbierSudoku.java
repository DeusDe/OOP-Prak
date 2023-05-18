package Sudoku.Lösungen;

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
        if (feld.getZeilenWert(zeile,spalte) != 0) {
            //Falls schon ein Wert existiert, wird eine spalten weiter gegangen.
            return loesenRekursiv(zeile, spalte + 1);
        }
        for (int wert = 1; wert <= feld.getGroesseGruppen(); wert++) {
            //Überprüft, ob ein wert gesetzt werden kann
            if (feld.setWert(zeile, spalte, wert)) {
                //Wenn ein Wert gesetzt werden konnte, wird eine spalten weiter gegangen.
                if (loesenRekursiv(zeile, spalte + 1)) {
                    return true;
                }

                feld.setWert(zeile, spalte, 0);
            }
        }
        return false;
    }

}
