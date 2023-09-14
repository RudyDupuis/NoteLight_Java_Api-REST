package fr.rdtech.notelight.bll;

import fr.rdtech.notelight.bo.User;
import fr.rdtech.notelight.dal.DAOFactory;
import fr.rdtech.notelight.dal.UserDAO;
import fr.rdtech.notelight.message.ErrorCode;
import fr.rdtech.notelight.message.MessageReader;

public class UserManager{
	private static UserManager instance;
	
	public static UserManager getInstance() {
		if(instance == null) {
			instance = new UserManager();
		}
		
		return instance;
	}
	
	private UserDAO dao = DAOFactory.getUserDAO();

	public void insert(User user) throws Exception {
		if(!dao.checkExistMail(user.getMail())) {
			dao.insert(user);
		} else {
			throw new Exception(MessageReader.getErrorMessage(ErrorCode.ERROR_MAIL_EXIST));
		}
		
	}

	public User connectionUser(User user) throws Exception {
		User authUser = dao.connectionUser(user);
		
		if (authUser != null) {
			return authUser;
		} else {
			throw new Exception(MessageReader.getErrorMessage(ErrorCode.ERROR_USER_UNKNOWN));
		}
	}
	
	
}
