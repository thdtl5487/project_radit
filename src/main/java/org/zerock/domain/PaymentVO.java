package org.zerock.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class PaymentVO {
	
	private String p_id;
	private String import_id;
	private String p_email;
	private int p_amount;
	private String p_num;
	private Date p_time;
	
}
