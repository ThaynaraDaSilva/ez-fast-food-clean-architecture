package br.com.fiap.ez.fastfood.domain.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class Order {

	private Long id;
	private String orderNumber;
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

	public Order(Long id, String orderNumber, Customer customer, ZonedDateTime orderTime, ZonedDateTime completedTime,
			Double totalPrice, OrderStatus status, String customerName, List<OrderItem> orderItems) {
		this.id = id;
		this.orderNumber = orderNumber;
		this.customer = customer;
		this.orderTime = orderTime;
		this.completedTime = completedTime;
		this.totalPrice = totalPrice;
		this.status = status;
		this.customerName = customerName;
		this.orderItems = orderItems;
	}
	
	public static double calculateTotalPrice(List<OrderItem> orderItems) {
		double total = 0;
		for (OrderItem item : orderItems) {
			total += item.getQuantity() * item.getProduct().getPrice();
		}
		return total;
	}

	public String calculateOrderWaitedTime(ZonedDateTime orderTime) {
		
		ZonedDateTime time = orderTime.withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
		ZonedDateTime now = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
		Duration duration = Duration.between(time, now);
		long hours = duration.toHours();
		long minutes = duration.toMinutes() % 60;
		return String.format("%02dh%02d", hours, minutes);
	}
	
	public void calculateAndSetTotalPrice() {
		this.totalPrice = calculateTotalPrice(this.orderItems);
	}

	
	
	public String generateOrderNumber(Order order) {
		ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
		
	    LocalDate now = zonedDateTime.toLocalDate();
	
		LocalDate orderDate = orderTime.withZoneSameInstant(ZoneId.of("America/Sao_Paulo")).toLocalDate();
		
		int nextOrderNumber;
		//String lastOrderNumber="";	

		if (orderDate.equals(now)) {
			System.out.println("===================metodo de generate order");
			if(order!=null) {
				String lastOrderNumber = order.getOrderNumber().split(" ")[0]; // Pega a parte numérica
				nextOrderNumber = Integer.parseInt(lastOrderNumber) + 1;
			}else {
				nextOrderNumber =+ 1;
			}
	
		} else {
			// Se a data for diferente, reseta para 0000
			nextOrderNumber = 0;
		}
		
		System.out.println("===================");

		// Gera o número de pedido no formato "0000" + Nome do Cliente
		String formattedOrderNumber = String.format("%04d", nextOrderNumber);
		return formattedOrderNumber + " " + customerName;
	}
	
	

	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ZonedDateTime getOrderTime() {
		return orderTime.withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
	}

	public void setOrderTime(ZonedDateTime orderTime) {
		this.orderTime = orderTime;
	}

	public ZonedDateTime getCompletedTime() {
		return completedTime.withZoneSameInstant(ZoneId.of("America/Sao_Paulo"));
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




}
