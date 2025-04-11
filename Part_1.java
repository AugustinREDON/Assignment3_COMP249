import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Part_1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("TradeData.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("UpdatedTradeData.txt"));
        ArrayList<Product> Products =  new ArrayList<Product>();
        TariffList t1 = new TariffList();
        TariffList t2 = new TariffList();

        try{
            Scanner sc = new Scanner(br);
            String filepath = "TradeData.txt";
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                try{
                    Scanner linescan = new Scanner(line);
                    linescan.useDelimiter(",");


                    //Have to add this to an arraylist
                    String ProductName = linescan.next();
                    String country = linescan.next();
                    String category = linescan.next();
                    double InitialPrice = linescan.nextDouble();
                    switch (country) {
                        case "China":
                            InitialPrice *= 1.25;
                            break;
                        case "USA":
                            if (category.equals("Electronics"))
                                InitialPrice *= 1.1;
                            break;
                        case "Japan":
                            if (category.equals("Automobiles"))
                                InitialPrice *= 1.15;
                            break;
                        case "India":
                            if (category.equals("Agriculture"))
                                InitialPrice *= 1.05;
                            break;
                        case "South Korea":
                            if (category.equals("Electronics"))
                                InitialPrice *= 1.08;
                            break;
                        case "Saudi Arabia":
                            if (category.equals("Energy"))
                                InitialPrice *= 1.12;
                            break;
                        case "Germany":
                            if (category.equals("Manufacturing"))
                                InitialPrice *= 1.06;
                            break;
                        case "Bangladesh":
                            if (category.equals("Textile"))
                                InitialPrice *= 1.04;
                            break;
                        case "Brazil":
                            if (category.equals("Agriculture"))
                                InitialPrice *= 1.09;
                            break;
                    }
                    Products.add(new Product(ProductName, country, category, Math.round(InitialPrice * 100)/100));



                    //We want to check which country
                    //Check which category it is
                    //Add the right tariff and add it to the updated file
                }
                catch(Exception e){
                    System.out.println(e);
                }

            }
        }
        catch(Exception e){

        }
        Products.sort(null);
        for(int i = 0; i < Products.size(); i++){
            bw.write(Products.get(i).toString() + "\n");
            System.out.println(Products.get(i).toString());
        }
        bw.close();
        br.close();


        //Part 2




    }
}

