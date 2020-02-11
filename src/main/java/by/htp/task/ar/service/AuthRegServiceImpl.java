package by.htp.task.ar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.htp.task.ar.dao.AuthRegDAO;
import by.htp.task.ar.dao.DAOException;
import by.htp.task.ar.model.LogPass;
import by.htp.task.ar.model.User;
import by.htp.task.ar.model.UserLogPass;

@Service
public class AuthRegServiceImpl implements AuthRegService{
	@Autowired
	private AuthRegDAO authRegDAO;
	
	public User authorize(LogPass logPass) throws ServiceException{
		User user = null;
		try {
			user = authRegDAO.authorize(logPass);
		}catch (DAOException e) {
			throw new ServiceException();
		}
		return user;
		
	}
	
	public boolean register(UserLogPass userLogPass) throws ServiceException{
		boolean isRegistered = false;
		try {
			isRegistered = authRegDAO.register(userLogPass);
		}catch (DAOException e) {
			throw new ServiceException();
		}
		return isRegistered;
		
	}

}
