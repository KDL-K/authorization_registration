package by.htp.task.ar.service;

import by.htp.task.ar.model.LogPass;
import by.htp.task.ar.model.User;
import by.htp.task.ar.model.UserLogPass;

public interface AuthRegService {
	public User authorize(LogPass logPass) throws ServiceException;
	public boolean register(UserLogPass userLogPass) throws ServiceException;

}
