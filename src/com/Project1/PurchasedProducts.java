package com.Project1;
import java.util.HashMap;

/** This class is created to keep customer's purchased products
 *  @author Kamil Ismayil
 *  @version 1.0
 *  @since 2019-09-13
 */

public class PurchasedProducts {
    String productName;
    int productPrice, priceTotal, amount;
    HashMap<String,Integer> purchasedProducts;

    public PurchasedProducts(String productName, int productPrice, int amount) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.purchasedProducts = new HashMap<>();
        this.amount = amount;
        this.priceTotal = amount * productPrice;
    }

    /**
     * This method returns the total price of the purchased products made by the member at a time
     * @return priceTotal
     */
    public int getPriceTotal() {
        return priceTotal;
    }

    /**
     * This method returns the product amount the member bought
     * @return amount
     */
    public int getAmount() {
        return amount;
    }
}
