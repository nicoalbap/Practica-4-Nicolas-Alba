package cuarentena;

import java.sql.SQLException;
import java.util.ArrayList;

import cuarentena.Conector;

public class Main {

	public static void main(String[] args) {
		System.out.println("Probando");
		
		Conector instancia = Conector.getInstancia();
		
		try {
			ArrayList<String> listaPaciente = instancia.getPacientes();
			for (String nombre:listaPaciente) {
				System.out.println(nombre);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			ArrayList<String> listaDoctor = instancia.getDoctors();
			for (String nombre:listaDoctor) {
				System.out.println(nombre);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
