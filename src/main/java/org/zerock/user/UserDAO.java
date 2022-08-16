package org.zerock.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<UserBean> retrieveUserList()  {
		return sqlSession.selectList("user.retrieveUserList");
	}

	public String retrieveUserId(String email){
		return sqlSession.selectOne("user.retrieveUserId", email);
	}

	public UserBean retrieveSessionInfo(String id){
		return sqlSession.selectOne("user.retrieveSessionInfo", id);
	}

	public String retrieveUserPw(String id, String email){
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("id", id);
		hm.put("email", email);
		return sqlSession.selectOne("user.retrieveUserPw", hm);
	}

	public UserBean retrieveUser(String id)  {
		return sqlSession.selectOne("user.retrieveUser", id);
	}

	public boolean logincheck(String id, String pw){
		boolean check = false;
		String email = null;
		HashMap<String, String> hm = new HashMap<String, String>();

		hm.put("loginId", id);
		hm.put("pw", pw);

		email = sqlSession.selectOne("user.logincheck", hm);
		if(email != null){
			check = true;
		}
		return check;
	}

	public void deleteUser(String id)  {
		sqlSession.delete("user.deleteUser",id);
	}

	public void createUser(UserBean bean)  {
		sqlSession.insert("user.createUser", bean);
	}

	public void updateUser(UserBean bean)  {
		sqlSession.update("user.updateUser", bean);
	}

	public void updateUserConnectedTime(Map<String, String> boardParam){
		sqlSession.update("user.updateUserConnectedTime", boardParam);
	}

}
