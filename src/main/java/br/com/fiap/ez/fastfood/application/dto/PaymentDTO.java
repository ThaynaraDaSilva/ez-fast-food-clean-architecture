package br.com.fiap.ez.fastfood.application.dto;

import java.util.Date;

public class PaymentDTO {

	private Long orderId;
	//private Date paymentDate;
	//private Double paymentPrice;
	//private PaymentDTO paymentStatus;

	/*
	 * public PaymentDTO(Long orderId, Date paymentDate, Double paymentPrice,
	 * PaymentDTO paymentStatus) { super(); this.orderId = orderId; this.paymentDate
	 * = paymentDate; this.paymentPrice = paymentPrice; this.paymentStatus =
	 * paymentStatus; }
	 */

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	

}
