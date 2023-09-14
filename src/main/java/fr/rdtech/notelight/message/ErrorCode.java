package fr.rdtech.notelight.message;

public abstract class ErrorCode {
	//DAL
	
	public static final int ERROR_CONNECTION = 10000;
	public static final int ERROR_INSERT= 10001;
	public static final int ERROR_SELECT = 10002;
	public static final int ERROR_UPDATE = 10003;
	public static final int ERROR_DELETE = 10004;
	
	//BLL
	public static final int ERROR_MAIL_EXIST = 20000;
	public static final int ERROR_USER_UNKNOWN = 20001;
	

}
