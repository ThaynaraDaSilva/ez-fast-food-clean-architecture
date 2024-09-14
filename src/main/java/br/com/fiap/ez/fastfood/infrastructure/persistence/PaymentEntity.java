package br.com.fiap.ez.fastfood.infrastructure.persistence;

import jakarta.persistence.*;
import java.time.ZonedDateTime;

import br.com.fiap.ez.fastfood.domain.model.PaymentStatus;

@Entity
@Table(name = "payment")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = true)
    private CustomerEntity customer;

    @Column(name = "payment_date", nullable = true)
    private ZonedDateTime paymentDate;

    @Column(name = "payment_price", nullable = false)
    private Double paymentPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;

    public PaymentEntity() {
    }

    public PaymentEntity(Long id, OrderEntity order, CustomerEntity customer, ZonedDateTime paymentDate,
                         Double paymentPrice, PaymentStatus paymentStatus) {
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

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
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
