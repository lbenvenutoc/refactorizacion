package com.lbenvenuto.factorizado;

import java.text.DateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.lbenvenuto.constante.Constante;
import com.lbenvenuto.dao.AccesoBD;
import com.lbenvenuto.util.Utilitario;

public class JobLogger {

	private static final String formatosalida = Utilitario.obtenerPropiedad("app", "app.conf.formatosalida");
	private static final String folder = Utilitario.obtenerPropiedad("app", "app.io.folder");
	private static final String archivo = Utilitario.obtenerPropiedad("app", "app.io.archivo");
	private static Logger logger;

	public JobLogger() {
		logger = Logger.getLogger("MyLog");
	}

	public static void LogMessage(String messageText, int messageType) throws Exception {
		String mensajeCompleto = null;
		messageText.trim();
		if (messageText == null || messageText.length() == 0) {
			return;
		}
		if (formatosalida.equals(null) || formatosalida.equals("")) {
			throw new Exception("Invalid configuration");
		}
		if (messageType < 0) {
			throw new Exception("Error or Warning or Message must be specified");
		}

		switch (messageType) {
		case Constante.LOG_ERROR:
			mensajeCompleto = "error " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date());
			break;
		case Constante.LOG_WARNING:
			mensajeCompleto = "warning " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date());
			break;
		default:
			mensajeCompleto = "message " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date());
			break;
		}
		mensajeCompleto += " " + messageText;

		String[] formatos = formatosalida.split("/");
		if (formatos.length > 0) {
			for (int indice = 1; indice <= formatos.length; indice++) {
				switch (indice) {
				case Constante.CONSOLA:
					ConsoleHandler ch = new ConsoleHandler();
					logger.addHandler(ch);
					logger.log(Level.INFO, mensajeCompleto);
					break;
				case Constante.ARCHIVO:
					FileHandler fh = new FileHandler(folder + "/" + archivo);
					logger.addHandler(fh);
					logger.log(Level.INFO, mensajeCompleto);
					break;
				default:
					AccesoBD acceso = new AccesoBD();
					acceso.crearConexion();
					acceso.ejecutarActualizacion(
							"insert into Log_Values('" + mensajeCompleto + "', " + String.valueOf(messageType) + ")");
					acceso.crearConexion();
					break;
				}
			}

		}

	}
}
