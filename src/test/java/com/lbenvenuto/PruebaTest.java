package com.lbenvenuto;

import org.junit.Test;

import com.lbenvenuto.constante.Constante;
import com.lbenvenuto.factorizado.JobLogger;

public class PruebaTest {

	private static JobLogger logger = new JobLogger();

	
	@Test
	public void pruebaMensaje() {
		try {
			logger.LogMessage("Prueba", Constante.LOG_MESSAGE);
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	}

}
