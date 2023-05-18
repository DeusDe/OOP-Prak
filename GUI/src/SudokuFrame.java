import Sudoku.Ausgabe.ISudokuAnzeige;
import Sudoku.Feld.Feld;
import Sudoku.Lader.LaderOptionen;
import Sudoku.Lader.SudokuLader;
import Sudoku.Lösungen.ProbierSudoku;
import Sudoku.Lösungen.StrategieSudoku;
import Sudoku.Lösungen.ZufallsSudoku;
import Sudoku.Sudoku;

import java.awt.*;

public class SudokuFrame extends java.awt.Frame implements ISudokuAnzeige {

    private final int FENSTER_HÖHE = 900;
    private final int FENSTER_BREITE = 900;
    private final int DISTANZ_SEITE = 225;
    private final int DISTANZ_TOP = 50;
    private final int DISTANZ_ELEMENTE = 5;
    private final Label STATUS_LABEL = new Label();
    private final Label FEHLER_LABEL = new Label();
    Sudoku sudoku;

    Button[][] felder = new Button[9][9];

    SudokuFrame(){
        super("Sudoku Anzeige Praktikum für OOP_2023");
        sudoku = new ProbierSudoku();
        sudoku.ladeFeld(LaderOptionen.Leer);
        setSize(FENSTER_BREITE,FENSTER_HÖHE);
        setVisible(true);
        setResizable(false);

        TextField tf = new TextField();
        tf.setBounds(112,100+(9*75) + 10,75,30);
        tf.setText("Werte");
        add(tf);

        erstelleButtons(tf);
        setzeButtonText();

        Button b = new Button();
        b.setLabel("Lösen");
        b.setBounds(192,100+(9*75) + 10,75,30);
        b.addActionListener(actionEvent ->{
            sudoku.loesen();
            setzeButtonText();
            STATUS_LABEL.setText(sudoku.getZustand().toString());
        });
        add(b);

        erstelleLaderChoice();
        erstelleLösungsChoice();
        erstelleStatusLabel();
        erstelleFehlerLabel();

        /**
         * TODO
         * Element wird nur hinzugefügt wegen einem Bug, welcher das letzte element vergrößert darstellt wenn das Fenster bewegt wird
         */
        add(new Label());
    }

    private void erstelleStatusLabel(){
        STATUS_LABEL.setText(sudoku.getZustand().toString());
        STATUS_LABEL.setBounds(467,100+(9*75) + 10,100,30);
        add(STATUS_LABEL);
    }

    private void erstelleFehlerLabel(){
        FEHLER_LABEL.setText("Fehler und so");
        FEHLER_LABEL.setBounds(567,100+(9*75) + 10,200,30);
        add(FEHLER_LABEL);
    }

    private void erstelleLösungsChoice() {
        Choice ch = new Choice();
        ch.setBounds(367,100+(9*75) + 10,100,30);
        ch.add("Probier");
        ch.add("Strategie");
        ch.add("Zufall");

        ch.addItemListener(itemEvent -> {
            switch (itemEvent.getItem().toString()){
                case "Probier" -> setSudoku(new ProbierSudoku());
                case "Strategie" -> setSudoku(new StrategieSudoku());
                case "Zufall" -> setSudoku(new ZufallsSudoku());
            }

            sudoku.ladeFeld(LaderOptionen.Leer);
            setzeButtonText();
        });

        add(ch);
    }

    private void erstelleLaderChoice(){
        Choice ch = new Choice();
        ch.setBounds(267,100+(9*75) + 10,100,30);
        ch.add("Leer");
        ch.add("Beispiel");
        ch.add("Zufall");

        ch.addItemListener(itemEvent -> {
            sudoku.ladeFeld(LaderOptionen.Leer);
            LaderOptionen option = LaderOptionen.Leer;
            switch (itemEvent.getItem().toString()){
                case "Beispiel" -> option = LaderOptionen.Beispiel;
                case "Zufall" -> option = LaderOptionen.Zufall;
            }

            sudoku.ladeFeld(option);
            setzeButtonText();
        });

        add(ch);
    }

    private void erstelleButtons(TextField tf){
        int size = (getHeight()-DISTANZ_SEITE)/9;
        for(int i = 0; i < 9; i++){
            for(int y = 0; y < 9; y++){
                Button b = new Button();
                int quadrantenDistanzTop = ((y)/3)*25;
                int quadrantenDistanzSeite = ((i)/3)*25;
                b.setBounds((DISTANZ_SEITE-50)/2 + (size * i) + quadrantenDistanzSeite,DISTANZ_TOP + (size * y) + quadrantenDistanzTop,size-DISTANZ_ELEMENTE,size-DISTANZ_ELEMENTE);

                int finalI = i;
                int finalY = y;

                b.addActionListener(actionEvent -> {
                    String eingabeText = tf.getText();
                    try{
                        int eingabeAlsInt = Integer.parseInt(eingabeText);
                        if(sudoku.getSudokuFeld().setWert(finalI,finalY,eingabeAlsInt)) b.setLabel(""+eingabeAlsInt);
                    }catch (Exception e){
                        return;
                    }});
                b.setFont(new Font("",Font.PLAIN,45));

                felder[i][y] = b;
                add(b);
            }
        }
    }

    private void setzeButtonText(){
        for (int zeile = 0; zeile < 9; zeile++){
            for (int spalte = 0; spalte < 9; spalte++){
                felder[spalte][zeile].setLabel(sudoku.getSudokuFeld().getFeld(zeile,spalte).getWert() + "");

            }
        }
    }

    @Override
    public Sudoku getSudoku() {
        return null;
    }

    @Override
    public void anzeigen() {

    }

    @Override
    public void setSudoku(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    public static void main(String[] args){
        new SudokuFrame();
    }

}
