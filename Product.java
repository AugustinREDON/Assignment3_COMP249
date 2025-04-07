public class Product implements Comparable<Product> {
    private String productName;
    private String Country;
    private String Category;
    private double price;

    public Product(String productName, String Country, String Category, double price) {
        this.productName = productName;
        this.Country = Country;
        this.Category = Category;
        this.price = price;
    }
    public String getProductName() {
        return this.productName;
    }
    public String getCountry() {
        return this.Country;
    }
    public String getCategory() {
        return this.Category;
    }
    public double getPrice() {
        return this.price;
    }
    public int compareTo(Product other) {
        return this.productName.compareToIgnoreCase(other.productName);
    }
    public String toString() {
        return this.productName + "," + this.Country + "," + this.Category + "," + this.price;
    }
    
    
}
