package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	/*
	 * Ottengo lo studente richiesto
	 */
	public Studente trovaStudente(int matricola) {

		final String sql = "SELECT * FROM studente WHERE matricola = ? ";
		Studente s = null;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			//metti al primo punto interrogativo la matricola che passo
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola2 = rs.getInt("matricola");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String cds = rs.getString("cds");

				// Crea un nuovo JAVA Bean Studente
				s = new Studente(matricola2,nome,cognome,cds);
				
			}
			
			conn.close();
			return s;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
}
