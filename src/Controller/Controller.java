package Controller;

import Model.Character;
import Model.EntityNotFoundException;
import Model.Product;
import Repository.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Controller {
    private final RepositoryProduct<Product> productRepository;
    private final Repository<Character> characterRepository;

    /**
     * Constructor for the Controller
     *
     * @param productRepository
     * @param characterRepository
     */
    public Controller(RepositoryProduct<Product> productRepository, Repository<Character> characterRepository) {
        this.productRepository = productRepository;
        this.characterRepository = characterRepository;
    }

    public Product addProduct(Product product) {
        Integer name = product.getName();
        if (productRepository.get(String.valueOf(name)) != null) {
            return null;
        } else {
            productRepository.create(product);
            return product;
        }
    }

    public Product getProduct(String name) {
        try {
            Product product = productRepository.get(name);
            if (product != null) {
                return product;
            } else throw new EntityNotFoundException("Product not found");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Product updateProduct(String name, Double price, String universe) {
        Product product = productRepository.get(name);
        if (product != null) {
            product.setName(name);
            product.setPrice(price);
            product.setUniverse(universe);
            productRepository.update(product);
            return product;
        } else return null;
    }

    public boolean deleteProduct(String name) {
        if (productRepository.get(name) != null) {
            productRepository.delete(name);
            return true;
        } else return false;
    }

    public List<Product> getAllProducts() {
        return productRepository.getAll();
    }

    public Character addCharacter(Character character) {
        Integer id = character.getID();
        if (characterRepository.get(id) != null) {
            return null;
        } else {
            characterRepository.create(character);
            return character;
        }
    }

    public List<Character> getAllCharacters() {
        return characterRepository.getAll();
    }

    public Character getCharacter(Integer id) {
        try {
            Character character = characterRepository.get(id);
            if (character != null) {
                return character;
            } else throw new EntityNotFoundException("Character not found");
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Character updateCharacter(Integer id, String name, String region) {
        Character character = characterRepository.get(id);
        if (character != null) {
            character.setName(name);
            character.setRegion(region);
            characterRepository.update(character);
            return character;
        } else return null;
    }

    public boolean deleteCharacter(Integer id) {
        if (characterRepository.get(id) != null) {
            characterRepository.delete(id);
            return true;
        } else return false;
    }

    public List<Character> filterCharacters(String region) {
        return characterRepository.getAll().stream().filter(character -> character.getRegion().equals(region)).toList();
    }

    public List<Character> charactersThatBoughtFromUniverse(String universe) {
        List<Character> characters = characterRepository.getAll();
        List<Character> filteredCharacters = new ArrayList<>();
        for (Character character : characters) {
            for (Product product : character.getProducts()) {
                if (product.getUniverse().equals(universe)) {
                    filteredCharacters.add(character);
                    break;
                }
            }
        }
        return filteredCharacters;
    }
}
