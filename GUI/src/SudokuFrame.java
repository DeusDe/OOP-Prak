import Sudoku.Anzeige.ISudokuAnzeige;
import Sudoku.Sudoku;
import SudokuPanel.SudokuPanel;

import javax.swing.*;
import java.awt.*;

public class SudokuFrame extends JFrame implements ISudokuAnzeige {
    Sudoku sudoku;

    Button[][] felder = new Button[9][9];

    SudokuFrame(){
        super("Sudoku Anzeige Praktikum für OOP_2023");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        sudoku = new Sudoku();
        JPanel p1 = new SudokuPanel(sudoku);
        setVisible(false);

        add(p1, "North");

        this.setMinimumSize(new Dimension(1000,1000));
    }
    @Override
    public Sudoku getSudoku() {
        return sudoku;
    }

    @Override
    public void anzeigen() {
        setVisible(true);
    }

    public void verstecken(){
        setVisible(false);
    }

    @Override
    public void setSudoku(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    public static void main(String[] args){
        new SudokuFrame().anzeigen();
    }
}
