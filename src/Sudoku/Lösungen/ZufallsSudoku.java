package Sudoku.Lösungen;

import Sudoku.Sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ZufallsSudoku extends Sudoku {

    public void loesen() {
        List<int[]> zufaelligeKoordinaten = new ArrayList<>();
        for (int zeile = 0; zeile < feld.getGroesseGruppen(); zeile++) {
            for (int spalte = 0; spalte < feld.getGroesseGruppen(); spalte++) {
                zufaelligeKoordinaten.add(new int[]{zeile, spalte});
            }
        }
        Collections.shuffle(zufaelligeKoordinaten);

        loesenRekursiv(zufaelligeKoordinaten);
    }

    private boolean loesenRekursiv(List<int[]> koordinaten) {
        schritte++;
        if (koordinaten.size() == 0) {
            return true;
        }

        int[] aktuelleKoordinaten = koordinaten.get(0);
        koordinaten.remove(0);

        if (feld.getZeilenWert(aktuelleKoordinaten[0], aktuelleKoordinaten[1]) != 0) {
            return loesenRekursiv(koordinaten);
        }

        for (int wert = 1; wert <= feld.getGroesseGruppen(); wert++) {
            //Überprüft, ob ein wert gesetzt werden kann
            if (feld.setWert(aktuelleKoordinaten[0], aktuelleKoordinaten[1], wert)) {
                //Wenn ein Wert gesetzt werden konnte, wird eine spalte weiter gegangen.
                if (loesenRekursiv(koordinaten)) {
                    return true;
                }

                feld.setWert(aktuelleKoordinaten[0], aktuelleKoordinaten[1], 0);
            }
        }

        koordinaten.add(0, aktuelleKoordinaten);
        return false;
    }

}
