package org.zerock.user;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserBean implements Serializable{
	private String u_Email;               //아이디
	private String u_Pw;               //패스워드
	private String u_Name;				//이름
	private String u_Address; 			//주소
	private String  u_gender ; //성별
	private String u_profile_path ; // 이미지 경로
	

	public UserBean() {
		super();
	}

	public UserBean(String u_Email, String u_Pw, String u_Name, String u_Address,
			String u_gender, String u_profile_path
			) {
		super();
		this.u_Email = u_Email;
		this.u_Pw = u_Pw;
		this.u_Name = u_Name;
		this.u_Address = u_Address;
		this.u_gender = u_gender;
		this.u_profile_path = u_profile_path;
	}





}
