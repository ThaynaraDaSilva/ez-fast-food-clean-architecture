package br.com.fiap.ez.fastfood.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "`order`") // Syntax to avoid conflict with reserved names
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = true)
	private Customer customer;

	@Column(name = "order_time")
	private ZonedDateTime orderTime;

	@Column(name = "completed_time", nullable = true)
	private ZonedDateTime completedTime;

	@Column(name = "total_price")
	private Double totalPrice;

	@Enumerated(EnumType.STRING)
	@Column(name = "order_status", nullable = true)
	private OrderStatus status;

	@Column(name = "customer_name", nullable = true)
	private String customerName;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> orderItems;

	public Order() {
		// Default constructor
	}

	public Order(Long id, Customer customer, ZonedDateTime orderTime, ZonedDateTime completedTime, Double totalPrice,
			OrderStatus status, String customerName, List<OrderItem> orderItems) {
		this.id = id;
		this.customer = customer;
		this.orderTime = orderTime;
		this.completedTime = completedTime;
		this.totalPrice = totalPrice;
		this.status = status;
		this.customerName = customerName;
		this.orderItems = orderItems;
	}

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public ZonedDateTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(ZonedDateTime orderTime) {
		this.orderTime = orderTime;
	}

	public ZonedDateTime getCompletedTime() {
		return completedTime;
	}

	public void setCompletedTime(ZonedDateTime completedTime) {
		this.completedTime = completedTime;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public void calculateAndSetTotalPrice() {
		this.totalPrice = calculateTotalPrice(this.orderItems);
	}

	public static double calculateTotalPrice(List<OrderItem> orderItems) {
		double total = 0;
		for (OrderItem item : orderItems) {
			total += item.getQuantity() * item.getProduct().getPrice();
		}
		return total;
	}

}
