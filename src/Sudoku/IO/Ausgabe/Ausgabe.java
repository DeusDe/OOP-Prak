package Sudoku.IO.Ausgabe;

import Sudoku.Feld.SudokuFeld;
import Sudoku.IO.ioWerte;

public abstract class Ausgabe {

    protected SudokuFeld sudokuFeld;
    protected ioWerte werte;

    /**
     * Konstruktor um das sudoku auszugeben
     * @param sudokuFeld
     * @param werte
     */
    protected Ausgabe(SudokuFeld sudokuFeld, ioWerte werte){
        this.sudokuFeld = sudokuFeld;
        this.werte = werte;
    }

    /**
     * Gibt das SudokuFeld als integer Array zurück
     * @return
     */
    int[][] feldAlsIntArray(){
        int [][] feld = new int[9][9];
        for(int zeile = 0; zeile < 9; zeile++){
            for(int spalte = 0; spalte < 9; spalte++){
                feld[zeile][spalte] = sudokuFeld.getFeld(zeile,spalte).getWert();
            }
        }
        return feld;
    }


    public SudokuFeld getSudokuFeld() {
        return sudokuFeld;
    }

    public void setSudokuFeld(SudokuFeld sudokuFeld) {
        this.sudokuFeld = sudokuFeld;
    }
}
