import Sudoku.Anzeige.ISudokuAnzeige;
import Sudoku.Konstanten.Konstanten;
import Sudoku.IO.Lader.LaderOptionen;
import Sudoku.Sudoku;

import java.util.Map;
import java.util.Scanner;

public class SudokuTerminalAnzeige implements ISudokuAnzeige {


    Sudoku sudoku = new Sudoku();

    /**
     * Fügt einen Command mit Kürzeln zur Map hinzu
     * @param commandMap
     * @param commandStrings
     * @param method
     */
    public static void command_adder(Map<String, Runnable> commandMap, String[] commandStrings, Runnable method) {
        for (String command : commandStrings) {
            commandMap.put(command, method);
        }
    }


    /**
     * Lässt den User auswählen welcher Programmpunkt gestartet werden soll.
     * @param args
     */
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        SudokuTerminalAnzeige app = new SudokuTerminalAnzeige();
        while(true){
            System.out.println("Wählen sie eine Lösungsstrategie\n" +
                    "1: Zufall\n" +
                    "2: Probier\n" +
                    "3: Strategie\n");
            int option = scanner.nextInt();

            switch (option){
                case 1 -> app.setSudoku(new Sudoku());
                case 3 -> app.setSudoku(new Sudoku());
                default -> app.setSudoku(new Sudoku());
            }



            System.out.println("Geben sie an welchen Lader sie benutzen wollen:\n" +
                    "1: Beispiel \n" +
                    "2: Terminal\n" +
                    "3: Zufall\n");

            option = scanner.nextInt();

            switch (option){
                case 1 -> app.getSudoku().ladeFeld(LaderOptionen.Beispiel);
                case 2 -> app.getSudoku().ladeFeld(LaderOptionen.Terminal);
                case 3 -> app.getSudoku().ladeFeld(LaderOptionen.Zufall);
                default -> app.getSudoku().ladeFeld(LaderOptionen.Leer);
            }

            app.anzeigen();
            //app.getSudoku().loesen();
            app.anzeigen();
        }
    }

    @Override
    public void anzeigen(){
        System.out.println("┌───────┬───────┬───────┐");
        for(int zeilenIndex = 0; zeilenIndex < 9; zeilenIndex++){
            System.out.print("│ ");
            for(int spaltenIndex = 0; spaltenIndex < 9; spaltenIndex++) {
                int aktuellerWert = sudoku.getSudokuFeld().getZeilenWert(zeilenIndex,spaltenIndex);
                System.out.print( (aktuellerWert == 0 ? Konstanten.SODOKU_LEERES_ZEICHEN : aktuellerWert) + ((spaltenIndex+1)%3==0?" │ ":" ") );
            }
            if((zeilenIndex+1)%3==0){
                if(((zeilenIndex+1)/3)!=3)System.out.println("\n├───────┼───────┼───────┤");
            }else{
                System.out.println();
            }

        }
        System.out.println("\n└───────┴───────┴───────┘");

        if(sudoku.loesungAbgeschlossen()){
            //TODO erfolgreich/unerfolgreich platzhalter ersetzen
            System.out.println("Der Lösungsversuch war {erfolgreich/unerfolgreich} und hat " + sudoku.getSchritte() + " Schritte benötigt");
        }
    }

    @Override
    public Sudoku getSudoku() {
        return  sudoku;
    }

    @Override
    public void setSudoku(Sudoku sudoku) {
        this.sudoku = sudoku;
    }
}