package SudokuPanel;

import Sudoku.Sudoku;

import javax.swing.*;
import java.awt.*;

public class QuadrantPanel extends JPanel {

    JButton[][] quadrantenButtons;
    Sudoku sudoku;
    int quadrantenSpalte;
    int quadrantenZeile;

    public QuadrantPanel(SettingsPanel settingsPanel, Sudoku sudoku, int quadrantenZeile, int quadrantenSpalte) {
        this.sudoku = sudoku;
        this.quadrantenZeile = quadrantenZeile;
        this.quadrantenSpalte = quadrantenSpalte;
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.ipady = 15;
        quadrantenButtons = new JButton[3][3];
        for (int zeile = 0; zeile < 3; zeile++) {
            for (int spalte = 0; spalte < 3; spalte++) {
                JButton b = new JButton(zeile + ":" + spalte);
                b.setFont(new Font("", Font.PLAIN, 45));
                int finalSpalte = (quadrantenSpalte * 3) + spalte;
                int finalZeile = (quadrantenZeile * 3) + zeile;
                int panelZeile = zeile;
                int panelSpalte = spalte;
                b.addActionListener(e -> {
                    try {
                        sudoku.setWert(finalZeile, finalSpalte, settingsPanel.getSpinnerValue());
                    } catch (Exception ex) {
                        settingsPanel.setFehlerText(ex.getClass().getSimpleName());
                    }
                    updateFeld(panelZeile, panelSpalte, sudoku.getFeld(finalZeile, finalSpalte).getWert() + "");
                });
                gbc.gridx = spalte;
                gbc.gridy = zeile;
                quadrantenButtons[zeile][spalte] = b;
                add(b, gbc);
            }
        }
    }

    public void updateFeld(int zeile, int spalte, String text) {
        quadrantenButtons[zeile][spalte].setText(text);
    }

}
