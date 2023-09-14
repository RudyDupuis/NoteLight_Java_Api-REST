package fr.rdtech.notelight.bo;

public class Note {
	private int id;
	private int idUser;
	private String message;
	
	public Note(int id, int idUser, String message) {
		this.id = id;
		this.idUser = idUser;
		this.message = message;
	}

	public Note(int idUser, String message) {
		this.idUser = idUser;
		this.message = message;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
