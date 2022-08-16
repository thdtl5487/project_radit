package org.zerock.user;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService{
	@Resource(name = "userDAO")
	private UserDAO UserDAO;

	@Autowired
	public void setUserDAO(UserDAO dao){
		this.UserDAO = dao;
	}

	public String retrieveUserId(String email){
		return UserDAO.retrieveUserId(email);
	}

	public String retrieveUserPw(String id, String email){
		return UserDAO.retrieveUserPw(id, email);
	}

	public List<UserBean> retrieveUserList()  {
		return UserDAO.retrieveUserList();
	}

	public UserBean retrieveUser(String id)  {
		return UserDAO.retrieveUser(id);
	}

	public UserBean retrieveSessionInfo(String id)  {
		return UserDAO.retrieveSessionInfo(id);
	}

	public boolean logincheck(String id, String pw){
		return UserDAO.logincheck(id, pw);
	}

	public void deleteUser(String id)  {
		UserDAO.deleteUser(id);
	}

	public void createUser(UserBean bean)  {
		UserDAO.createUser(bean);
	}

	public void updateUser(UserBean bean)  {
		UserDAO.updateUser(bean);
	}

	public void updateUserConnectedTime(Map<String, String> boardParam){
		UserDAO.updateUserConnectedTime(boardParam);
	}

}
