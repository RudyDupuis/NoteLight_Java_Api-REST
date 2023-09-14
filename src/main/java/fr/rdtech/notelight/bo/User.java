package fr.rdtech.notelight.bo;

public class User {
	private int id;
	private String mail;
	private String passeword;
	
	public User(int id, String mail, String passeword) {
		super();
		this.id = id;
		this.mail = mail;
		this.passeword = passeword;
	}

	public User(String mail, String passeword) {
		this.mail = mail;
		this.passeword = passeword;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPasseword() {
		return passeword;
	}

	public void setPasseword(String passeword) {
		this.passeword = passeword;
	}
}
