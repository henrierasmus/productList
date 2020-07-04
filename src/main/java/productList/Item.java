package productList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Item {
  private String desc;
  private int ID;
  private double price;
  private double vat;
  private double totalPrice;

  public Item(String desc, int ID, double price) {
    this.desc = desc;
    this.ID = ID;
    this.price = price;
    this.vat = round(this.price * 0.15, 2);
    this.totalPrice = this.price + this.vat;
  }

  public double getPrice() {
    return this.price;
  }

  public int getID() {
    return this.ID;
  }

  public String getDesc() {
    return this.desc;
  }

  public double getVat() {
    return this.vat;
  }

  public double getTotalPrice() {
    return this.totalPrice;
  }

  public double totalPrice() {
    return this.totalPrice;
  }

  private double round(double value, int place) {
    if (place < 0) throw new IllegalArgumentException();
    BigDecimal bd = BigDecimal.valueOf(value);
    bd = bd.setScale(place, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }

  @Override
  public String toString() {
    return this.ID + ": " + this.desc + " price: R" + this.price;
  }
}
