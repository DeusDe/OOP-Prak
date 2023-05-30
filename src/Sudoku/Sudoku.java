package Sudoku;


import Sudoku.Feld.Feld;
import Sudoku.Feld.Feldgruppe;
import Sudoku.Feld.SudokuFeld;
import Sudoku.IO.Ausgabe.Ausgabe;
import Sudoku.IO.Ausgabe.AusgabeXml;
import Sudoku.IO.Lader.*;
import Sudoku.IO.ioWerte;
import Sudoku.IO.Lader.*;
import Sudoku.Lösungen.Observer.ZustandErzähler;
import Sudoku.Lösungen.Observer.ZustandObserver;
import Sudoku.Lösungen.Probier;
import Sudoku.Lösungen.Strategie;
import Sudoku.Lösungen.SudokuZustand;
import Sudoku.Lösungen.Zufall;

import java.util.ArrayList;
import java.util.List;

public class Sudoku implements ZustandErzähler {
    private List<ZustandObserver> zustandObserverListe;

    protected SudokuFeld sudokuFeld;
    protected SudokuZustand zustand;
    public long schritte;
    private ioWerte werte = new ioWerte();

    public Sudoku() {
        this.zustandObserverListe = new ArrayList<>();
        sudokuFeld = new LeererLader(werte).getSudokuFeld();
        setZustand(SudokuZustand.Leer);
    }

    public Sudoku(LaderOptionen lader){
        super();
        this.ladeFeld(lader);
    }


    public void ladeFeld(LaderOptionen option){
        switch (option) {
            case Leer -> sudokuFeld = new LeererLader(werte).getSudokuFeld();
            case Beispiel -> sudokuFeld = new BeispielLader(werte).getSudokuFeld();
            case Terminal -> sudokuFeld = new TerminalLader(werte).getSudokuFeld();
            case XML_Lader -> sudokuFeld = new XMLLader(werte).getSudokuFeld();
            case Zufall -> sudokuFeld = new ZufallLader(werte).getSudokuFeld();
        }

        schritte = 0;
        if(option == LaderOptionen.Leer)setZustand(SudokuZustand.Leer);
        else setZustand(SudokuZustand.Geladen);
    }

    /**
     * Gibt das Sudoku Feld in der Konsole aus
     */

    public void loesen(String Methode){
        switch (Methode.toLowerCase()){
            case "strategie" -> new Strategie().lösen(this);
            case "zufall" -> new Zufall().lösen(this);
            default -> new Probier().lösen(this);
        }

    }

    public void ueberpruefLoesung(){
        if (zustand == SudokuZustand.Unloesbar)return;
        for(Feldgruppe aktuellerQuadrant : sudokuFeld.getQuadranten()){
            if(aktuellerQuadrant.istVorhanden(0)){
                setZustand(SudokuZustand.Unloesbar);
                return;
            }
        }
        setZustand(SudokuZustand.Geloest);
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
        notifyObservers();
    }

    public void setWert(int zeile,int spalte, int wert) throws Exception{
        this.getSudokuFeld().setWert(zeile,spalte,wert);
    }

    public Feld getFeld(int zeile, int spalte){
        return this.getSudokuFeld().getFeld(zeile,spalte);
    }

    @Override
    public void register(ZustandObserver obj) {
        if(obj == null)return;
        if(!zustandObserverListe.contains(obj)){
            zustandObserverListe.add(obj);
        }
    }

    @Override
    public void unregister(ZustandObserver obj) {
        zustandObserverListe.remove(obj);
    }

    @Override
    public void notifyObservers() {
        for (ZustandObserver obj : zustandObserverListe) {
            obj.update(this.zustand);
        }
    }

    public void setLaderPath(String path){
        this.werte.setLoad_path(path);
    }

    public String getLaderPath(){
        return this.werte.getLoad_path();
    }

    public void setSavePath(String path){
        this.werte.setSave_path(path);
    }

    public String getSavePath(String path){
        return this.werte.getSave_path();
    }


    public void speichern() {
        new AusgabeXml(getSudokuFeld(),werte);
    }
}