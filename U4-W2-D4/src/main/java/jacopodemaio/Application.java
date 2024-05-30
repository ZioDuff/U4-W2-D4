package jacopodemaio;

import com.github.javafaker.Faker;
import jacopodemaio.entities.Customer;
import jacopodemaio.entities.Order;
import jacopodemaio.entities.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Application {

    public static void main(String[] args) {
        //        Prezzo generato con un random dentro un supplier
        Supplier<Double> randomNumbersSupplier = () -> {
            Random rndm = new Random();
            return rndm.nextDouble(1.99, 299.99);
        };
//        array di stringhe contenenti categorie
        String[] category = {"Books", "Baby", "Boys", "Man", "Woman"};
//        generiamo le categorie in modo random in base all'array istanziato poco fa
        Supplier<String> categorySupplier = () -> {

            Random rndm = new Random();
            int index = rndm.nextInt(category.length);

            return category[index];
        };

//      tramite il supplier generiamo il prodotto come ci serve
        Supplier<Product> productSupplier = () -> new Product("NOME", categorySupplier.get(), randomNumbersSupplier.get());
//        istanziamo una nuova lista dove verra agiiunti i nostri prodotti
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
//            qui verranno aggiunti i nostri prodotti
            productList.add(productSupplier.get());

        }

//        proviamo a generare i nomi random con il faker

        Supplier<Customer> customerSupplier = () -> {
            Faker faker = new Faker();
            return new Customer(faker.dragonBall().character());
        };
        List<Customer> customerList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            customerList.add(customerSupplier.get());

        }
        System.out.println(customerList);

        //        genero date che ci serviranno per il costruttore della classe Order
        LocalDate today = LocalDate.now();
        LocalDate shipDay = today.plusDays(3);

//        creo un supplier con i parametri appena acquisiti
        Supplier<Order> orderSupplier = () -> new Order("da fare", today, shipDay, productList, customerSupplier.get());

//        e creo un ciclo in modo che stampi 5 ordini contenenti tutti una lista filtrata
        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            orderList.add(orderSupplier.get());

        }
    }
}
