package jacopodemaio;

import com.github.javafaker.Faker;
import jacopodemaio.entities.Customer;
import jacopodemaio.entities.Order;
import jacopodemaio.entities.Product;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
        for (int i = 0; i < 5; i++) {
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
//        stampo la lista di customer per vedere il risultato del faker
//        System.out.println(customerList);

        //        genero date che ci serviranno per il costruttore della classe Order
        LocalDate today = LocalDate.now();
        LocalDate shipDay = today.plusDays(3);

//        creo un supplier con i parametri appena acquisiti
        Supplier<Order> orderSupplier = () -> new Order("da fare", today, shipDay, productList, customerSupplier.get());

//        e creo un ciclo in modo che stampi 5 ordini contenenti tutti una lista filtrata
        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            orderList.add(orderSupplier.get());

        }
//        _________________________________________ Struttura generale ___________________________________________________
        System.out.println("****************************** ES1 *******************************************");
        Map<String, List<Order>> customerOrder = orderList.stream().collect(Collectors.groupingBy(order -> order.getCustomer().getName()));
        customerOrder.forEach((nome, listaOrdini) -> System.out.println("Nome " + nome + ", " + listaOrdini));
        System.out.println();


        System.out.println("****************************** ES2 *******************************************");
//        facciamo la somma dei prodotti
        Map<Customer, DoubleSummaryStatistics> totalCost = orderList.stream()
                .collect(Collectors.groupingBy(order -> order.getCustomer(),
                        Collectors.summarizingDouble(order -> order.getProducts().stream().mapToDouble(Product::getPrice).sum())));
        totalCost.forEach((nome, costoTotale) -> System.out.println("Nome " + nome + ", " + "Hai speso un totale di " + costoTotale));
        System.out.println();


        System.out.println("****************************** ES3 *******************************************");
        List<Product> highToLow = productList.stream().sorted(Comparator.comparingDouble(Product::getPrice).reversed()).limit(3).toList();
        highToLow.forEach(System.out::println);
        System.out.println();


        System.out.println("****************************** ES4 *******************************************");
//        Double comparePrice = orderList.stream()
//                .collect(Collectors.averagingDouble(order -> order.getProducts().stream().collect(Collectors.averagingDouble(Product::getPrice))));
//        System.out.println(comparePrice);

        Map<Long, Double> mediaProdotti = orderList.stream().collect(Collectors.groupingBy(order -> order.getId(), Collectors.averagingDouble(order -> order.getProducts().stream().mapToDouble(product -> product.getPrice()).sum())));
        mediaProdotti.forEach((ordine, media) -> System.out.println("il tuo ordine " + ordine + " questa e la media di quanto spenderai " + media));
        System.out.println();


        System.out.println("****************************** ES5 *******************************************");
        Map<String, Double> categoriaESommaPrezzi = productList.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.summingDouble(Product::getPrice)));
        categoriaESommaPrezzi.forEach((categoria, somma) -> System.out.println("questa è la categoria " + categoria + " questa  è la somma di tutti i prodotti presente in essa " + somma));


    }
}
