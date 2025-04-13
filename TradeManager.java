
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;


import java.io.*;
public class TradeManager {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        TariffList tList1 = new TariffList();
        TariffList tList2 = new TariffList();
        ArrayList<Tariff> TariffRequests = new ArrayList<Tariff>();
        BufferedReader br = new BufferedReader(new FileReader("Tariffs.txt"));
        BufferedReader br2 = new BufferedReader(new FileReader("TradeRequests.txt"));

        try {
            Scanner sc = new Scanner(br);
            Scanner sc2 = new Scanner(br2);
            //String filepath = "TradeData.txt";
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                try {
                    Scanner linescan = new Scanner(line);
                    String destincationCountry = linescan.next();
                    String originCountry = linescan.next();
                    String category = linescan.next();
                    double minimumTariff = linescan.nextDouble();
                    Tariff newT = new Tariff(destincationCountry,originCountry,category,minimumTariff);
                    if(!tList1.contains(originCountry,destincationCountry,category)){
                        tList1.addToStart(newT);
                        linescan.close();
                    } else {
                        System.out.println("Tariff already exists");
                        linescan.close();
                    }
                }
                catch (Exception e) {
                    System.out.println("You screwed up 1");
                } 
            }
            sc.close();

            while (sc2.hasNextLine()) {
                String line2 = sc2.nextLine();
                Scanner linescan = new Scanner(line2);

                String RequestId = linescan.next();
                String OriginCountry = linescan.next();
                String DestinationCountry = linescan.next();
                String ProductCategory = linescan.next();
                int TradeValue = linescan.nextInt();
                double ProposedTariff = linescan.nextDouble();
                Tariff newTariff = new Tariff(RequestId, DestinationCountry,OriginCountry,ProductCategory,ProposedTariff,TradeValue);
                TariffRequests.add(newTariff);
                //System.out.println(RequestId);
                //Surcharge = Trade value * ((Minimum Tariff - ProposedTariff) / 100
                linescan.close();
            }
            sc2.close();


            System.out.println("\n---- Evaluating Trade Requests ----\n");

            for (int i = 0; i < TariffRequests.size(); i++) {
                Tariff req = TariffRequests.get(i); 

                if (tList1.contains(req.getOriginCountry(), req.getDestinationCountry(), req.getProductCategory())) {

                    double minimumTariff = tList1.find(req.getOriginCountry(), req.getDestinationCountry(), req.getProductCategory()).getTariff().getMinimumTariff();
                    double proposedTariff = req.getProposedTariff();
                    String outcome = tList1.evaluateTrade(proposedTariff, minimumTariff);

                    System.out.print(req.getReqID() + " - ");

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
                    System.out.println(req.getReqID() + " - No matching tariff rule found.\n");
                }
            }


        } catch(Exception e){
            System.out.println(e.getMessage());
        } 

        /* 
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n ----- Tariff Lookup -----");


        while(true){
            System.out.println("Enter country of origin (or type 'exit' to quit):");
            String originInput = scanner.next();
            originInput = originInput.trim().toLowerCase();
            if(originInput.equalsIgnoreCase("exit")) break ;

            System.out.println("Enter destination");
            String destinationInput = scanner.next();
            destinationInput = destinationInput.trim().toLowerCase();
            System.out.println("Enter category");
            String categoryInput = scanner.next();
            categoryInput = categoryInput.trim().toLowerCase();

            TariffList.TariffNode result = t1.find(originInput, destinationInput, categoryInput);
            if(result != null){
                System.out.println("Tariff found: " + result.getTariff());
            } else {
                System.out.println(" No tariff not found for that combination");
            }
   
        }
        */
        


        System.out.println("\n Manual Testing of all classes & methods");

        Tariff t1 = new Tariff("France", "UK", "Wine", 10.0);
        System.out.println("Tarrif 1: " + t1);

        //Copy constructor
        Tariff t1Copy = new Tariff(t1);
        System.out.println("\n Tarrif 1 Copy: " + t1Copy);

        // Clone method
        Tariff t3 = t1.clone();
        System.out.println("\n Cloned Tariff of Tarrif 1: " + t3);

        //equals method
        System.out.println("t1 = t1Copy? " + t1.equals(t1Copy));

        //Creating tariffList
        TariffList list = new TariffList();
        list.addToStart(t1);
        list.addToStart(new Tariff("Canada", "Italy", "Syrup", 2));
        list.addToStart(new Tariff("India", "China", "Textile", 30));   
        System.out.println("\n List after adding to start: ");
        list.displayList();

        //Insert at index
        list.insertAtIndex(new Tariff("Brazil", "Canada","Beans", 5), 1);
        System.out.println("\n List after adding at index: ");
        list.displayList();

        //Delete from index
        list.deleteAtIndex(2);
        System.out.println("\n List after deleting at index: ");
        list.displayList();

        //Delete from start
        list.deleteFromStart();
        System.out.println("\n List after deleting from start");
        list.displayList();


        //Replace at index
        list.replaceAtIndex(new Tariff("Djibouti", "USA", "Microchips", 40), 2);
        System.out.println("\n List after replacing at index");
        list.displayList();

        //Contains
        System.out.println("\n List contains method");
        System.out.println(list.contains("Canada", "Brazil", "Beans"));

        //List equals
        TariffList listCopy = new TariffList(list);
        System.out.println("\n List equals method: " + list.equals(listCopy));



        //scanner.close();
    }
}