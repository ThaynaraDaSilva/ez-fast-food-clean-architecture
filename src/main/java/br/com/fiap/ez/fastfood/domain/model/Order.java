package br.com.fiap.ez.fastfood.domain.model;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class Order {

	private Long id;
	private Customer customer;
	private ZonedDateTime orderTime;
	private ZonedDateTime completedTime;
	private Double totalPrice;
	private OrderStatus status;
	private String customerName;
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

	private String calculateOrderWaitedTime(Order order) {
		ZonedDateTime orderTime = order.getOrderTime().withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
		Duration duration = Duration.between(orderTime, now);
		long hours = duration.toHours();
		long minutes = duration.toMinutes() % 60;
		return String.format("%02dh%02d", hours, minutes);
	}
	
	
}
