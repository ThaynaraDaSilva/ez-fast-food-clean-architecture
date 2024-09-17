package br.com.fiap.ez.fastfood.domain.model;

public class Product {
    
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Category category;

    public Product() {}

    public Product(Long id, String name, String description, double price,  Category category) {
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

	public void setPrice(Double price) {
		this.price = price;
	}
    
}