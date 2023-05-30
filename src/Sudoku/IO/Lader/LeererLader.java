package Sudoku.IO.Lader;

import Sudoku.IO.ioWerte;

public class LeererLader extends SudokuLader{
    public LeererLader(ioWerte werte){
        super( werte);
        for(int i = 0; i < getSudokuFeld().getGroesseGruppen(); i++){
            int[] zeile = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
            zeilenLader(i,zeile);
        }
    }
}
