package jacopodemaio.entities;

import java.util.Random;

public class Product {
    //    ATTRIBUTI
    private Long id;
    private String name;
    private String category;
    private Double price;

    //    COSTRUTTORE
    public Product(String name, String category, Double price) {
        Random randomId = new Random();
        this.id = randomId.nextLong(10000, 90000);
        this.name = name + " " + this.id;
        this.category = category;
        this.price = price;

    }
//    METODI

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
