package Sudoku.Lösungen.Observer;

import Sudoku.Lösungen.SudokuZustand;

public interface ZustandErzähler {
    public void register(ZustandObserver obj);
    public void unregister(ZustandObserver obj);

    //method to notify observers of change
    public void notifyObservers();
}
