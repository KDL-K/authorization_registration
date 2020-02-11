package by.htp.task.ar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import by.htp.task.ar.model.LogPass;
import by.htp.task.ar.model.User;
import by.htp.task.ar.model.UserLogPass;

@Repository
public class AuthRegDAOImpl implements AuthRegDAO{
	@Autowired
	ComboPooledDataSource dataSource;
	
	
	public User authorize(LogPass logPass) throws DAOException{
		final String GET_USER_QUERY = "SELECT* FROM user WHERE login=? AND password=?";
		
		User user = null;
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = dataSource.getConnection();
			ps = connection.prepareStatement(GET_USER_QUERY);
			ps.setString(1, logPass.getLogin());
			ps.setString(2, logPass.getPassword());
			rs = ps.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setSurname(rs.getString("surname"));
			}
		}catch(SQLException e) {
			throw new DAOException();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null) {
				try {
					ps.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return user;
	}
	
	public boolean register(UserLogPass userLogPass) throws DAOException{
		final String CHECK_USER_QUERY = "SELECT login FROM user WHERE login=?";
		final String SET_NEW_USER_QUERY = "INSERT INTO user(name, surname, login, password)"
				+ " VALUE(?,?,?,?)";
		
		Connection connection = null;
		PreparedStatement psCheckUser = null;
		PreparedStatement psSetNewUser = null;
		ResultSet rsCheckUser = null;
		
        try {
        	connection = dataSource.getConnection();
        	psCheckUser = connection.prepareStatement(CHECK_USER_QUERY);
        	psCheckUser.setString(1, userLogPass.getLogPass().getLogin());
        	rsCheckUser = psCheckUser.executeQuery();
        	if(rsCheckUser.next()) {
        		return false;
        	}
        	psSetNewUser = connection.prepareStatement(SET_NEW_USER_QUERY);
        	psSetNewUser.setString(1, userLogPass.getUser().getName());
        	psSetNewUser.setString(2, userLogPass.getUser().getSurname());
        	psSetNewUser.setString(3, userLogPass.getLogPass().getLogin());
        	psSetNewUser.setString(4, userLogPass.getLogPass().getPassword());
        	psSetNewUser.executeUpdate();
        	
		}catch(SQLException e) {
			throw new DAOException();
		}finally {
			if(rsCheckUser!=null) {
				try {
					rsCheckUser.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(psCheckUser!=null) {
				try {
					psCheckUser.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(psSetNewUser!=null) {
				try {
					psSetNewUser.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

}
