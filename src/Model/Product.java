package Model;

public class Product  implements HasName{
    private String name;
    private double price;
    private String universe;

    public Product(String name, double price, String universe) {
        this.name = name;
        this.price = price;
        this.universe = universe;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUniverse() {
        return universe;
    }

    public void setUniverse(String universe) {
        this.universe = universe;
    }

    public Integer getName() {
        return Integer.parseInt(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Product{" + "name=" + name + ", price=" + price + ", universe=" + universe + '}';
    }
}
