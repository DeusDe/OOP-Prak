package Sudoku;


import Sudoku.Feld.Feldgruppe;
import Sudoku.Feld.SudokuFeld;
import Sudoku.Lader.*;
import Sudoku.Lösungen.SudokuZustand;

public abstract class Sudoku {
    protected SudokuFeld feld;
    protected SudokuZustand zustand;
    protected long schritte;

    public Sudoku() {
        feld = new LeererLader().getFeld();
        zustand = SudokuZustand.Leer;
    }



    public void ladeFeld(LaderOptionen option){
        switch (option) {
            case Leer -> feld = new LeererLader().getFeld();
            case Beispiel -> feld = new BeispielLader().getFeld();
            case Terminal -> feld = new TerminalLader().getFeld();
            case Zufall -> feld = new ZufallLader(5).getFeld();
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
        for(Feldgruppe aktuellerQuadrant : feld.getQuadranten()){
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
        return feld;
    }

    public long getSchritte() {
        return schritte;
    }

    public SudokuZustand getZustand(){
        return this.zustand;
    }

    public int getGroesseGruppen(){
        return feld.getGroesseGruppen();
    }

    public void setZustand(SudokuZustand zustand) {
        this.zustand = zustand;
    }
}