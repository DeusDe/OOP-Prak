package Sudoku.Lösungen;

import Sudoku.Sudoku;

public class Probier implements LösungsStrategie{

    @Override
    public void lösen(Sudoku sudoku) {
        loesenRekursiv(0, 0, sudoku);
        sudoku.ueberpruefLoesung();
    }

    private boolean loesenRekursiv(int zeile, int spalte, Sudoku sudoku) {
        sudoku.schritte++;
        //Wenn das Sudoku in Zeile 9 ist, ist die Loesung abgeschlossen.
        if (zeile == 9) {
            return true;
        }
        if (spalte == 9) {
            //Wenn das Sudoku in Spalte 9 ist, wird eine Rekursiver aufruf in die nächste zeile in spalte 0 gestartet.
            return loesenRekursiv(zeile + 1, 0,sudoku);
        }
        if (sudoku.getSudokuFeld().getZeilenWert(zeile,spalte) != 0) {
            //Falls schon ein Wert existiert, wird eine spalten weiter gegangen.
            return loesenRekursiv(zeile, spalte + 1,sudoku);
        }
        for (int aktuellerWert = 1; aktuellerWert <= sudoku.getSudokuFeld().getGroesseGruppen(); aktuellerWert++) {
            //Überprüft, ob ein Wert gesetzt werden kann
            try {
                    sudoku.setWert(zeile,spalte,aktuellerWert);
                    //Wenn ein Wert gesetzt werden konnte, wird eine spalten weiter gegangen.
                    if (loesenRekursiv(zeile, spalte + 1,sudoku)) {
                        return true;
                    }

                    sudoku.setWert(zeile,spalte,0);
            } catch (Exception ignore) {
            }
        }
        return false;
    }

}
