package fr.rdtech.notelight.dal;

import java.util.List;

import fr.rdtech.notelight.bo.Note;


public interface NoteDAO {
	List<Note> selectAll(int idUser) throws Exception;
	Note select(int idNote) throws Exception;
	void insert(Note note) throws Exception;
	void update(Note note) throws Exception;
	void delete(int idNote) throws Exception;	
}
