public class Tariff {
    private String destinationCountry;
    private String originCountry;
    private String productCategory;
    private double minimumTariff;

    public Tariff(String destinationCountry, String originCountry, String productCategory, double minimumTariff) {
        this.destinationCountry = destinationCountry;
        this.originCountry = originCountry;
        this.productCategory = productCategory;
        this.minimumTariff = minimumTariff;

    }
    public String getDestinationCountry() {
        return destinationCountry;
    }
    public String getOriginCountry() {
        return originCountry;
    }
    public String getProductCategory() {
        return productCategory;
    }
    public double getMinimumTariff() {
        return minimumTariff;
    }
    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }
    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }
    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
    public void setMinimumTariff(double minimumTariff) {
        this.minimumTariff = minimumTariff;
    }
    public Tariff(Tariff tariff) {
        this.destinationCountry = tariff.getDestinationCountry();
        this.originCountry = tariff.getOriginCountry();
        this.productCategory = tariff.getProductCategory();
        this.minimumTariff = tariff.getMinimumTariff();
    }
    public Object clone(){

        return new Tariff(this);
    }
    public String toString(){
        return destinationCountry + " " + originCountry + " " + productCategory + " " + minimumTariff;

    }
    public boolean equals(Tariff tariff){
      return    destinationCountry.equals(tariff.getDestinationCountry()) &&
                originCountry.equals(tariff.getOriginCountry()) &&
                productCategory.equals(tariff.getProductCategory()) &&
                Double.compare(minimumTariff, tariff.getMinimumTariff()) == 0;
    }
}
