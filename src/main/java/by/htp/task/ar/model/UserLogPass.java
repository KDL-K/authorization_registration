package by.htp.task.ar.model;

public class UserLogPass {
	private User user;
	private LogPass logPass;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public LogPass getLogPass() {
		return logPass;
	}
	public void setLogPass(LogPass logPass) {
		this.logPass = logPass;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((logPass == null) ? 0 : logPass.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserLogPass other = (UserLogPass) obj;
		if (logPass == null) {
			if (other.logPass != null)
				return false;
		} else if (!logPass.equals(other.logPass))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
	

}
