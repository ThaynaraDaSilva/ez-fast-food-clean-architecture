//package br.com.fiap.ez.fastfood.domain.model;
//
//import io.swagger.v3.oas.annotations.media.Schema;
//import jakarta.persistence.*;
//
//@Entity
//@Schema(name= "ez_fastfood")
//@Table(name = "products")
//public class Product {
//	
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//	
//	@Column(name = "name", nullable = false)
//    private String name;
//	
//	@Column(name = "description", nullable = false)
//	private String description;
//	
//	@ManyToOne
//	@JoinColumn(name = "category_id", nullable = false)
//    private Category category;
//	
//	@Column(name = "price", nullable = false)
//	private double price;
//	
//	public Product() {}
//
//	public Product(Long id, String name, String description, Category category, double price) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.description = description;
//		this.category = category;
//		this.price = price;
//	}
//	
//	public Long getId() {
//		return id;
//	}
//	
//	public void setId(Long id) {
//		this.id = id;
//	}
//	
//	public String getName() {
//		return name;
//	}
//	
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//	public String getDescription() {
//		return description;
//	}
//	
//	public void setDescription(String description) {
//		this.description = description;
//	}
//	
//	public Category getCategory() {
//		return category;
//	}
//	
//	public void setCategory(Category category) {
//		this.category = category;
//	}
//	
//	public double getPrice() {
//		return price;
//	}
//	
//	public void setPrice(double price) {
//		this.price = price;
//	}
//
//}

package br.com.fiap.ez.fastfood.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Product() {}

    public Product(Long id, String name, String description, double price, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
