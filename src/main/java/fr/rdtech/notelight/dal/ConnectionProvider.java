package fr.rdtech.notelight.dal;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import fr.rdtech.notelight.message.ErrorCode;
import fr.rdtech.notelight.message.MessageReader;

public abstract class ConnectionProvider {
	private static DataSource dataSource;

	static {
		Context context;
		try {
			context = new InitialContext();
			ConnectionProvider.dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
		} catch (NamingException e) {
			e.printStackTrace();
			new Exception(MessageReader.getErrorMessage(ErrorCode.ERROR_CONNECTION));
		}
	}

	public static Connection getConnection() throws SQLException {
		return ConnectionProvider.dataSource.getConnection();
	}
}
