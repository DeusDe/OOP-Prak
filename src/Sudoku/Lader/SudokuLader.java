package Sudoku.Lader;

import Sudoku.Feld.Feld;
import Sudoku.Feld.Feldgruppe;
import Sudoku.Feld.SudokuFeld;

public abstract class SudokuLader{

    private SudokuFeld feld;

    protected SudokuLader(){
        feld = new SudokuFeld(3);
    }
    protected boolean zeilenLader(int zeile,int[] werte){
        for(int spalte = 0; spalte < werte.length; spalte++){
            if(!feld.setWert(zeile,spalte,werte[spalte]))return false;
        }
        return true;
    }

    protected boolean gesammtLader(int[][] werte){
        for(int zeile = 0; zeile < werte.length; zeile++){
            if(!zeilenLader(zeile,werte[zeile]))return false;
        }
        return true;
    }

    public SudokuFeld getFeld() {
        return feld;
    }
}
