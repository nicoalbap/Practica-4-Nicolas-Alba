package bookstore;

import java.sql.SQLException;
import java.util.ArrayList;

// import bookstore.Conector;

public class Main {

		public static void main(String[] args) {

			System.out.println("Probando");
			
			Conector instancia = Conector.getInstancia();
			
			try {
				ArrayList<String> listaEmpleados = instancia.getNombres();
				for (String nombre:listaEmpleados) {
					System.out.println(nombre);
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			

			try {
				ArrayList<String> listaEmails = instancia.getEmail();
				for (String email:listaEmails) {
					System.out.println(email);
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				ArrayList<String> listaNroPublicaciones = instancia.getNroPublicaciones();
				for (String NroPublicaciones:listaNroPublicaciones) {
					System.out.println(NroPublicaciones);
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				ArrayList<String> listaNroVentas = instancia.getNroVentas();
				for (String NroVentas:listaNroVentas) {
					System.out.println(NroVentas);
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
