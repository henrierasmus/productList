
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;
import java.util.Map;
import java.util.HashMap;
import productList.Item;
import java.util.List;
import java.util.ArrayList;
import productList.Order;
import productList.OrderList;
import java.awt.Desktop;

public class Main {

  public static void main(String[] args) throws IOException {
//    Maps to keep track of the data before creating a new object/Item,
    Map<Integer, Integer> quantitiesList = new HashMap<>();
    Map<Integer, Double> pricesList = new HashMap<>();
    Map<Integer, String> itemsList = new HashMap<>();
    List<Item> items = new ArrayList<>();
    OrderList orderList = new OrderList();

    try {
//    Reading the provided files in the location
      Scanner quantity = new Scanner(Paths.get("quantities.csv"));
      Scanner price = new Scanner(Paths.get("prices.csv"));
      Scanner item = new Scanner(Paths.get("items.csv"));

//    Loop through the files to get every line and populate the maps
//    Count to keep track of what line is being read
      int count = 0;
      while (quantity.hasNextLine()) {
        String nextLine = quantity.nextLine();
        if (count != 0) {
            String[] quantitiesRow = nextLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
          //      Data to populate the maps
          int quantityID = Integer.parseInt(quantitiesRow[0]);
          int quantityAmount = Integer.parseInt(quantitiesRow[1]);
          quantitiesList.put(quantityID, quantityAmount);
        }
        count++;
      }

      count = 0;
//    Loop through the files to get every line and populate the maps
      while (price.hasNextLine()) {
        String nextLine = price.nextLine();
        if (count != 0) {
          String[] pricesRow = nextLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
          //      Data to populate the maps
          int priceID = Integer.parseInt(pricesRow[0]);
          double priceValue = Double.parseDouble(pricesRow[1]);
          pricesList.putIfAbsent(priceID, priceValue);
        }
        count++;
      }

      count = 0;
//    Loop through the files to get every line and populate the maps
      while (item.hasNextLine()) {
        String nextLine = item.nextLine();
        if (count != 0) {
          String[] itemsRow = nextLine.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
          //      Data to populate the maps
          int itemID = Integer.parseInt(itemsRow[0]);
          String itemDesc = itemsRow[1];
          itemsList.putIfAbsent(itemID, itemDesc);
        }
        count++;
      }

    } catch (Exception e) {
      System.out.println(e);
    }

//  Create a new Item and add to a list from the HashMaps.
//  This list will keep track of all the items and later match it to an order list
    for (int item : itemsList.keySet()) {
      items.add(new Item(itemsList.get(item), item, pricesList.get(item)));
    }

//  List of items that the client is interested in buying
//  Creates a new list that adds only the items that the client is interested in
    for (int quantity : quantitiesList.keySet()) {
      orderList.add(new Order(Order.findItem(quantity, items), quantitiesList.get(quantity)));
    }

//  Print to a file
    try {
      orderList.writeToFile();
    } catch (Exception e) {
      e.printStackTrace();
    }

    Desktop desktop = Desktop.getDesktop();
    desktop.open(new File("quote.csv"));
  }
}
