package Sudoku;


import Sudoku.Feld.Feldgruppe;
import Sudoku.Feld.SudokuFeld;
import Sudoku.Lader.*;
import Sudoku.Lösungen.SudokuZustand;

public abstract class Sudoku {
    protected SudokuFeld sudokuFeld;
    protected SudokuZustand zustand;
    protected long schritte;

    public Sudoku() {
        sudokuFeld = new LeererLader().getSudokuFeld();
        zustand = SudokuZustand.Leer;
    }



    public void ladeFeld(LaderOptionen option){
        switch (option) {
            case Leer -> sudokuFeld = new LeererLader().getSudokuFeld();
            case Beispiel -> sudokuFeld = new BeispielLader().getSudokuFeld();
            case Terminal -> sudokuFeld = new TerminalLader().getSudokuFeld();
            case Zufall -> sudokuFeld = new ZufallLader(5).getSudokuFeld();
        }

        schritte = 0;
        if(option == LaderOptionen.Leer) zustand = SudokuZustand.Leer;
        else zustand = SudokuZustand.Geladen;
    }

    /**
     * Gibt das Sudoku Feld in der Konsole aus
     */

    public abstract void loesen();

    public void ueberpruefLoesung(){
        if (zustand == SudokuZustand.Unloesbar)return;
        for(Feldgruppe aktuellerQuadrant : sudokuFeld.getQuadranten()){
            if(aktuellerQuadrant.istVorhanden(0)){
                zustand = SudokuZustand.Unloesbar;
                return;
            }
        }
        zustand = SudokuZustand.Geloest;
    }

    public boolean loesungAbgeschlossen(){
        return zustand == SudokuZustand.Unloesbar || zustand == SudokuZustand.Geloest;
    }

    public SudokuFeld getSudokuFeld(){
        return sudokuFeld;
    }

    public long getSchritte() {
        return schritte;
    }

    public SudokuZustand getZustand(){
        return this.zustand;
    }

    public int getGroesseGruppen(){
        return sudokuFeld.getGroesseGruppen();
    }

    public void setZustand(SudokuZustand zustand) {
        this.zustand = zustand;
    }
}