package it.polito.tdp.extflightdelays;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.extflightdelays.model.Model;
import it.polito.tdp.extflightdelays.model.StatoVicino;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private ComboBox<String> cmbBoxStati;

    @FXML
    private Button btnVisualizzaVelivoli;

    @FXML
    private TextField txtT;

    @FXML
    private TextField txtG;

    @FXML
    private Button btnSimula;

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	this.txtResult.clear();
    	
    	this.model.creaGrafo();
    	this.txtResult.appendText("Grafo creato!\n");
    	this.txtResult.appendText("# VERTICI: " + this.model.nVertici() + "\n");
    	this.txtResult.appendText("# ARCHI: " + this.model.nArchi() + "\n\n");

    }

    @FXML
    void doSimula(ActionEvent event) {
    	this.txtResult.clear();
    	
    	Integer T;
    	try {
    		T = Integer.parseInt(this.txtT.getText());
    	} catch(NumberFormatException e) {
    		this.txtResult.appendText("Errore! Inserire un valore numerico intero nella casella di testo 'T'!\n");
    		return;
    	}
    	
    	Integer G;
    	try {
    		G = Integer.parseInt(this.txtG.getText());
    	} catch(NumberFormatException e) {
    		this.txtResult.appendText("Errore! Inserire un valore numerico intero nella casella di testo 'G'!\n");
    		return;
    	}
    	
    	if(this.cmbBoxStati.getValue() == null) {
    		this.txtResult.appendText("Errore! Devi selezionare uno stato dall'apposito menu a tendina!\n");
    		return;
    	}
    	
    	String partenza = this.cmbBoxStati.getValue();
    	
    	Map<String, Integer> result = this.model.simula(T, G, partenza);
    	this.txtResult.appendText("Al termine della simulazione i turisti sono divisi negli stati nel seguente modo:\n");
    	for(String stato : result.keySet()) {
    		this.txtResult.appendText("Stato: " + stato + ", numero di turisti: " + result.get(stato) + "\n");
		}

    }

    @FXML
    void doVisualizzaVelivoli(ActionEvent event) {
    	this.txtResult.clear();
    	
    	if(this.cmbBoxStati.getValue() == null) {
    		this.txtResult.appendText("Errore! Devi selezionare uno stato dall'apposito menu a tendina!\n");
    		return;
    	}
    	
    	String stato = this.cmbBoxStati.getValue();
    	List<StatoVicino> vicini = this.model.visualizzaVelivoli(stato);
    	this.txtResult.appendText("Elenco degli stati collegati da arco uscente a partire dallo stato " + stato + ":\n");
    	for(StatoVicino vicino : vicini) {
    		this.txtResult.appendText(vicino + "\n");
    	}
    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert cmbBoxStati != null : "fx:id=\"cmbBoxStati\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnVisualizzaVelivoli != null : "fx:id=\"btnVisualizzaVelivoli\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert txtT != null : "fx:id=\"txtT\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert txtG != null : "fx:id=\"txtG\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'ExtFlightDelays.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		this.cmbBoxStati.getItems().addAll(this.model.getStates());
	}
}
