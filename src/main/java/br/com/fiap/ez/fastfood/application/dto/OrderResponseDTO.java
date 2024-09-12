package br.com.fiap.ez.fastfood.application.dto;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.fiap.ez.fastfood.domain.model.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;



public class OrderResponseDTO {
	

	@JsonProperty("order_id")
	private Long orderId = 0L;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("customer_cpf")
	@Schema(description = "Customer CPF (optional)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String customerCpf = "";

	@JsonProperty("customer_name")
	private String customerName = "";

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@JsonProperty("order_time")
	private ZonedDateTime  orderTime;

	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@JsonProperty("completed_time")
	private ZonedDateTime completedTime=null;
	
	

	@JsonProperty("total_price")
	private Double totalPrice;

	@JsonProperty("status")
	private OrderStatus orderStatus = null;

	@JsonProperty("order_items")
	private List<OrderItemDTO> orderItems;
	
	 @JsonProperty("waited_time")
	 private String waitedTime = "";

	public OrderResponseDTO() {
		// Default constructor
		this.orderItems = new ArrayList<>();
	}

	
	public OrderResponseDTO(Long orderId, String customerName, ZonedDateTime orderTime, ZonedDateTime completedTime, Double totalPrice,
			OrderStatus orderStatus) {
		super();
		this.orderId = orderId;
		this.customerName = customerName;
		this.orderTime = orderTime;
		this.completedTime = completedTime;
		this.totalPrice = totalPrice;
		this.orderStatus = orderStatus;
	}




	public OrderResponseDTO(Long orderId, String customerCpf, String customerName, ZonedDateTime orderTime, ZonedDateTime completedTime,
			Double totalPrice, OrderStatus orderStatus) {
		this.orderId = orderId;
		this.customerCpf = customerCpf;
		this.customerName = customerName;
		this.orderTime = orderTime;
		this.completedTime = completedTime;
		this.totalPrice = totalPrice;
		this.orderStatus = orderStatus;
	}


	public OrderResponseDTO(String customerCpf, String customerName, ZonedDateTime orderTime, ZonedDateTime completedTime, Double totalPrice,
			OrderStatus orderStatus, List<OrderItemDTO> orderItems) {
		super();
		this.customerCpf = customerCpf;
		this.customerName = customerName;
		this.orderTime = orderTime;
		this.completedTime = completedTime;
		this.totalPrice = totalPrice;
		this.orderStatus = orderStatus;
		this.orderItems = orderItems;
	}

	// Getters and setters

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<OrderItemDTO> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDTO> orderItems) {
		this.orderItems = orderItems;
	}

	public String getCustomerCpf() {
		return customerCpf;
	}

	public void setCustomerCpf(String customerCpf) {
		this.customerCpf = customerCpf;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


	public String getWaitedTime() {
		return waitedTime;
	}


	public void setWaitedTime(String waitedTime) {
		this.waitedTime = waitedTime;
	}



}
