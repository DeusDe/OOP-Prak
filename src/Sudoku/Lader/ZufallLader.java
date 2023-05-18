package Sudoku.Lader;

public class ZufallLader extends SudokuLader{
    private final int[] werte = {1,2,3,4,5,6,7,8,9};
    public ZufallLader(int zufallswerte){
        super();
        int zufallGesetzt = 0;
        while(zufallGesetzt <= zufallswerte){
            for(int zeile = 0; zeile < getFeld().getGroesseGruppen(); zeile++){
                for(int spalte = 0; spalte < getFeld().getGroesseGruppen(); spalte++){
                    if(getFeld().getZeilenWert(zeile,spalte) == 0){
                        int randomNumber = (int)(Math.random() * getFeld().getGroesseGruppen()+1);
                        if(getFeld().setWert(zeile,spalte,randomNumber)){
                            zufallGesetzt++;
                            if(zufallGesetzt >= 5)return;
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ZufallLader zfl = new ZufallLader(5);
        System.out.println();
    }
}
