package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {
	private int b_number;
	private String b_title;
	private String b_img;
	private String b_video;
	private String b_text;
	private Date b_regDate;
	private Date b_updateDate;
	private String u_email;
	
	// might need attachfilelist
}
