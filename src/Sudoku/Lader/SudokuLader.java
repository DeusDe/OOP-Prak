package Sudoku.Lader;

import Sudoku.Feld.Feld;
import Sudoku.Feld.SudokuFeld;

public abstract class SudokuLader{

    private SudokuFeld getSudokuFeld;

    protected SudokuLader(){
        getSudokuFeld = new SudokuFeld(3);
    }
    protected boolean zeilenLader(int zeile,int[] werte){
        for(int spalte = 0; spalte < werte.length; spalte++){
            Feld aktuellesFeld = getFeld(zeile,spalte);
            int aktuellerWert = werte[spalte];
            if(aktuellesFeld.wertIstSetzbar(aktuellerWert) || aktuellerWert == 0){
                try {
                    aktuellesFeld.setWert(aktuellerWert);
                } catch (Exception ignore) {
                    return false;
                }
            }
        }
        return true;
    }

    protected boolean gesammtLader(int[][] werte){
        for(int zeile = 0; zeile < werte.length; zeile++){
            if(!zeilenLader(zeile,werte[zeile]))return false;
        }
        return true;
    }

    public SudokuFeld getSudokuFeld() {
        return getSudokuFeld;
    }

    public Feld getFeld(int zeile, int spalte){
        return getSudokuFeld().getFeld(zeile,spalte);
    }

}
