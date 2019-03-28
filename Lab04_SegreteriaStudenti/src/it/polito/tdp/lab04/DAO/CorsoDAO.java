package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				Corso c = new Corso(codins,numeroCrediti,nome,periodoDidattico);
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				corsi.add(c);
			}
			
			conn.close();
			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {

		final String sql = "SELECT DISTINCT studente.matricola,cognome, nome,cds "
				+"FROM studente, iscrizione "+
				"WHERE studente.matricola = iscrizione.matricola "+
				"AND iscrizione.codins = ? ";

		List<Studente> studentiIscritti = new LinkedList<Studente>();
		Studente s;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
			
				int matricola2 = rs.getInt("matricola");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String cds = rs.getString("cds");

				// Crea un nuovo JAVA Bean Studente
				s = new Studente(matricola2,nome,cognome,cds);
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				studentiIscritti.add(s);
			}
			
			conn.close();
			return studentiIscritti;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
	}
	
	//data matricola trovo corsi a cui è iscritta
	public List<Corso> getCorsiMatricola(int matricola) {

		final String sql = "SELECT DISTINCT corso.* FROM iscrizione,corso WHERE iscrizione.matricola = ? AND iscrizione.codins = corso.codins";

		List<Corso> corsiMatricola = new LinkedList<Corso>();
		Corso c;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
			
				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				c = new Corso(codins,numeroCrediti,nome,periodoDidattico);
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				corsiMatricola.add(c);
			}
			
			conn.close();
			return corsiMatricola;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
		
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}
}
