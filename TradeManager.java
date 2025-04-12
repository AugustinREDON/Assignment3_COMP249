
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class TradeManager {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        TariffList t1 = new TariffList();
        TariffList t2 = new TariffList();
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
                    if(!t1.contains(originCountry,destincationCountry,category))
                        t1.addToStart(newT);
                    else
                        System.out.println("Tariff already exists");
                }
                catch (Exception e) {
                    System.out.println("You screwed up 1");
                }
            }

            while (sc2.hasNextLine()) {
                String line2 = sc2.nextLine();
                Scanner linescan = new Scanner(line2);

                String RequestId = linescan.next();
                String OriginCountry = linescan.next();
                String DestinationCountry = linescan.next();
                String ProductCategory = linescan.next();
                int TradeValue = linescan.nextInt();
                double ProposedTariff = linescan.nextDouble();
                Tariff newTariff = new Tariff(DestinationCountry,OriginCountry,ProductCategory,ProposedTariff,TradeValue);
                TariffRequests.add(newTariff);
//                System.out.println(RequestId);
                //Surcharge = Trade value * ((Minimum Tariff - ProposedTariff) / 100


            }
            for(int i = 0 ; i < TariffRequests.size() ; i++)
            {
               int num = i;
                if(t1.contains(TariffRequests.get(i).getOriginCountry(),TariffRequests.get(i).getDestinationCountry(),TariffRequests.get(i).getProductCategory()))
                {
                   double minimumTariff =  t1.find(TariffRequests.get(i).getOriginCountry(),TariffRequests.get(i).getDestinationCountry(),TariffRequests.get(i).getProductCategory()).getTariff().getMinimumTariff();
                   double proposedTariff = TariffRequests.get(num).getProposedTariff();
                   String outcome = t1.evaluateTrade(proposedTariff,minimumTariff);
                   if(outcome.equals("Accepted"))
                    System.out.println(outcome);
                   else if(outcome.equals("Rejected"))
                       System.out.println(outcome);
                   else {
                       System.out.println(outcome);
                    System.out.println(TariffRequests.get(num).getValue() * (minimumTariff - proposedTariff) / 100);
                   }
                    // we want to then take the minimum tariff of that and the proposed tariff of the araylist and call the impemented method
                }
                else {
                    System.out.println("This tariff does not exist ");
                }
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }

        
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

        System.out.println("Exited search while loop");










        scanner.close();
    }
}