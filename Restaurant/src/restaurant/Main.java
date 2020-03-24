package restaurant;

import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		System.out.println("Probando");
		
		Conector instancia = Conector.getInstancia();
		
		try {
			ArrayList<String> listNames = instancia.getName();
			for (String name:listNames) {
				System.out.println(name);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			ArrayList<String> listNames = instancia.getNames();
			for (String name:listNames) {
				System.out.println(name);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			ArrayList<String> listAvgPrice = instancia.getAvgUnitPrice();
			for (String price:listAvgPrice) {
				System.out.println(price);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			ArrayList<String> listNames = instancia.getNamesI();
			for (String name:listNames) {
				System.out.println(name);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			ArrayList<String> listCompanyNames = instancia.getCompanyNames();
			for (String companyname:listCompanyNames) {
				System.out.println(companyname);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
