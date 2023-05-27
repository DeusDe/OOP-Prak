package SudokuPanel;

import Sudoku.Lader.LaderOptionen;
import Sudoku.Sudoku;

import javax.swing.*;
import java.awt.*;

public class SudokuPanel extends JPanel {

    private final Sudoku sudoku;
    private SudokuField sudokoField;
    private final SettingsPanel settings;

    public SudokuPanel(Sudoku sudoku) {
        this.sudoku = sudoku;
        this.sudoku.ladeFeld(LaderOptionen.Beispiel);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        settings = new SettingsPanel(sudoku, sudokoField);
        sudokoField = new SudokuField(settings, sudoku);
        settings.setField(sudokoField);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(sudokoField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(settings, gbc);
        updateFieldTexts();
    }

    private void updateFieldTexts() {
        for (int i = 0; i < 9; i++) {
            for (int y = 0; y < 9; y++) {
                sudokoField.updateFeld(i, y, "" + sudoku.getFeld(i, y).getWert());
            }
        }
    }

}
