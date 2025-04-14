public class Product implements Comparable<Product> {
    // Instance variables representing the details of a product
    private String productName; // Name of the product
    private String Country; // Country of origin
    private String Category; // Product category
    private double price; // Price of the product

    // Constructor to initialize a Product object with all details
    public Product(String productName, String Country, String Category, double price) {
        this.productName = productName; // Set the product name
        this.Country = Country; // Set the country of origin
        this.Category = Category; // Set the product category
        this.price = price; // Set the price of the product
    }

    // Getter for productName
    public String getProductName() {
        return this.productName; // Return the product name
    }

    // Getter for Country
    public String getCountry() {
        return this.Country; // Return the country of origin
    }

    // Getter for Category
    public String getCategory() {
        return this.Category; // Return the product category
    }

    // Getter for price
    public double getPrice() {
        return this.price; // Return the price of the product
    }

    // compareTo method to compare two Product objects by their product names
    @Override
    public int compareTo(Product other) {
        // Compare product names ignoring case sensitivity
        return this.productName.compareToIgnoreCase(other.productName);
    }

    // toString method to return a string representation of the Product object
    @Override
    public String toString() {
        // Format: productName,Country,Category,price
        return this.productName + "," + this.Country + "," + this.Category + "," + this.price;
    }
}
