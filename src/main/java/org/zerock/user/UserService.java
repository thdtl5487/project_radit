package org.zerock.user;

import java.util.List;
import java.util.Map;

public interface UserService {
	public List<UserBean> retrieveUserList();
	public UserBean retrieveUser(String id);
	public String retrieveUserId(String email);
	public String retrieveUserPw(String id, String email);
	public boolean logincheck(String id, String pw);
	public void deleteUser(String id);
	public UserBean retrieveSessionInfo(String id);
	public void updateUser(UserBean bean);
	public void createUser(UserBean bean);

	public void updateUserConnectedTime(Map<String, String> boardParam);

}
