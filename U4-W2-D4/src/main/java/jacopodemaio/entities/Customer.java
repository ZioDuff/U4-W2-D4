package jacopodemaio.entities;

import java.util.Random;

public class Customer {
    //    ATTRIBUTI
    private Long id;
    private String name;
    private int tier;

    //    COSTRUTTORE
    public Customer(String name) {
        Random randomId = new Random();
        this.id = randomId.nextLong(1000, 9000);
        this.name = name;
        this.tier = new Random().nextInt(1, 5);

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

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tier=" + tier +
                '}';
    }
}
