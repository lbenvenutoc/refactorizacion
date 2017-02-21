package com.lbenvenuto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;
import com.lbenvenuto.util.Utilitario;

public class AccesoBD {

	private static Logger depurador = Logger.getLogger(AccesoBD.class.getName());

	private Connection con;

	private static final String driver = Utilitario.obtenerPropiedad("app", "app.bd.driver");
	private static final String dbms = Utilitario.obtenerPropiedad("app", "app.bd.dbms");
	private static final String servidor = Utilitario.obtenerPropiedad("app", "app.bd.servidor");
	private static final String puerto = Utilitario.obtenerPropiedad("app", "app.bd.puerto");
	private static final String nombrebase = Utilitario.obtenerPropiedad("app", "app.bd.nombrebd");
	private static final String usuario = Utilitario.obtenerPropiedad("app", "app.bd.usuario");
	private static final String clave = Utilitario.obtenerPropiedad("app", "app.bd.clave");

	public void crearConexion() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection("jdbc:" + dbms + "://" + servidor + ":" + puerto + "/" + nombrebase + "",
					usuario, clave);
		} catch (ClassNotFoundException e) {
			depurador.error("ERROR : ClassNotFoundException : crearConexion => : " + e);
		} catch (SQLException e) {
			depurador.error("ERROR : SQLException : crearConexion => : " + e);
		}
	}

	public void ejecutarActualizacion(String sql) {
		try {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			depurador.error("ERROR : SQLException : ejecutarActualizacion => : " + e);
		}
	}

	public void cerrarConexion() {
		try {
			con.close();
		} catch (SQLException e) {
			depurador.error("ERROR : SQLException : cerrarConexion => : " + e);
		}
	}

}
