package Sudoku.Lösungen.Observer;

import Sudoku.Lösungen.SudokuZustand;

public interface ZustandObserver{
    public void update(SudokuZustand zustand);
    public void setErzähler(ZustandErzähler er);
}
