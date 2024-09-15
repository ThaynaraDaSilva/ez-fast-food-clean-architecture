package br.com.fiap.ez.fastfood.domain.model;

import java.time.ZonedDateTime;

public class Payment {

    private Long id;
    private Order order;
    private Customer customer;
    private ZonedDateTime paymentDate;
    private Double paymentPrice;
    private PaymentStatus paymentStatus;

    public Payment() {
    }

    public Payment(Long id, Order order, Customer customer, ZonedDateTime paymentDate, Double paymentPrice,
                   PaymentStatus paymentStatus) {
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
