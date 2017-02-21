package com.lbenvenuto.util;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class Utilitario {

	private static Logger depurador = Logger.getLogger(Utilitario.class.getName());

	private static final String folder = Utilitario.obtenerPropiedad("app", "app.io.folder");
	private static final String archivo = Utilitario.obtenerPropiedad("app", "app.io.archivo");

	public Utilitario() {

	}

	public static String obtenerPropiedad(String archivo, String dato) {
		String variable = "";
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(archivo);
			variable = bundle.getString(dato);
		} catch (Exception e) {
			depurador.error("ERROR : Utilitario : obtenerPropiedad => : " + e);
		}
		return variable;
	}

	public static void crearArchivo() {
		File logFile = new File(folder + "/" + archivo);
		if (!logFile.exists()) {
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				depurador.error("ERROR : Utilitario : IOException => : " + e);
			}
		}
	}

}
