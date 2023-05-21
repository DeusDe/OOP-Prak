package Tests;

import Sudoku.Feld.Feld;
import Sudoku.Lader.LaderOptionen;
import Sudoku.Lader.SudokuLader;
import Sudoku.Lösungen.ProbierSudoku;
import Sudoku.Lösungen.StrategieSudoku;
import Sudoku.Lösungen.SudokuZustand;
import Sudoku.Sudoku;
import Sudoku.Sudoku.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JUnit {
    @Test
    void erstelle_leeres_sudoku(){
        Sudoku s = new StrategieSudoku();
        s.ladeFeld(LaderOptionen.Leer);
        for(int i = 0; i < 9; i++){
            for(int y = 0; y < 9; y++){
                assertEquals(s.getSudokuFeld().getFeld(y,i).getWert(),0);
            }
        }
    }
    @Test
    void setze_gueltige_werte(){
        int sudoku[][] = new int[][] {
                {4,8,3,9,2,1,6,5,7},
                {9,6,7,3,4,5,8,2,1},
                {2,5,1,8,7,6,4,9,3},
                {5,4,8,1,3,2,9,7,6},
                {7,2,9,5,6,4,1,3,8},
                {1,3,6,7,9,8,2,4,5},
                {3,7,2,6,8,9,5,1,4},
                {8,1,4,2,5,3,7,6,9},
                {6,9,5,4,1,7,3,8,2}
        };
        teste_vordefinierte_sudokus(sudoku,sudoku);
    }
    @Test
    void setze_beispiel_sudoku(){
        int sudoku[][] = new int[][] {
                {0,3,0,0,0,0,0,0,0},
                {0,0,0,1,9,5,0,0,0},
                {0,0,8,0,0,0,0,6,0},
                {8,0,0,0,6,0,0,0,0},
                {4,0,0,8,0,0,0,0,1},
                {0,0,0,0,2,0,0,0,0},
                {0,6,0,0,0,0,2,8,0},
                {0,0,0,4,1,9,0,0,5},
                {0,0,0,0,0,0,0,7,0}
        };
        teste_vordefinierte_sudokus(sudoku,sudoku);
    }

    @Test
    void setze_und_löse_beispiel_sudoku(){
        Sudoku s = new ProbierSudoku();
        int sudoku[][] = new int[][] {
                {0,3,0,0,0,0,0,0,0},
                {0,0,0,1,9,5,0,0,0},
                {0,0,8,0,0,0,0,6,0},
                {8,0,0,0,6,0,0,0,0},
                {4,0,0,8,0,0,0,0,1},
                {0,0,0,0,2,0,0,0,0},
                {0,6,0,0,0,0,2,8,0},
                {0,0,0,4,1,9,0,0,5},
                {0,0,0,0,0,0,0,7,0}
        };
        for(int i = 0; i < 9; i++){
            for(int y = 0; y < 9; y++){
                try {
                    s.getSudokuFeld().setWert(y,i,sudoku[y][i]);
                } catch (Exception ignored) {
                }
            }
        }
        s.loesen();
        assertEquals(s.getZustand(), SudokuZustand.Geloest);
    }

    @Test
    void unloesbares_sudoku(){
        Sudoku s = new ProbierSudoku();
        s.ladeFeld(LaderOptionen.Leer);
        int sudoku[][] = new int[][] {
                {1,0,0,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,0,0},
                {0,0,0,0,0,0,2,3,4},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0}
        };
        for(int i = 0; i < 9; i++){
            for(int y = 0; y < 9; y++){
                try {
                    s.getSudokuFeld().setWert(y,i,sudoku[y][i]);
                } catch (Exception ignored) {
                }
            }
        }
        s.loesen();
        assertEquals(s.getZustand(), SudokuZustand.Unloesbar);

    }

    void teste_vordefinierte_sudokus(int [][] zuSetzen, int[][] erwartetesErgebnis){
        Sudoku s = new StrategieSudoku();
        s.ladeFeld(LaderOptionen.Leer);
        for(int i = 0; i < 9; i++){
            for(int y = 0; y < 9; y++){
                try{s.getSudokuFeld().setWert(y,i,zuSetzen[y][i]);}
                catch (Exception ignored){}
                assertEquals(s.getSudokuFeld().getFeld(y,i).getWert(),erwartetesErgebnis[y][i]);
            }
        }
    }

    @Test
    void setze_belegtes_feld(){
        Sudoku s = new StrategieSudoku();
        s.ladeFeld(LaderOptionen.Leer);
        Feld zuTestendesFeld = s.getSudokuFeld().getFeld(0,0);
        try {
            zuTestendesFeld.setWert(1);
        } catch (Exception ignored) {
        }
        Exception e = assertThrows(Exception.class,() -> zuTestendesFeld.setWert(2));
        assertTrue(e.getMessage().contains("FeldBelegtException"));
    }

    @Test
    void setze_vorhandenen_wert_quadrant(){
        Sudoku s = new StrategieSudoku();
        s.ladeFeld(LaderOptionen.Leer);
        try {
            s.getSudokuFeld().getFeld(0,0).setWert(1);
        } catch (Exception ignored) {
        }
        Exception e = assertThrows(Exception.class,() -> s.getSudokuFeld().getFeld(1,1).setWert(1));
        assertTrue(e.getMessage().contains("WertInQuadrantVorhandenException"));
    }
    @Test
    void setze_vorhandenen_wert_Zeile(){
        Sudoku s = new StrategieSudoku();
        s.ladeFeld(LaderOptionen.Leer);
        try {
            s.getSudokuFeld().getFeld(0,0).setWert(1);
        } catch (Exception ignored) {
        }
        Exception e = assertThrows(Exception.class,() -> s.getSudokuFeld().getFeld(0,5).setWert(1));
        assertTrue(e.getMessage().contains("WertInZeileVorhandenException"));
    }
    @Test
    void setze_vorhandenen_wert_spalte(){
        Sudoku s = new StrategieSudoku();
        s.ladeFeld(LaderOptionen.Leer);
        try {
            s.getSudokuFeld().getFeld(0,0).setWert(1);
        } catch (Exception ignored) {
        }
        Exception e = assertThrows(Exception.class,() -> s.getSudokuFeld().getFeld(5,0).setWert(1));
        assertTrue(e.getMessage().contains("WertInSpalteVorhandenException"));
    }

    @Test
    void setze_wert_außerhalb_des_wertebereichs(){
        Sudoku s = new StrategieSudoku();
        s.ladeFeld(LaderOptionen.Leer);
        Exception e = assertThrows(Exception.class,() -> s.getSudokuFeld().getFeld(0,0).setWert(10));
        assertTrue(e.getMessage().contains("WertebereichUngueltigException"));
    }

    @Test
    void setze_wert_außerhalb_des_feldes(){
        Sudoku s = new StrategieSudoku();
        s.ladeFeld(LaderOptionen.Leer);
        Exception e = assertThrows(Exception.class,() -> s.getSudokuFeld().setWert(10,10,1));
        System.out.println(e.getMessage());
        assertTrue(e.getMessage().contains("UngueltigeKoordinatenException"));
    }

}