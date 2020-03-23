package bookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conector {
	
	//Atribustos de clase
	
		private static Connection con;
		private static Conector INSTANCE = null;
		
		//Constructor
		
		private Conector(){
			
		}
		
		//Crear instancia
		
		private synchronized static void crearInstancia() {
			if (INSTANCE == null)	{
				INSTANCE = new Conector();
				crearConexion();
			}
			
		}
		
		// Obtener instancia
		
		public static Conector getInstancia(){
			if (INSTANCE == null)	{
				crearInstancia();
			}
			return INSTANCE;
		}
		
		//Crear conexion
		
		private static void crearConexion() {
			String host = "127.0.0.1";
			String user = "root";
			String password = "familiadenico";
			String dataBase = "bookstore";
			try {
				//Importando la libreria de conexion
				Class.forName("com.mysql.jdbc.Driver");
				String urlConexion = "jdbc:mysql://"+host+"/"+dataBase+"?user="+user+"&password="+password;
				con = DriverManager.getConnection(urlConexion);
				System.out.println("Lo lograste :)");
				
			}catch(Exception e) {
				System.out.println("Error al conectar la base de datos :(");
			}
			
		}
		
		public ArrayList<String> getNombres() throws SQLException {
			ArrayList<String> listaEmpleados = new ArrayList<String>(); 
			PreparedStatement ps = con.prepareStatement("Select nombre, apellido, usuario from empleado\r\n" + 
					"left join usuario on empleado.idempleado = usuario.idempleado");
			ResultSet rs = ps.executeQuery();
			System.out.println("");
			System.out.println("Ejercicio 1");
			System.out.println("Nombre   Apellidos      Usuario");
			while(rs.next()) {
				
				listaEmpleados.add(rs.getString("nombre")+ " " + rs.getString("apellido")+ " " + rs.getString("usuario")); 
			}
			rs.close();
			return listaEmpleados;

		}
		
		
		public ArrayList<String> getEmail() throws SQLException {
			ArrayList<String> listaEmails = new ArrayList<String>(); 
			PreparedStatement ps = con.prepareStatement("Select email from empleado\r\n" + 
					"inner join usuario on empleado.idempleado = usuario.idempleado\r\n" + 
					"where usuario.activo = 1;");
			ResultSet rs = ps.executeQuery();
			System.out.println("");
			System.out.println("Ejercicio 2");
			System.out.println("Emails:");
			while(rs.next()) {
				
				listaEmails.add(rs.getString("email"));
			}
			rs.close();
			return listaEmails;
		}

		
		public ArrayList<String> getNroPublicaciones() throws SQLException {
			ArrayList<String> listaNroPublicaciones = new ArrayList<String>(); 
			PreparedStatement ps = con.prepareStatement("Select Count(autor) as autor from publicacion\r\n" + 
					"where autor = 'Eric G. Coronel Castillo'");
			ResultSet rs = ps.executeQuery();
			System.out.println("");
			System.out.println("Ejercicio 3");
			System.out.println("Numero de publicaciones realizadas por Eric C.:");
			while(rs.next()) {
				
				listaNroPublicaciones.add(rs.getString("autor"));
			}
			rs.close();
			return listaNroPublicaciones;
		}
		
		public ArrayList<String> getNroVentas() throws SQLException {
			ArrayList<String> listaNroVentas = new ArrayList<String>(); 
			PreparedStatement ps = con.prepareStatement("Select Sum(cantidad) as cantidad from venta \r\n" + 
					"inner join empleado on venta.idempleado = empleado.idempleado\r\n" + 
					"where empleado.nombre = 'Emilio';");
			ResultSet rs = ps.executeQuery();
			System.out.println("");
			System.out.println("Ejercicio 4");
			System.out.println("La sumatoria de las ventas relaizadas por Emilio es:");
			while(rs.next()) {
				
				listaNroVentas.add(rs.getString("cantidad"));
			}
			rs.close();
			return listaNroVentas;
		}
		
		
		
}
