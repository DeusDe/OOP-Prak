package Sudoku.Lösungen;

import Sudoku.Sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Zufall implements LösungsStrategie{

    public void lösen(Sudoku sudoku) {
        List<int[]> zufaelligeKoordinaten = new ArrayList<>();
        for (int zeile = 0; zeile < sudoku.getSudokuFeld().getGroesseGruppen(); zeile++) {
            for (int spalte = 0; spalte < sudoku.getSudokuFeld().getGroesseGruppen(); spalte++) {
                zufaelligeKoordinaten.add(new int[]{zeile, spalte});
            }
        }
        Collections.shuffle(zufaelligeKoordinaten);

        loesenRekursiv(zufaelligeKoordinaten, sudoku);
    }

    private boolean loesenRekursiv(List<int[]> koordinaten, Sudoku sudoku) {
        sudoku.schritte++;
        if (koordinaten.size() == 0) {
            return true;
        }

        int[] aktuelleKoordinaten = koordinaten.get(0);
        koordinaten.remove(0);

        if (sudoku.getSudokuFeld().getZeilenWert(aktuelleKoordinaten[0], aktuelleKoordinaten[1]) != 0) {
            return loesenRekursiv(koordinaten,sudoku);
        }

        for (int wert = 1; wert <= sudoku.getSudokuFeld().getGroesseGruppen(); wert++) {
            try {
                if (sudoku.getSudokuFeld().setWert(aktuelleKoordinaten[0], aktuelleKoordinaten[1], wert)) {
                    //Wenn ein Wert gesetzt werden konnte, wird eine spalte weiter gegangen.
                    if (loesenRekursiv(koordinaten,sudoku)) {
                        return true;
                    }

                    sudoku.getSudokuFeld().setWert(aktuelleKoordinaten[0], aktuelleKoordinaten[1], 0);
                }
            } catch (Exception ignored) {
            }
        }

        koordinaten.add(0, aktuelleKoordinaten);
        return false;
    }
}
