package restaurant;

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
		String dataBase = "restaurant";
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
	
	public ArrayList<String> getName() throws SQLException {
		ArrayList<String> listaNames = new ArrayList<String>(); 
		PreparedStatement ps = con.prepareStatement("Select name from ingredients\r\n" + 
				"inner join vendors on ingredients.vendorid = vendors.vendorid\r\n" + 
				"where vendors.companyname = 'Veggies_R_Us';");
		ResultSet rs = ps.executeQuery();
		System.out.println("\nEjercicio 1");
		while(rs.next()) {
			listaNames.add(rs.getString("name"));
		}
		rs.close();
		return listaNames;
	}
	
	public ArrayList<String> getNames() throws SQLException {
		ArrayList<String> listaNames = new ArrayList<String>(); 
		PreparedStatement ps = con.prepareStatement("Select name from ingredients \r\n" + 
				"inner join vendors on ingredients.vendorid = vendors.vendorid\r\n" + 
				"where (vendors.companyname = 'Veggies_R_Us'or vendors.companyname = 'Spring Water Supply')");
		ResultSet rs = ps.executeQuery();
		System.out.println("\nEjercicio 2");
		while(rs.next()) {
			listaNames.add(rs.getString("name"));
		}
		rs.close();
		return listaNames;
	}
	
	public ArrayList<String> getAvgUnitPrice() throws SQLException {
		ArrayList<String> listaAvgPrice = new ArrayList<String>(); 
		PreparedStatement ps = con.prepareStatement("Select avg(price) as price from items\r\n" + 
				"inner join madewith on items.itemid = madewith.itemid\r\n" + 
				"inner join ingredients on madewith.ingredientid = ingredients.ingredientid\r\n" + 
				"inner join vendors on ingredients.vendorid = vendors.vendorid\r\n" + 
				"where companyname = 'Veggies_R_Us'");
		ResultSet rs = ps.executeQuery();
		System.out.println("\nEjercicio 3");
		while(rs.next()) {
			listaAvgPrice.add(rs.getString("price"));
		}
		rs.close();
		return listaAvgPrice;
	}
	
	public ArrayList<String> getNamesI() throws SQLException {
		ArrayList<String> listaNames = new ArrayList<String>(); 
		PreparedStatement ps = con.prepareStatement("Select name from ingredients \r\n" + 
				"where ingredients.inventory < (select avg(inventory)from ingredients)");
		ResultSet rs = ps.executeQuery();
		System.out.println("\nEjercicio 4");
		while(rs.next()) {
			listaNames.add(rs.getString("name"));
		}
		rs.close();
		return listaNames;
	}
	
	public ArrayList<String> getCompanyNames() throws SQLException {
		ArrayList<String> listaCompanyNames = new ArrayList<String>(); 
		PreparedStatement ps = con.prepareStatement("Select companyname from vendors\r\n" + 
				"inner join ingredients on ingredients.vendorid = vendors.vendorid\r\n" + 
				"where referredby = (Select vendorid from vendors where companyname = 'Veggies_R_Us') \r\n" + 
				"and ingredients.foodgroup = 'milk'");
		ResultSet rs = ps.executeQuery();
		System.out.println("\nEjercicio 5");
		while(rs.next()) {
			listaCompanyNames.add(rs.getString("companyname"));
		}
		rs.close();
		return listaCompanyNames;
	}
	
}
