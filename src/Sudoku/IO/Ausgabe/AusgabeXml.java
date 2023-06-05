package Sudoku.IO.Ausgabe;

import Sudoku.Feld.SudokuFeld;
import Sudoku.IO.ioWerte;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class AusgabeXml extends Ausgabe{

    Document doc;

    /**
     * Konstruktor um aus dem SudokuFeld ein XML Dokument zu erstellen
     * @param sudokuFeld
     * @param werte
     */
    public AusgabeXml(SudokuFeld sudokuFeld, ioWerte werte) {
        super(sudokuFeld, werte);
        try {
            feldZuXML();
            xmlZuDatei();
        } catch (ParserConfigurationException | TransformerException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Erstellt aus dem SudokuFeld ein XML Dokument
     * @throws ParserConfigurationException
     */
    private void feldZuXML() throws ParserConfigurationException {


        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("sudoku");
        doc.appendChild(rootElement);


        for(int zeile = 0; zeile < 9; zeile++){
            Element zeileElement = doc.createElement("zeile");
            zeileElement.setAttribute("position" , String.valueOf(zeile+1));
            rootElement.appendChild(zeileElement);
            for(int spalte = 0; spalte < 9; spalte++){
                Element wertElement = doc.createElement("feld");
                wertElement.setAttribute("position" , String.valueOf(spalte+1));
                wertElement.setAttribute("wert",String.valueOf(getSudokuFeld().getFeld(zeile,spalte).getWert()));
                zeileElement.appendChild(wertElement);
            }
        }
    }

    /**
     * Erstellt den Speicherpfad, falls dieser nicht mit .xml endet
     */
    private void erstelleSpeicherPfad(){
        if(!werte.getSave_path().endsWith(".xml")){
            werte.setSave_path(werte.getSave_path() + ".xml");
        }
    }


    /**
     * Speichert das Dokument in eine Datei
     * @throws TransformerException
     * @throws FileNotFoundException
     */
    private void xmlZuDatei() throws TransformerException, FileNotFoundException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        erstelleSpeicherPfad();
        StreamResult result = new StreamResult(new FileOutputStream(werte.getSave_path()));

        transformer.transform(source, result);
    }

}
