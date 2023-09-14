package fr.rdtech.notelight.bll;

import java.util.List;

import fr.rdtech.notelight.bo.Note;
import fr.rdtech.notelight.dal.DAOFactory;
import fr.rdtech.notelight.dal.NoteDAO;

public class NoteManager implements NoteDAO {
	private static NoteManager instance;
	
	public static NoteManager getInstance() {
		if(instance == null) {
			instance = new NoteManager();
		}
		
		return instance;
	}
	
	private NoteDAO dao = DAOFactory.getNoteDAO();

	@Override
	public List<Note> selectAll(int idUser) throws Exception {
		return dao.selectAll(idUser);
	}

	@Override
	public Note select(int idNote) throws Exception {
		return dao.select(idNote);
	}

	@Override
	public void insert(Note note) throws Exception {
		dao.insert(note);
	}

	@Override
	public void update(Note note) throws Exception {
		dao.update(note);
	}

	@Override
	public void delete(int idNote) throws Exception {
		dao.delete(idNote);
	}
	
	
}
