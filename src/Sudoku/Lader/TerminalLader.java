package Sudoku.Lader;

import Sudoku.Feld.Feld;

import java.util.Scanner;

public class TerminalLader extends SudokuLader{
    public TerminalLader(){
        super();
        Scanner scanner = new Scanner(System.in);

        int fehlerWerte = 0;
        int fehlerZeilen = 0;

        for (int zeilen = 0; zeilen < getSudokuFeld().getGroesseGruppen(); zeilen++) {
            //System.out.print((zeilen+1) + ". ");
            String input = scanner.nextLine();
            String[] splittedInput = input.split(" ");
            if (splittedInput.length != getSudokuFeld().getGroesseGruppen()) {
                fehlerZeilen++;
                continue;
            }
            for (int index = 0; index < getSudokuFeld().getGroesseGruppen(); index++) {
                String aktuellesZeichen = splittedInput[index];
                if (aktuellesZeichen.length() != 1 || !Character.isDigit(aktuellesZeichen.charAt(0))) {
                    if(!aktuellesZeichen.equals("_")){
                        fehlerWerte++;
                    }

                } else {
                    Feld aktuellesFeld = getSudokuFeld().getFeld(zeilen,index);
                    int aktuellerWert = Integer.parseInt(aktuellesZeichen);

                        try {
                            aktuellesFeld.setWert(aktuellerWert);
                        } catch (Exception e) {
                            fehlerWerte++;
                        }
                }
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TerminalLader term = new TerminalLader();
        System.out.println("Test");
    }

}
