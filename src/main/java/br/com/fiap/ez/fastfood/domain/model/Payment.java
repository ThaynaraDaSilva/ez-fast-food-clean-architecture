
package br.com.fiap.ez.fastfood.domain.model;

import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.Date;

@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;

	@ManyToOne

	@JoinColumn(name = "customer_id", nullable = true)
	private Customer customer;

	@Temporal(TemporalType.TIMESTAMP)

	@Column(name = "payment_date", nullable = true)
	private ZonedDateTime paymentDate;

	@Column(name = "payment_price", nullable = false)
	private Double paymentPrice;

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_status", nullable = false)
	private PaymentStatus paymentStatus;
	
	

	public Payment() {
		super();
	}

	public Payment(Long id, Order order, Customer customer, ZonedDateTime paymentDate, Double paymentPrice,
			PaymentStatus paymentStatus) {
		super();
		this.id = id;
		this.order = order;
		this.customer = customer;
		this.paymentDate = paymentDate;
		this.paymentPrice = paymentPrice;
		this.paymentStatus = paymentStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ZonedDateTime getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(ZonedDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Double getPaymentPrice() {
		return paymentPrice;
	}

	public void setPaymentPrice(Double paymentPrice) {
		this.paymentPrice = paymentPrice;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	
}
