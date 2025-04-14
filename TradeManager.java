import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import java.io.*;
public class TradeManager {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Initialize two TariffLists to store tariff data
        TariffList tList1 = new TariffList();
        TariffList tList2 = new TariffList();

        // ArrayList to store trade requests
        ArrayList<Tariff> TariffRequests = new ArrayList<Tariff>();

        // BufferedReaders to read data from files
        BufferedReader br = new BufferedReader(new FileReader("Tariffs.txt"));
        BufferedReader br2 = new BufferedReader(new FileReader("TradeRequests.txt"));

        try {
            // Scanner to read Tariffs.txt
            Scanner sc = new Scanner(br);
            Scanner sc2 = new Scanner(br2);

            // Read each line from Tariffs.txt
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                try {
                    // Scanner to parse each line
                    Scanner linescan = new Scanner(line);

                    // Read tariff details from the line
                    String destincationCountry = linescan.next();
                    String originCountry = linescan.next();
                    String category = linescan.next();
                    double minimumTariff = linescan.nextDouble();

                    // Create a new Tariff object
                    Tariff newT = new Tariff(destincationCountry, originCountry, category, minimumTariff);

                    // Check if the tariff already exists in tList1
                    if (!tList1.contains(originCountry, destincationCountry, category)) {
                        tList1.addToStart(newT); // Add the tariff to the list
                        linescan.close(); // Close the line scanner
                    } else {
                        System.out.println("Tariff already exists");
                        linescan.close(); // Close the line scanner
                    }
                } catch (Exception e) {
                    System.out.println("You screwed up 1"); // Error handling for parsing issues
                }
            }
            sc.close(); // Close the scanner for Tariffs.txt

            // Read each line from TradeRequests.txt
            while (sc2.hasNextLine()) {
                String line2 = sc2.nextLine();
                Scanner linescan = new Scanner(line2);

                // Read trade request details from the line
                String RequestId = linescan.next();
                String OriginCountry = linescan.next();
                String DestinationCountry = linescan.next();
                String ProductCategory = linescan.next();
                int TradeValue = linescan.nextInt();
                double ProposedTariff = linescan.nextDouble();

                // Create a new Tariff object for the trade request
                Tariff newTariff = new Tariff(RequestId, DestinationCountry, OriginCountry, ProductCategory, ProposedTariff, TradeValue);
                TariffRequests.add(newTariff); // Add the trade request to the list

                linescan.close(); // Close the line scanner
            }
            sc2.close(); // Close the scanner for TradeRequests.txt

            System.out.println("\n---- Evaluating Trade Requests ----\n");

            // Evaluate each trade request
            for (int i = 0; i < TariffRequests.size(); i++) {
                Tariff req = TariffRequests.get(i);

                // Check if the trade request matches a tariff rule in tList1
                if (tList1.contains(req.getOriginCountry(), req.getDestinationCountry(), req.getProductCategory())) {
                    // Retrieve the minimum tariff for the matching rule
                    double minimumTariff = tList1.find(req.getOriginCountry(), req.getDestinationCountry(), req.getProductCategory()).getTariff().getMinimumTariff();
                    double proposedTariff = req.getProposedTariff();

                    // Evaluate the trade request
                    String outcome = tList1.evaluateTrade(proposedTariff, minimumTariff);

                    System.out.print(req.getReqID() + " - ");

                    // Print the outcome of the evaluation
                    if (outcome.equals("Accepted")) {
                        System.out.println("Accepted.");
                        System.out.printf("Proposed tariff %.2f%% meets or exceeds the minimum required tariff %.2f%%.\n\n", proposedTariff, minimumTariff);
                    } else if (outcome.equals("Rejected")) {
                        System.out.println("Rejected.");
                        System.out.printf("Proposed tariff %.2f%% is more than 20%% below the required minimum tariff %.2f%%.\n\n", proposedTariff, minimumTariff);
                    } else {
                        System.out.println("Conditionally Accepted.");
                        System.out.printf("Proposed tariff %.2f%% is within 20%% of the required minimum tariff %.2f%%.\n", proposedTariff, minimumTariff);
                        double surcharge = req.getValue() * ((minimumTariff - proposedTariff) / 100.0);
                        System.out.printf("A surcharge of $%.2f is applied.\n\n", surcharge);
                    }
                } else {
                    // No matching tariff rule found
                    System.out.println(req.getReqID() + " - No matching tariff rule found.\n");
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage()); // General error handling
        }

        // Interactive tariff lookup
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n ----- Tariff Lookup ----- \n");

        while (true) {
            // Prompt user for input
            System.out.println("Enter country of origin (or type 'exit' to quit):");
            String originInput = scanner.next();
            originInput = originInput.trim().toLowerCase();
            if (originInput.equalsIgnoreCase("exit")) break;

            System.out.println("Enter destination");
            String destinationInput = scanner.next();
            destinationInput = destinationInput.trim().toLowerCase();
            System.out.println("Enter category");
            String categoryInput = scanner.next();
            categoryInput = categoryInput.trim().toLowerCase();

            // Search for a matching tariff
            TariffList.TariffNode result = tList1.find(originInput, destinationInput, categoryInput);
            if (result != null) {
                System.out.println("Tariff found: " + result.getTariff());
            } else {
                System.out.println("No tariff found for that combination");
            }
        }

        // Manual testing of all classes and methods
        System.out.println("\n ---- Manual Testing of all classes & methods ---- \n");

        // Create and test Tariff objects
        Tariff t1 = new Tariff("France", "UK", "Wine", 10.0);
        System.out.println("Tariff 1: " + t1);

        // Copy constructor
        Tariff t1Copy = new Tariff(t1);
        System.out.println("\nTariff 1 Copy: " + t1Copy);

        // Clone method
        Tariff t3 = t1.clone();
        System.out.println("\nCloned Tariff of Tariff 1: " + t3);

        // Equals method
        System.out.println("t1 = t1Copy? \n" + t1.equals(t1Copy));

        // Create and test TariffList
        TariffList list = new TariffList();
        list.addToStart(t1);
        list.addToStart(new Tariff("Canada", "Italy", "Syrup", 2));
        list.addToStart(new Tariff("India", "China", "Textile", 30));
        System.out.println("\nList after adding to start: ");
        list.displayList();

        // Insert at index
        list.insertAtIndex(new Tariff("Brazil", "Canada", "Beans", 5), 1);
        System.out.println("\nList after adding at index: ");
        list.displayList();

        // Delete from index
        list.deleteAtIndex(2);
        System.out.println("\nList after deleting at index: ");
        list.displayList();

        // Delete from start
        list.deleteFromStart();
        System.out.println("\nList after deleting from start");
        list.displayList();

        // Replace at index
        list.replaceAtIndex(new Tariff("Djibouti", "USA", "Microchips", 40), 2);
        System.out.println("\nList after replacing at index");
        list.displayList();

        // Contains method
        System.out.println("\nList contains method");
        System.out.println(list.contains("Canada", "Brazil", "Beans"));

        // Equals method for lists
        TariffList listCopy = new TariffList(list);
        System.out.println("\nList equals method: \n" + list.equals(listCopy));

        // Clone method for lists
        tList2 = list.clone();
        System.out.println("\nCreating tList2, clone of list");
        System.out.println("\ntList2 (clone)");
        tList2.displayList();
        System.out.println("\nlist (original)");
        list.displayList();

        // Compare cloned list with original
        System.out.println("\nComparing tList2 (clone) to list (original)");
        System.out.println(list.equals(tList2));

        scanner.close(); // Close the scanner
    }
}