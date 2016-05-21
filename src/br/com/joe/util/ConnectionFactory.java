package br.com.joe.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionFactory {

	private static Logger log = Logger.getLogger(ConnectionFactory.class);

	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost/js-eletronicos?user=root&password=root";
			conn = DriverManager.getConnection(url);
			log.debug("Conexao aberta");
		} catch (SQLException e) {
			log.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage());
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return conn;
	}
}
