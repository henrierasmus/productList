package productList;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class OrderList {

//  creating a list of items ordered and calculate the total cost,
//  as well as the functionality of printing to a new file
  private List<Order> orderList;

  public OrderList() {
    this.orderList = new ArrayList<>();
  }

  public void add(Order order) {
    orderList.add(order);
  }

//  prints to console
  public void print() {
    int count = 0;
    System.out.println("Order List \n");
    for (Order order : orderList) {
      System.out.println(order);
      count++;
    }
    System.out.println("Count: " + count);
  }

  public void writeToFile() throws Exception {
    PrintWriter writer = new PrintWriter("quote.csv");
    PrintWriter writerTxt = new PrintWriter("quote.txt");

    for (Order order : orderList) {
      writer.println(order);
    }

    for (Order order : orderList) {
      writerTxt.println(order);
    }

//  Adding the totals for the summary at the end of the file
    double totalExclVat = orderList.stream().map(Order::totalPriceExcVat)
        .reduce((double) 0, Double::sum);
    double totalVat = orderList.stream().map(Order::totalVat)
        .reduce((double) 0, Double::sum);
    double totalIncVat = orderList.stream().map(Order::totalPrice)
        .reduce((double) 0, Double::sum);

    writer.println("Summary");
    writer.println("Total no vat:,R " + round(totalExclVat, 2));
    writer.println("Total vat:,R " + round(totalVat, 2));
    writer.println("Total price including vat:,R " + round(totalIncVat, 2));

    writerTxt.println("Summary");
    writerTxt.println("Total no vat:,R " + round(totalExclVat, 2));
    writerTxt.println("Total vat:,R " + round(totalVat, 2));
    writerTxt.println("Total price including vat:,R " + round(totalIncVat, 2));

    writer.close();
    writerTxt.close();
  }

  private double round(double value, int place) {
    if (place < 0)
      throw new IllegalArgumentException();
    BigDecimal bd = BigDecimal.valueOf(value);
    bd = bd.setScale(place, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }
}

