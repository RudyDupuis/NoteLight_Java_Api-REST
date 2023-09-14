package fr.rdtech.notelight.dal;

import fr.rdtech.notelight.bo.User;

public interface UserDAO {
	void insert(User user) throws Exception;
	Boolean checkExistMail(String mail) throws Exception;
	User connectionUser(User user) throws Exception;
}
