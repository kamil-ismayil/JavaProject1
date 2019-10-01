package com.Project1;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is created to hold list of products to buy
 * @author Kamil Ismayil
 * @version 1.0
 * @since 2019-09-13
 */
public class Products {
    Map<String, Integer> products;

    public Products() {
        this.products = new HashMap<>();
    }

    /**
      * This method shows the list of products to buy
    **/
    public void productList(){
        products.put("Banana",10);
        products.put("Energy Drink",40);
        products.put("Protein Bar",60);
        products.put("Sandwich",65);
    }

    /**
     * This method returns the list of products
     */
    public HashMap<String, Integer> getProducts() {
        return new HashMap<String, Integer>(products);
    }
}
