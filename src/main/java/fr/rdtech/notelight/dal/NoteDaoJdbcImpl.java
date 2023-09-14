package fr.rdtech.notelight.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.rdtech.notelight.bo.Note;
import fr.rdtech.notelight.message.ErrorCode;
import fr.rdtech.notelight.message.MessageReader;

public class NoteDaoJdbcImpl implements NoteDAO {

	@Override
	public List<Note> selectAll(int idUser) throws SQLException {
		String Sql = "SELECT * FROM notes WHERE idUser = ?";
		List<Note> notes = new ArrayList<>();
		
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(Sql)) {
			ps.setInt(1, idUser);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Note note = new Note(rs.getInt("id"), rs.getInt("idUser"), rs.getString("message"));
				notes.add(note);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			new Exception(MessageReader.getErrorMessage(ErrorCode.ERROR_SELECT));
		}
		
		return notes;
	}

	@Override
	public Note select(int idNote) throws SQLException {
		String Sql = "SELECT * FROM notes WHERE id = ?";
		Note note = null;
		
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(Sql)) {
			ps.setInt(1, idNote);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				note = new Note(rs.getInt("id"), rs.getInt("idUser"), rs.getString("message"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			new Exception(MessageReader.getErrorMessage(ErrorCode.ERROR_SELECT));
		}
		
		return note;
	}

	@Override
	public void insert(Note note) throws SQLException {
		String Sql = "INSERT INTO notes(idUser, message) VALUES (?, ?)";
		
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(Sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
			 ps.setInt(1, note.getIdUser());
			 ps.setString(2, note.getMessage());
			 ps.executeUpdate();
			 
			 ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
					note.setId(generatedKeys.getInt(1));
				}
		} catch (SQLException e) {
			e.printStackTrace();
			new Exception(MessageReader.getErrorMessage(ErrorCode.ERROR_INSERT));
		}
		
	}

	@Override
	public void update(Note note) throws SQLException {
		String Sql = "UPDATE notes SET message = ? WHERE id = ?";
		
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(Sql)) {
			 ps.setString(1, note.getMessage());
			 ps.setInt(2, note.getId());
			 ps.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
			new Exception(MessageReader.getErrorMessage(ErrorCode.ERROR_UPDATE));
		}
	}

	@Override
	public void delete(int idNote) throws SQLException {
		String Sql = "DELETE FROM notes WHERE id = ?";
		
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement ps = cnx.prepareStatement(Sql)) {
			 ps.setInt(1, idNote);
			 ps.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
			new Exception(MessageReader.getErrorMessage(ErrorCode.ERROR_DELETE));
		}
		
	}
	

}
