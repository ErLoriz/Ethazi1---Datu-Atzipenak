package eredua;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

import kontroladorea.*;

public class Kontsultak {

	public static ArrayList <Enplegatua> EnplegatuakIkusi(){
		ArrayList <Enplegatua> Enplegatuak = new ArrayList<Enplegatua>();
		int idEnplegatua=0;
		String izena=null;
		double soldata;
		String altaData=null;
		String altaOrdua=null;
		int Departamentua_idDepartamentua=0;
		int Ardura_idArdura=0;
		String zuzendari=null;
		boolean zuzendariBoolean=false;
		Enplegatua e = new Enplegatua();
		
		
		Connection Conexion = (Connection) Konexioa.getConnection();
		Statement s =null;

		try {
			
			s =(Statement) Conexion.createStatement();

			ResultSet rs = ((java.sql.Statement) s).executeQuery("SELECT idEnplegatua, izena, soldata, altaData, altaOrdua, zuzendari,	Departamentua_idDepartamentua, Ardura_idArdura FROM `enplegatua`");
			while (rs.next()) {
				idEnplegatua = rs.getInt("idEnplegatua");
				izena = rs.getString("izena");
				soldata = rs.getDouble("soldata");
				altaData = rs.getString("altaData");
				altaOrdua = rs.getString("altaOrdua");
				Departamentua_idDepartamentua = rs.getInt("Departamentua_idDepartamentua");
				Ardura_idArdura = rs.getInt("Ardura_idArdura");
				zuzendari = rs.getString("zuzendari");
				Enplegatua e1 = new Enplegatua (idEnplegatua, izena, soldata, altaData,	altaOrdua,	Departamentua_idDepartamentua, Ardura_idArdura, zuzendari);
				Enplegatua e2 = new Enplegatua (Enplegatuak);
				Enplegatuak.add(e1);

			}
			
		}catch(Exception y) {
			System.out.println(y.getMessage());
			
		}
		
		
		return Enplegatuak;
	}
	
	public static ArrayList <Departamentua> DepartamentuakIkusi(){
		ArrayList <Departamentua> Departamentuak = new ArrayList<Departamentua>();
		int idDepartamentua=0;
		String izena=null;
		String kokapena=null;
	
		
		Connection Conexion = (Connection) Konexioa.getConnection();
		Statement s =null;

		try {
			
			s =(Statement) Conexion.createStatement();

			ResultSet rs = ((java.sql.Statement) s).executeQuery("SELECT idDepartamentua, izena, kokapena FROM `departamentua`");
			while (rs.next()) {
				idDepartamentua = rs.getInt(1);
				izena = rs.getString(2);
				kokapena = rs.getString(3);
				Departamentua d1 = new Departamentua(idDepartamentua, izena, kokapena);
				Departamentuak.add(d1);

			}
			
		}catch(Exception y) {
			System.out.println(y.getMessage());
			
		}
		
		
		return Departamentuak;
	}
	

	
	public static ArrayList <Ardura> ArdurakIkusi(){
		ArrayList <Ardura> Ardurak = new ArrayList<Ardura>();
		int idArdura=0;
		String izenArdura=null;
	
		
		Connection Conexion = (Connection) Konexioa.getConnection();
		Statement s =null;

		try {
			
			s =(Statement) Conexion.createStatement();

			ResultSet rs = ((java.sql.Statement) s).executeQuery("SELECT idArdura, izenArdura FROM `ardura`");
			while (rs.next()) {
				idArdura = rs.getInt(1);
				izenArdura = rs.getString(2);
				Ardura a1 = new Ardura(idArdura, izenArdura);
				Ardurak.add(a1);

			}
			
		}catch(Exception y) {
			System.out.println(y.getMessage());
			
		}
		
		
		return Ardurak;
	}
	
	public static int DepartamentuIdLortu(String izena) {
		int idDepartamentua=0;
	
		
		Connection Conexion = (Connection) Konexioa.getConnection();
		Statement s =null;

		try {
			
			s =(Statement) Conexion.createStatement();

			ResultSet rs = ((java.sql.Statement) s).executeQuery("SELECT idDepartamentua FROM `departamentua` WHERE izena LIKE '"+izena+"'");
			while (rs.next()) {
				idDepartamentua = rs.getInt(1);

			}
			
		}catch(Exception y) {
			System.out.println(y.getMessage());
			
		}
		return idDepartamentua;
	}
	
	public static String DepartamentuIzenaLortu(int idDept) {
		String izena="";
	
		
		Connection Conexion = (Connection) Konexioa.getConnection();
		Statement s =null;

		try {
			
			s =(Statement) Conexion.createStatement();

			ResultSet rs = ((java.sql.Statement) s).executeQuery("SELECT izena FROM `departamentua` WHERE idDepartamentua LIKE '"+idDept+"'");
			while (rs.next()) {
				izena = rs.getString(1);

			}
			
		}catch(Exception y) {
			System.out.println(y.getMessage());
			
		}
		return izena;
	}
	
	public static int ArduraIdLortu(String izena) {
		int idArdura=0;
	
		
		Connection Conexion = (Connection) Konexioa.getConnection();
		Statement s =null;

		try {
			
			s =(Statement) Conexion.createStatement();

			ResultSet rs = ((java.sql.Statement) s).executeQuery("SELECT idArdura FROM `ardura` WHERE izenArdura LIKE '"+izena+"'");
			while (rs.next()) {
				idArdura = rs.getInt(1);

			}
			
		}catch(Exception y) {
			System.out.println(y.getMessage());
			
		}
		return idArdura;
	}
	
	public static String ArduraIzenaLortu(int idArdura) {
		String izena="";
	
		
		Connection Conexion = (Connection) Konexioa.getConnection();
		Statement s =null;

		try {
			
			s =(Statement) Conexion.createStatement();

			ResultSet rs = ((java.sql.Statement) s).executeQuery("SELECT izenArdura FROM `ardura` WHERE idArdura LIKE '"+idArdura+"'");
			while (rs.next()) {
				izena = rs.getString(1);

			}
			
		}catch(Exception y) {
			System.out.println(y.getMessage());
			
		}
		return izena;
	}
	
}
