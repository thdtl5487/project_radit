package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.PaymentVO;
import org.zerock.mapper.PaymentMapper;


@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentMapper mapper;

	@Override
	public void insertPaymentSuccess(PaymentVO vo) {
		mapper.insertPaymentSuccess(vo);
	}
	
	
	
	
}
