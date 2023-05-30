package Sudoku.IO.Lader;

import Sudoku.Feld.Feld;
import Sudoku.IO.ioWerte;

public class ZufallLader extends SudokuLader{
    private final int[] werte = {1,2,3,4,5,6,7,8,9};
    public ZufallLader(ioWerte werte){
        super(werte);
        int zufallswerte = werte.getZufallswerte();
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
                                if(zufallGesetzt >= zufallswerte)return;
                            }catch (Exception ignore){

                            }
                        }
                    }
                }
            }
        }
    }
}
