
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
//            String filepath = "TradeData.txt";
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
                System.out.println(RequestId);

                try{

                }
                catch (Exception e) {
                    System.out.println("You screwed up 2");
                }

            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}