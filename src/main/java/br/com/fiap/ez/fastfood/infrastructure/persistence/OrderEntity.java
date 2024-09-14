package br.com.fiap.ez.fastfood.infrastructure.persistence;

import jakarta.persistence.*;
import java.time.ZonedDateTime;
import java.util.List;

import br.com.fiap.ez.fastfood.domain.model.OrderStatus;

@Entity
@Table(name = "`order`")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = true)
    private CustomerEntity customer;

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
    private List<OrderItemEntity> orderItems;

    public OrderEntity() {
        // Default constructor
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
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

    
    public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerName() {
        return customerName;
    }
    
    public List<OrderItemEntity> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemEntity> orderItems) {
		this.orderItems = orderItems;
	}

}