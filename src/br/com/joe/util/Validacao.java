package br.com.joe.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class Validacao {
	private static Logger log = Logger.getLogger(Validacao.class);

	public java.sql.Date converterData(String data) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date date = formatter.parse(data);
			return new java.sql.Date(date.getTime());
		} catch (ParseException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public boolean numeroValido(String valor) {
		if (valor.matches("[0-9]+.[0-9]*|[0-9]+")) {
			return true;
		} else {
			log.error("numero invalido");
			return false;
		}
	}
}
