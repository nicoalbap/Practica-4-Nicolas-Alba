package cuarentena;

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
		String dataBase = "cuarentena";
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
	
	public ArrayList<String> getPacientes() throws SQLException {
		ArrayList<String> listaPaciente = new ArrayList<String>(); 
		PreparedStatement ps = con.prepareStatement("Select nombres, apellidos from persona\r\n" + 
				"inner join paciente on persona.CI = paciente.personaID;") ;
		ResultSet rs = ps.executeQuery();
		System.out.println("\nEjercicio 1:");
		System.out.println("Nombres   Apellidos");
		while(rs.next()) {
		listaPaciente.add(rs.getString("nombres") +" "+ rs.getString("apellidos"));
		}
		rs.close();
		return listaPaciente;
	}
	
	public ArrayList<String> getDoctors() throws SQLException {
		ArrayList<String> listaDoctor = new ArrayList<String>(); 
		PreparedStatement ps = con.prepareStatement("Select nombres, apellidos, nro from persona\r\n" + 
				"inner join doctor on persona.CI = doctor.ID\r\n" + 
				"inner join consultorio on consultorio.ID = doctor.consultorioID") ;
		ResultSet rs = ps.executeQuery();
		System.out.println("\nEjercicio 2:");
		System.out.println("Nombres  Apellidos  Nro Consultorio ");
		while(rs.next()) {
		listaDoctor.add(rs.getString("nombres") +"   "+ rs.getString("apellidos")+"     "+ rs.getString("nro"));
		}
		rs.close();
		return listaDoctor;
	}
	
	}

