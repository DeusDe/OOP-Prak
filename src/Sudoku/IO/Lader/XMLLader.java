package Sudoku.IO.Lader;

import Sudoku.IO.ioWerte;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXParseException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class XMLLader extends SudokuLader{
    private Document doc;
    private final String VALIDATION_FILE = "/Users/privat/Downloads/OOP-Prak/xml/sudokuSchema.xsd";
    public void loadFile(String path){

        try{
            //Validiere das Dokument
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            Schema schema = factory.newSchema(new File(VALIDATION_FILE));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(path)));

            //Lade das Dokument

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(new File(path));

            doc.getDocumentElement().normalize();

        }catch (SAXParseException e){
            System.out.println("Das Dokument ist nicht valide");
        }catch (FileNotFoundException e){
            System.out.println("Das Dokument wurde nicht gefunden");
        } catch (Exception e){
            System.out.println(e.getClass());
        }
    }

    public int[][] erstelleFeldVonDokument(){
        int [][] feld =  new int[9][9];
        //Falls das Document nicht erstellt wurde wird ein Leeres Feld zurückgegeben
        if(this.doc == null){
            return feld;
        }
        NodeList Zeilen = doc.getElementsByTagName("zeile");
        for(int aktuelleZeile = 0; aktuelleZeile < Zeilen.getLength(); aktuelleZeile++){
            Node node = Zeilen.item(aktuelleZeile);
            for(int aktuellesFeld = 0; aktuellesFeld < Zeilen.getLength(); aktuellesFeld++){
                feld[aktuelleZeile][aktuellesFeld] = Integer.parseInt(((Element) node).getElementsByTagName("feld").item(aktuellesFeld).getAttributes().getNamedItem("wert").getNodeValue());
            }
        }
        return feld;

    }

    public XMLLader(ioWerte werte){
        super(werte);
        loadFile(werte.getLoad_path());
        this.gesammtLader(erstelleFeldVonDokument());
    }


}
