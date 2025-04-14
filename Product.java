// ---------------------------------------------------------------
// Assignment 3
// Question: Part 1 - Product Class for Tariff Calculation
// Written by: Augustin Redon 40240986 - Jacob Paterak 40268958
// ---------------------------------------------------------------


public class Product implements Comparable<Product> {
    // Instance variables representing the details of a product
    private String productName; 
    private String Country; 
    private String Category;
    private double price; 

    // Constructor to initialize a Product object with all details
    public Product(String productName, String Country, String Category, double price) {
        this.productName = productName; 
        this.Country = Country; 
        this.Category = Category; 
        this.price = price; 
    }

    // Getter for productName
    public String getProductName() {
        return this.productName; 
    }

    // Getter for Country
    public String getCountry() {
        return this.Country; 
    }

    // Getter for Category
    public String getCategory() {
        return this.Category; 
    }

    // Getter for price
    public double getPrice() {
        return this.price; 
    }

    // compareTo method to compare two Product objects by their product names
    @Override
    public int compareTo(Product other) {
        return this.productName.compareToIgnoreCase(other.productName);
    }

    // toString method to return a string representation of the Product object
    @Override
    public String toString() {
        return this.productName + "," + this.Country + "," + this.Category + "," + this.price;
    }
}
