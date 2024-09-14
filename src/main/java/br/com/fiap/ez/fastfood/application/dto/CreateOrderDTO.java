package br.com.fiap.ez.fastfood.application.dto;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class CreateOrderDTO {

	@JsonProperty("customer_name")
	private String customerName;

	//@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("customer_cpf")
	@Schema(description = "Customer CPF (optional)", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
	private String customerCpf = "";

	@JsonProperty("order_items")
	private List<OrderItemDTO> orderItems;

	// Getters and setters

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerCpf() {
		return customerCpf;
	}

	public void setCustomerCpf(String customerCpf) {
		this.customerCpf = customerCpf;
	}

	public List<OrderItemDTO> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDTO> orderItems) {
		this.orderItems = orderItems;
	}
}
