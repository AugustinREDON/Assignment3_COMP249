import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Part_1 {
    public static void main(String[] args) throws IOException {
        
        // BufferedReader to read data from TradeData.txt
        BufferedReader br = new BufferedReader(new FileReader("TradeData.txt"));
        // BufferedWriter to write updated data to UpdatedTradeData.txt
        BufferedWriter bw = new BufferedWriter(new FileWriter("UpdatedTradeData.txt"));
        // ArrayList to store Product objects
        ArrayList<Product> Products = new ArrayList<Product>();


        try {
            // Scanner to read lines from the BufferedReader
            Scanner sc = new Scanner(br);
            // Iterate through each line in the file
            while (sc.hasNextLine()) {
                String line = sc.nextLine(); // Read a line from the file
                try {
                    // Scanner to parse the line using a comma as the delimiter
                    Scanner linescan = new Scanner(line);
                    linescan.useDelimiter(",");

                    // Parse the line into Product details
                    String ProductName = linescan.next(); // Product name
                    String country = linescan.next(); // Country of origin
                    String category = linescan.next(); // Product category
                    double InitialPrice = linescan.nextDouble(); // Initial price of the product

                    // Apply tariffs based on the country and category
                    switch (country) {
                        case "China":
                            InitialPrice *= 1.25; // Apply 25% tariff for China
                            break;
                        case "USA":
                            if (category.equals("Electronics"))
                                InitialPrice *= 1.1; // Apply 10% tariff for Electronics from the USA
                            break;
                        case "Japan":
                            if (category.equals("Automobiles"))
                                InitialPrice *= 1.15; // Apply 15% tariff for Automobiles from Japan
                            break;
                        case "India":
                            if (category.equals("Agriculture"))
                                InitialPrice *= 1.05; // Apply 5% tariff for Agriculture from India
                            break;
                        case "South Korea":
                            if (category.equals("Electronics"))
                                InitialPrice *= 1.08; // Apply 8% tariff for Electronics from South Korea
                            break;
                        case "Saudi Arabia":
                            if (category.equals("Energy"))
                                InitialPrice *= 1.12; // Apply 12% tariff for Energy from Saudi Arabia
                            break;
                        case "Germany":
                            if (category.equals("Manufacturing"))
                                InitialPrice *= 1.06; // Apply 6% tariff for Manufacturing from Germany
                            break;
                        case "Bangladesh":
                            if (category.equals("Textile"))
                                InitialPrice *= 1.04; // Apply 4% tariff for Textile from Bangladesh
                            break;
                        case "Brazil":
                            if (category.equals("Agriculture"))
                                InitialPrice *= 1.09; // Apply 9% tariff for Agriculture from Brazil
                            break;
                    }

                    // Add the product to the ArrayList with the updated price
                    Products.add(new Product(ProductName, country, category, Math.round(InitialPrice * 100) / 100));

                    // Close the line scanner after processing the line
                    linescan.close();
                } catch (Exception e) {
                    // Handle any exceptions that occur while parsing the line
                    System.out.println(e);
                }
            }
            // Close the main scanner after processing all lines
            sc.close();
        } catch (Exception e) {
            // Handle any exceptions that occur while reading the file
            System.out.println(e.getMessage());
        }

        // Sort the products in the ArrayList (assumes Product implements Comparable)
        Products.sort(null);

        // Write the sorted products to the output file and print them to the console
        for (int i = 0; i < Products.size(); i++) {
            bw.write(Products.get(i).toString() + "\n"); // Write to the file
            System.out.println(Products.get(i).toString()); // Print to the console
        }

        // Close the BufferedWriter and BufferedReader
        bw.close();
        br.close();
    }
}

