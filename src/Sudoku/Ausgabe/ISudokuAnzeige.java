package Sudoku.Ausgabe;

import Sudoku.Sudoku;

public interface ISudokuAnzeige {
    Sudoku getSudoku();
    void setSudoku(Sudoku sudoku);
    void anzeigen();
}

