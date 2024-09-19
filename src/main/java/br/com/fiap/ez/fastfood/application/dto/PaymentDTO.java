package br.com.fiap.ez.fastfood.application.dto;

public class PaymentDTO {
	
	private Long paymentId;
	private String paymentStatus;
	
	
	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

}
