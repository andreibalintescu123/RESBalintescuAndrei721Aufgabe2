package Model;

import java.util.ArrayList;
import java.util.List;

public class Character implements HasID{
    private int id;
    private String name;
    private String region;
    private List<Product> products;

    public Character(int id, String name, String region) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.products = new ArrayList();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public Integer getID() {
        return this.id;
    }

    public String toString(){
        return "Character{" + "id=" + id + ", name=" + name + ", region=" + region + ", products=" + products + '}';
    }

    public void kaufeProduct(Product product){
        this.products.add(product);
    }
}
