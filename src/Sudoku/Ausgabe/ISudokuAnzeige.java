package Sudoku.Ausgabe;

import Sudoku.Lösungen.Observer.ZustandObserver;
import Sudoku.Sudoku;

public interface ISudokuAnzeige{
    Sudoku getSudoku();
    void setSudoku(Sudoku sudoku);
    void anzeigen();
}

