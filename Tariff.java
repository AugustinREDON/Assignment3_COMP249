// ---------------------------------------------------------------
// Assignment 3
// Question: Part 1 - Tariff Adjustment with ArrayList & File I/O
// Written by: Augustin Redon 40240986 - Jacob Paterak 40268958
// ---------------------------------------------------------------


public class Tariff {

    // Instance variables representing the details of a tariff
    private String reqID; 
    private String destinationCountry; 
    private String originCountry; 
    private String productCategory;
    private double minimumTariff; 
    private double proposedTariff;
    private int value;

    // Constructor to initialize a Tariff object with all details
    public Tariff(String reqID, String destinationCountry, String originCountry, String productCategory, double proposedTariff, int value) {
        this.reqID = reqID;
        this.destinationCountry = destinationCountry;
        this.originCountry = originCountry;
        this.productCategory = productCategory;
        this.minimumTariff = 0; 
        this.proposedTariff = proposedTariff;
        this.value = value;
    }

    // Constructor to initialize a Tariff object with minimum tariff details
    public Tariff(String destinationCountry, String originCountry, String productCategory, double minimumTariff) {
        this.destinationCountry = destinationCountry;
        this.originCountry = originCountry;
        this.productCategory = productCategory;
        this.minimumTariff = minimumTariff;
        this.proposedTariff = 0; 
        this.value = 0; 
    }

    // Getter for destinationCountry
    public String getDestinationCountry() {
        return destinationCountry;
    }

    // Getter for originCountry
    public String getOriginCountry() {
        return originCountry;
    }

    // Getter for productCategory
    public String getProductCategory() {
        return productCategory;
    }

    // Getter for minimumTariff
    public double getMinimumTariff() {
        return minimumTariff;
    }

    // Getter for reqID
    public String getReqID() {
        return reqID;
    }

    // Getter for proposedTariff
    public double getProposedTariff() {
        return proposedTariff;
    }

    // Getter for value
    public int getValue() {
        return value;
    }

    // Setter for destinationCountry
    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    // Setter for originCountry
    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    // Setter for productCategory
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    // Setter for minimumTariff
    public void setMinimumTariff(double minimumTariff) {
        this.minimumTariff = minimumTariff;
    }

    // Setter for reqID
    public void setReqID(String reqID) {
        this.reqID = reqID;
    }

    // Copy constructor to create a new Tariff object as a copy of another Tariff
    public Tariff(Tariff tariff) {
        this.destinationCountry = tariff.getDestinationCountry();
        this.originCountry = tariff.getOriginCountry();
        this.productCategory = tariff.getProductCategory();
        this.minimumTariff = tariff.getMinimumTariff();
    }

    // Clone method to create a deep copy of the current Tariff object
    @Override
    public Tariff clone() {
        return new Tariff(this); // Uses the copy constructor
    }

    // toString method to return a string representation of the Tariff object
    public String toString() {
        return destinationCountry + " " + originCountry + " " + productCategory + " " + minimumTariff;
    }

    // equals method to compare two Tariff objects for equality
    public boolean equals(Tariff tariff) {
        return destinationCountry.equals(tariff.getDestinationCountry()) && // Compare destination countries
               originCountry.equals(tariff.getOriginCountry()) && // Compare origin countries
               productCategory.equals(tariff.getProductCategory()) && // Compare product categories
               Double.compare(minimumTariff, tariff.getMinimumTariff()) == 0; // Compare minimum tariffs
    }
}
