package productList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class Order {

//this class is responsible for calculating the prices per item(unit cost, vat, total cost)
  private Item item;
  private int amount;

  public Order(Item item, int amount) {
    this.item = item;
    this.amount = amount;
  }

  public static Item findItem(int itemID, List<Item> itemList) {
    for (Item item : itemList) {
      if (item.getID() == itemID) {
        return item;
      }
    }
    System.out.println("Item not found: ITEM findItem()");
    return null;
  }

  public double totalPriceExcVat() {
    return round(this.item.getPrice() * amount,2 );
  }

  public double totalPrice() {
    return round(this.item.getTotalPrice() * amount, 2);
  }

  public double totalVat() {
    return round(this.item.getVat() * amount, 2);
  }

  private double round(double value, int place) {
    if (place < 0) throw new IllegalArgumentException();
    BigDecimal bd = BigDecimal.valueOf(value);
    bd = bd.setScale(place, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }

  @Override
  public String toString() {
    return "ID: "  + this.item.getID() + "\n" + "Description:," + this.item.getDesc() + "\n"
        + "Quantity: " + this.amount + "\n" + "Item price(exc vat):,R" + this.item.getPrice()
        + "\n" + "Total Price(exc vat):,R" + this.totalPriceExcVat() + "\n" + "Total vat:,R" + this.totalVat()
        + "\n" + "Total Price:,R" + this.totalPrice() + "\n";
  }
}
