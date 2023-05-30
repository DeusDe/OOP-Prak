package Sudoku.IO;

public class ioWerte {
    private String save_path;
    private String load_path;

    private int zufallswerte;

    public ioWerte(){
        this.save_path = "src/Sudoku/Speicher/";
        this.load_path = "/Users/privat/Downloads/OOP-Prak/xml/nichtValide/woerterAlsWerte.xmls";
        this.zufallswerte = 20;
    }

    public int getZufallswerte() {
        return zufallswerte;
    }

    public void setZufallswerte(int zufallswerte) {
        this.zufallswerte = zufallswerte;
    }

    public String getSave_path() {
        return save_path;
    }

    public void setSave_path(String save_path) {
        this.save_path = save_path;
    }

    public String getLoad_path() {
        return load_path;
    }

    public void setLoad_path(String load_path) {
        this.load_path = load_path;
    }

}
