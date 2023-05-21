package Sudoku.Lader;

import Sudoku.Feld.Feld;

public class ZufallLader extends SudokuLader{
    private final int[] werte = {1,2,3,4,5,6,7,8,9};
    public ZufallLader(int zufallswerte){
        super();
        int zufallGesetzt = 0;
        while(zufallGesetzt <= zufallswerte){
            for(int zeile = 0; zeile < getSudokuFeld().getGroesseGruppen(); zeile++){
                for(int spalte = 0; spalte < getSudokuFeld().getGroesseGruppen(); spalte++){
                    if(getSudokuFeld().getZeilenWert(zeile,spalte) == 0){
                        int aktuellerWert = (int)(Math.random() * getSudokuFeld().getGroesseGruppen()+1);
                        Feld aktuellesFeld = getFeld(zeile,spalte);
                        if(aktuellesFeld.wertIstSetzbar(aktuellerWert)){
                            try{
                                aktuellesFeld.setWert(aktuellerWert);
                                zufallGesetzt++;
                                if(zufallGesetzt >= 5)return;
                            }catch (Exception ignore){

                            }
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
