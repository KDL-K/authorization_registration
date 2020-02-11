package by.htp.task.ar.dao;

import by.htp.task.ar.model.LogPass;
import by.htp.task.ar.model.User;
import by.htp.task.ar.model.UserLogPass;

public interface AuthRegDAO {
	public User authorize(LogPass logPass) throws DAOException;
	public boolean register(UserLogPass userLogPass)throws DAOException;

}
