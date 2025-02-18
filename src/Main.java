import Controller.Controller;
import Model.Character;
import Model.Product;
import Model.ValidationException;
import Repository.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    private Controller controller;

    public Main(Controller controller) {
        this.controller = controller;
    }

    /**
     * This is the UI of the app
     */
    private void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            try {
                System.out.print("""
                        Welcome to the Shop!
                        1. Add Character
                        2. Search Character
                        3. Update Character
                        4. Remove Character
                        5. View All Characters
                        6. Add Product
                        7. Search Product
                        8. Update Product
                        9. Remove Product
                        10. View All Products
                        11. Filter characters
                        12. Characters that bought from universe
                        13. Sorted Products for character
                        0. Exit
                        Please select an option:
                        """);
                String option = scanner.nextLine();
                switch (option) {
                    case "0":
                        running = false;
                        break;
                    case "1":
                        addCharacter(scanner);
                        break;
                    case "2":
                        searchCharacter(scanner);
                        break;
                    case "3":
                        updateCharacter(scanner);
                        break;
                    case "4":
                        deleteCharacter(scanner);
                        break;
                    case "5":
                        viewCharacters();
                        break;
                    case "6":
                        addProduct(scanner);
                        break;
                    case "7":
                        searchProduct(scanner);
                        break;
                    case "8":
                        updateProduct(scanner);
                        break;
                    case "9":
                        deleteProduct(scanner);
                        break;
                    case "10":
                        viewProducts(scanner);
                        break;
                    case "11":
                        filterCharacters(scanner);
                        break;
                    case "12":
                        charsThatBoughtFromUniverse(scanner);
                        break;
                    case "13":
                        sortProductPricesOfCharacter(scanner);
                        break;
                    default:
                        throw new ValidationException("Invalid option");
                }
            } catch (ValidationException e) {
                System.out.println(e.getMessage());
            }

        }
    }
    private void sortProductPricesOfCharacter(Scanner scanner) {
        System.out.println("Enter the Character ID:");
        Integer id = Integer.parseInt(scanner.nextLine());
        System.out.println("For the sort choose asc or desc:");
        String order = scanner.nextLine();
        List<Product> sortedProducts = controller.sortCharacterProducts(id,order);
        for (Product product : sortedProducts){
            System.out.println(product.toString());
        }
    }
    private void charsThatBoughtFromUniverse(Scanner scanner) {
        System.out.println("Enter universe");
        String universe = scanner.nextLine();
        List<Character> characters = controller.charactersThatBoughtFromUniverse(universe);
        List<Character> sortedChars = characters.stream().sorted(Comparator.comparing(Character::getName)).toList();
        for (Character character : sortedChars) {
            System.out.println(character);
        }
    }

    private void filterCharacters(Scanner scanner) {
        System.out.println("Enter region:");
        String region = scanner.nextLine();
        List<Character> filteredCharacters = controller.filterCharacters(region);
        for (Character character : filteredCharacters) {
            System.out.println(character.toString());
        }
    }

    /**
     * Shows a list of Products
     * @param scanner
     */
    private void viewProducts(Scanner scanner) {
        System.out.println("Here are the products:");
        List<Product> products = controller.getAllProducts();
        for (Product product: products){
            System.out.println(product.toString());
        }
    }

    /**
     * Deletes a Product from the repository
     * @param scanner
     */
    private void deleteProduct(Scanner scanner) {
        System.out.println("Enter the name of the Product you want to delete:");
        String name = scanner.nextLine();
        if(controller.deleteProduct(name)){
            System.out.println("Product deleted successfully.");
        }
        else {
            System.out.println("Product not found.");
        }
    }

    /**
     * Updates a Product
     * @param scanner
     */
    private void updateProduct(Scanner scanner) {
        System.out.println("Enter the id of the Product you want to update:");
        Integer id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter new name:");
        String name = scanner.nextLine();
        System.out.println("Enter new price:");
        Double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter new universe:");
        String universe = scanner.nextLine();
        Product product = controller.updateProduct(name,price,universe);
        if( product != null) {
            System.out.println("Product updated successfully.");
        }
        else {
            System.out.println("Product not found.");
        }
    }

    /**
     * Searches for a specific Product
     * @param scanner
     */
    private void searchProduct(Scanner scanner) {
        System.out.println("Enter the name of the Product you want to search:");
        String name = scanner.nextLine();
        Product product = controller.getProduct(name);
        if(product != null) {
            System.out.println(product);
        }
    }

    /**
     * Adds a Product to the repository
     * @param scanner
     */
    private void addProduct(Scanner scanner) {
        System.out.println("Enter Product ID:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Product Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Product Price:");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Product Universe:");
        String universe = scanner.nextLine();
        Product product = new Product(name, price, universe);
        product = controller.addProduct(product);
        if(product != null){
            System.out.println("Product added successfully.");
        } else
        {
            System.out.println("Product could not be added.");
        }
    }

    /**
     * Shows a list of Characters
     *
     */
    private void viewCharacters() {
        System.out.println("Here are the Characters");
        List<Model.Character> characters = controller.getAllCharacters();
        for (Model.Character character: characters){
            System.out.println(character.toString());
        }
    }

    /**
     * Deletes a Character from the repository
     * @param scanner
     */
    private void deleteCharacter(Scanner scanner) {
        System.out.println("Id of the Character you want to delete:");
        Integer id = Integer.parseInt(scanner.nextLine());
        if(controller.deleteCharacter(id)){
            System.out.println("Character deleted successfully.");
        }
        else {
            System.out.println("Character could not be deleted.");
        }
    }

    /**
     * Updates a Character
     * @param scanner
     */
    private void updateCharacter(Scanner scanner) {
        System.out.println("Enter the id of the Character you want to update:");
        Integer id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter new name:");
        String name = scanner.nextLine();
        System.out.println("Enter new region:");
        String region = scanner.nextLine();
        Model.Character character = controller.updateCharacter(id,name,region);
        if(character != null){
            System.out.println("Character updated successfully.");

        }
        else {
            System.out.println("Character not found.");
        }

    }

    /**
     * Searches for a specific Character
     * @param scanner
     */
    private void searchCharacter(Scanner scanner) {
        System.out.println("Enter Character ID:");
        Integer id = Integer.parseInt(scanner.nextLine());
        Model.Character character = controller.getCharacter(id);
        if( character != null) {
            System.out.println(character);
        }

    }

    /**
     * Adds a Character to the repository
     * @param scanner
     */
    private void addCharacter(Scanner scanner) {
        System.out.println("Enter Character ID:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Character Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Character Region:");
        String region = scanner.nextLine();
        Model.Character character = new Character(id, name, region);
        character = controller.addCharacter(character);
        if(character!= null) {
            System.out.println("Character added successfully.");
        }
        else {
            System.out.println("Character could not be added.");
        }
    }
    public static void main(String[] args) {
// Initialisierung der Producte
        RepositoryProduct<Product> productRepository = new InMemoryRepositoryProduct<Product>();
        Repository<Character> characterRepository = new InMemoryRepository<Character>();
        List<Product> produkte = new ArrayList<>();
        produkte.add(new Product("Mjolnir", 500.0, "Asgard"));
        produkte.add(new Product("Vibranium-Schild", 700.0, "Wakanda"));
        produkte.add(new Product("Infinity Gauntlet", 10000.0, "Titan"));
        produkte.add(new Product("Web-Shooter", 250.0, "Terra"));
        produkte.add(new Product("Arc-Reaktor", 1500.0, "Terra"));
        produkte.add(new Product("Norn Stones", 1200.0, "Asgard"));
        produkte.add(new Product("Quantum Suit", 3000.0, "Terra"));
        produkte.add(new Product("X-Gene Serum", 850.0, "X-Mansion"));
        produkte.add(new Product("Cosmic Cube", 9000.0, "Multiverse"));
        produkte.add(new Product("Darkhold", 2000.0, "Multiverse"));
// Initialisierung der Charactere
        List<Character> charaktere = new ArrayList<>();
        Character c1 = new Character(1, "Thor", "Asgard");
        c1.kaufeProduct(produkte.get(0)); // Mjolnir
        c1.kaufeProduct(produkte.get(5)); // Norn Stones
        c1.kaufeProduct(produkte.get(9)); // Darkhold
        Character c2 = new Character(2, "Black Panther", "Wakanda");
        c2.kaufeProduct(produkte.get(1)); // Vibranium-Schild
        c2.kaufeProduct(produkte.get(7)); // X-Gene Serum
        Character c3 = new Character(3, "Iron Man", "Terra");
        c3.kaufeProduct(produkte.get(4)); // Arc-Reaktor
        c3.kaufeProduct(produkte.get(6)); // Quantum Suit
        c3.kaufeProduct(produkte.get(3)); // Web-Shooter
        Character c4 = new Character(4, "Spider-Man", "Terra");
        c4.kaufeProduct(produkte.get(3)); // Web-Shooter
        c4.kaufeProduct(produkte.get(8)); // Cosmic Cube
        Character c5 = new Character(5, "Doctor Strange", "Multiverse");
        c5.kaufeProduct(produkte.get(9)); // Darkhold
        c5.kaufeProduct(produkte.get(8)); // Cosmic Cube
        c5.kaufeProduct(produkte.get(2)); // Infinity Gauntlet
        charaktere.add(c1);
        charaktere.add(c2);
        charaktere.add(c3);
        charaktere.add(c4);
        charaktere.add(c5);
        Controller controller = new Controller(productRepository, characterRepository);
        Main app = new Main(controller);
        app.start();
    }
}