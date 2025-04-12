
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
            String filepath = "TradeData.txt";
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                try {
                    Scanner linescan = new Scanner(line);
                    String destincationCountry = linescan.next();
                    String originCountry = linescan.next();
                    String category = linescan.next();
                    double minimumTariff = linescan.nextDouble();
                    System.out.println("Origin Country: " + originCountry + " Category: " + category  + " minimumTariff: " + minimumTariff + " Tariff");

                    t2.addToStart(new Tariff(destincationCountry,originCountry,category,minimumTariff));
                    //still need to check if the tariff already exists
                    if(t2.contains(destincationCountry,originCountry,category))
                    {

                    }

                }
                catch (Exception e) {
                    System.out.println("You screwed up 1");
                }
            }
            Scanner sc2 = new Scanner(br2);
            while (sc2.hasNextLine()) {
                String RequestId = sc2.nextLine();
                String OriginCountry = sc2.nextLine();
                String DestinationCountry = sc2.nextLine();
                String ProductCategory = sc2.nextLine();
                int TradeValue = sc2.nextInt();
                double ProposedTariff = sc2.nextDouble();

                try{

                }
                catch (Exception e) {
                    System.out.println("You screwed up 2");
                }

            }
        }
        catch(Exception e){
            System.out.println("You screwed up 3");
        }
    }
}