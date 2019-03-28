package it.polito.tdp.lab04.controller;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.*;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SegreteriaStudentiController {
	
	Model model = new Model();
	List<Corso> corsi = new LinkedList<Corso>();
	List<Studente> stemp = new LinkedList<Studente>();
	List<Corso> cMat = new LinkedList<Corso>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> comboBox;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private TextField txtMatricola;

    @FXML
    private Button btnSpunta;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnIscrivi;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button btnReset;

    @FXML
    void choose(ActionEvent event) {
    	
    	
    	
    }

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	if(model.trovaStudente(Integer.parseInt(txtMatricola.getText())) != null) {
    	cMat = model.getCorsiMatricola(Integer.parseInt(txtMatricola.getText()));
    	
    	String s = "";
		for(Corso c:cMat) {
			s += c.descrizione();
		}
		txtResult.setText(s);
    	}
    	else {
    		txtResult.setText("Inserire matricola valida");
    	}
    	
    }

    @FXML
    void doCercaIscritti(ActionEvent event){
    	Corso c = comboBox.getValue();
    	if(c==null) {
    		txtResult.setText("Nessun corso selezionato");
    	}
    	else {
    		stemp = model.getStudentiIscrittiAlCorso(c);
    		String s = "";
    		for(Studente temp:stemp) {
    			s += temp.toString();
    		}
    		txtResult.setText(s);
    	}
    }

    @FXML
    void doCercaMatricola(ActionEvent event) {
    	int matricola = Integer.parseInt(txtMatricola.getText());
    	Studente s = model.trovaStudente(matricola);
    	if(s !=null) {
    	txtNome.setText(s.getNome());
    	txtCognome.setText(s.getCognome());
    	}
    	else {
    		txtResult.setText("Matricola inesistente");
    	}
    	cMat= model.getCorsiMatricola(matricola);
    	for(Corso c:cMat) {
    		if(c.getNome().compareTo(comboBox.getValue().getNome())==0) {
    			txtResult.setText("Studente già iscritto al corso");
    			return;
    		}
    		else {
    			txtResult.setText("La matricola non è iscritta a questo corso");
    		}
    	}
    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtResult.clear();
    }

    @FXML
    void initialize() {
        assert comboBox != null : "fx:id=\"comboBox\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnSpunta != null : "fx:id=\"btnSpunta\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'SegreteriaStudenti.fxml'.";
        comboBox.getItems().setAll(model.getTuttiICorsi());
        comboBox.getItems().add(null);
        txtResult.setStyle("-fx-font-family: monospace");
    }
}
