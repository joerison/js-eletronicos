package br.com.joe.util;

public class Validacao {

	public boolean ehNumero(String valor) {
		if (valor.matches("[0-9]+.[0-9]*|[0-9]+")) {
			return true;
		} else {
			return false;
		}
	}
}
