
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class TradeManager {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        TariffList t1 = new TariffList();
        TariffList t2 = new TariffList();
        ArrayList<TariffList.TariffNode> TariffRequests = new ArrayList<TariffList.TariffNode>();
        BufferedReader br = new BufferedReader(new FileReader("Tariffs.txt"));

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

        }
        catch(Exception e){
            System.out.println("You screwed up 2");
        }
    }
}