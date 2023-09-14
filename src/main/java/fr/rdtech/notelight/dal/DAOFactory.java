package fr.rdtech.notelight.dal;

public class DAOFactory {
	public static NoteDAO getNoteDAO() {
		return new NoteDaoJdbcImpl();
	}
	
	public static UserDAO getUserDAO() {
		return new UserDaoJdbcImpl();
	}

}
