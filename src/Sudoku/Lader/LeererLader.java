package Sudoku.Lader;

public class LeererLader extends SudokuLader{
    public LeererLader(){
        super();
        for(int i = 0; i < getFeld().getGroesseGruppen(); i++){
            int[] zeile = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
            zeilenLader(i,zeile);
        }
    }
}
