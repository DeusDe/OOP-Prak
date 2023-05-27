package SudokuPanel;

import Sudoku.Lader.LaderOptionen;
import Sudoku.Sudoku;

import javax.swing.*;
import java.awt.*;


public class SettingsPanel extends JPanel {

    private final JSpinner spinner;
    private Label fehler;
    private SudokuField field;

    public SettingsPanel(Sudoku sudoku, SudokuField field) {
        this.setLayout(new GridBagLayout());
        this.field = field;

        // Buttons
        Button laderSetzen = new Button("Laden");
        Button sudokuLösen = new Button("Lösen");

        // Choices
        Choice lösungen = new Choice();
        lösungen.add("Probier");
        lösungen.add("Strategie");
        lösungen.add("Zufall");

        Choice lader = new Choice();
        lader.add("Leer");
        lader.add("Beispiel");
        lader.add("Zufall");

        // Action Listeners
        sudokuLösen.addActionListener(e -> {
            System.out.println(lösungen.getSelectedItem());
            sudoku.loesen(lösungen.getSelectedItem());
            this.field.updateFieldTexts();
        });

        laderSetzen.addActionListener(e -> {
            LaderOptionen option = switch (lader.getSelectedItem()) {
                case "Leer" -> LaderOptionen.Leer;
                case "Zufall" -> LaderOptionen.Zufall;
                default -> LaderOptionen.Beispiel;
            };

            sudoku.ladeFeld(option);
            this.field.updateFieldTexts();
        });

        // Labels
        Label status = new Label();
        Label fehler = new Label();

        // Spinner
        spinner = new JSpinner();
        spinner.setModel(new SpinnerNumberModel());

        // GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(5, 5, 0, 5);

        // Add components to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(spinner, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(lader, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(laderSetzen, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        add(lösungen, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        add(sudokuLösen, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        add(status, gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        add(fehler, gbc);
    }

    public void setField(SudokuField field) {
        this.field = field;
    }

    public JSpinner getSpinner() {
        return spinner;
    }

    public int getSpinnerValue() {
        return (Integer) getSpinner().getValue();
    }

    public void setFehlerText(String text) {
        fehler.setText(text);
    }

}
