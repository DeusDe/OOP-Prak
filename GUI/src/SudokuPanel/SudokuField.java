package SudokuPanel;

import Sudoku.Sudoku;

import javax.swing.*;
import java.awt.*;

public class SudokuField extends JPanel {

    QuadrantPanel[][] quadrantenPanels;
    SettingsPanel settings;
    Sudoku sudoku;

    public SudokuField(SettingsPanel settings, Sudoku sudoku) {
        this.sudoku = sudoku;
        this.settings = settings;
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        quadrantenPanels = new QuadrantPanel[3][3];
        for (int zeile = 0; zeile < 3; zeile++) {
            for (int spalte = 0; spalte < 3; spalte++) {
                QuadrantPanel p = new QuadrantPanel(settings, sudoku, zeile, spalte);
                gbc.gridx = spalte;
                gbc.gridy = zeile;
                quadrantenPanels[zeile][spalte] = p;
                add(p, gbc);
            }
        }
    }

    public void updateFeld(int zeile, int spalte, String text) {
        quadrantenPanels[(zeile) / 3][(spalte) / 3].updateFeld((zeile) % 3, (spalte) % 3, text);
    }

    public void updateFieldTexts() {
        for (int i = 0; i < 9; i++) {
            for (int y = 0; y < 9; y++) {
                updateFeld(i, y, "" + sudoku.getFeld(i, y).getWert());
            }
        }
    }

}
