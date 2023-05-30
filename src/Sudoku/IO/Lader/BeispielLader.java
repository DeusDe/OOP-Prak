package Sudoku.IO.Lader;

import Sudoku.IO.ioWerte;

public class BeispielLader extends SudokuLader{
    public BeispielLader(ioWerte werte){
        super(werte);
        int[][] beispiel = new int[9][9];
        beispiel[0] = new int[] {0, 3, 0, 0, 0, 0, 0, 0, 0};
        beispiel[1] = new int[] {0, 0, 0, 1, 9, 5, 0, 0, 0};
        beispiel[2] = new int[] {0, 0, 8, 0, 0, 0, 0, 6, 0};
        beispiel[3] = new int[] {8, 0, 0, 0, 6, 0, 0, 0, 0};
        beispiel[4] = new int[] {4, 0, 0, 8, 0, 0, 0, 0, 1};
        beispiel[5] = new int[] {0, 0, 0, 0, 2, 0, 0, 0, 0};
        beispiel[6] = new int[] {0, 6, 0, 0, 0, 0, 2, 8, 0};
        beispiel[7] = new int[] {0, 0, 0, 4, 1, 9, 0, 0, 5};
        beispiel[8] = new int[] {0, 0, 0, 0, 0, 0, 0, 7, 0};
        gesammtLader(beispiel);
    }
}
