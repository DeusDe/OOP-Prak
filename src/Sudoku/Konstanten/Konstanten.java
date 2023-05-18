package Sudoku.Konstanten;

import java.util.Arrays;

public class Konstanten {

    public static final String FEHLER_EINGABE = "Die Eingabe war fehlerhaft";
    public static final String FREIER_MODUS_TRENNZEICHEN = ":";
    public static final String[] BEFEHL_BEENDEN_ALIASE = {"x", "ex", "exit"};
    public static final String BEFEHL_BEENDEN_BESCHREIBUNG = "Beendet das Programm";
    public static final String[] BEFEHL_COMMAND_ALIASE = {"b", "befehle"};
    public static final String BEFEHL_COMMAND_BESCHREIBUNG = "Zeigt diese Liste an";
    public static final String EINGABE_COMMAND_BESCHREIBUNG = "Liest ein Sudoku ein";
    public static final String[] EINGABE_COMMAND_ALIASE = {"e","eingabe"};
    public static final String[] BEFEHL_FREIERMODUS_ALIASE = {"fm", "f", "freiermodus"};
    public static final String BEFEHL_FREIERMODUS_BESCHREIBUNG = "FreierModus";
    public static final String BEFEHLE_ÜBERSCHRIFT = "Geben sie an welchen Programmpunkt sie ausführen wollen.\n\n";
    public static final String[] BEFEHL_WERTSETZEN_ALIASE = {"\"zeile:spalte:werte\""};
    public static final String BEFEHL_WERTSETZEN_BESCHREIBUNG = "Setze ein Wert in das Feld";
    public static final String MAIN_BEFEHLE = BEFEHLE_ÜBERSCHRIFT + Arrays.toString(BEFEHL_FREIERMODUS_ALIASE) + " " + BEFEHL_FREIERMODUS_BESCHREIBUNG + "\n" + Arrays.toString(BEFEHL_BEENDEN_ALIASE) + " " + BEFEHL_BEENDEN_BESCHREIBUNG + "\n" + Arrays.toString(EINGABE_COMMAND_ALIASE) + " " + EINGABE_COMMAND_BESCHREIBUNG + "\n";
    public static final String FREIER_MODUS_BEFEHLE = BEFEHLE_ÜBERSCHRIFT + BEFEHL_WERTSETZEN_BESCHREIBUNG + "\n" + BEFEHL_COMMAND_BESCHREIBUNG + "\n" +
            BEFEHL_BEENDEN_BESCHREIBUNG + "\n";
    public static final String SODOKU_LEERES_ZEICHEN = "?";

}
