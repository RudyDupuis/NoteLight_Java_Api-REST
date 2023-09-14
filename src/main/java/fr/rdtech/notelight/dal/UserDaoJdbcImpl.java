package fr.rdtech.notelight.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.rdtech.notelight.bo.User;
import fr.rdtech.notelight.message.ErrorCode;
import fr.rdtech.notelight.message.MessageReader;

public class UserDaoJdbcImpl implements UserDAO {

	@Override
	public void insert(User user) throws Exception {
		String Sql = "INSERT INTO users(mail, password) VALUES (?, ?)";
		
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(Sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
			 ps.setString(1, user.getMail());
			 ps.setString(2, user.getPasseword());
			 ps.executeUpdate();
			 
			 ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
					user.setId(generatedKeys.getInt(1));
				}
		} catch (SQLException e) {
			e.printStackTrace();
			new Exception(MessageReader.getErrorMessage(ErrorCode.ERROR_INSERT));
		}
		
	}

	@Override
	public Boolean checkExistMail(String mail) throws Exception {
		String Sql = "SELECT id FROM users WHERE mail = ?";
		
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(Sql)) {
			ps.setString(1, mail);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			new Exception(MessageReader.getErrorMessage(ErrorCode.ERROR_SELECT));
		}
		
		return null;
	}

	@Override
	public User connectionUser(User user) throws Exception {
		String Sql = "SELECT * FROM users WHERE mail = ? AND password = ?";
		User userData = null;
		
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(Sql)) {
			ps.setString(1, user.getMail());
			ps.setString(2, user.getPasseword());
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				userData = new User(rs.getInt("id"), rs.getString("mail"), rs.getString("password"));
			} 
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			new Exception(MessageReader.getErrorMessage(ErrorCode.ERROR_SELECT));
		}
		
		return userData;
	}

}
