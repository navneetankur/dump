package com.service;
/**
 * @author Sanyam tyagi
 * 
 */
import java.util.Comparator;

import com.glecom.products.models.Product;

public class Compare{
 
   // @Override
    public static Comparator<Product> priceComparator = new Comparator<Product>() {

    	public int compare(Product p1, Product p2) {
    	   String ProductName1 = p1.getPrice();
    	   String ProductName2 = p2.getPrice();

    	   //ascending order
    	   return ProductName1.compareTo(ProductName2);

    	   //descending order
    	   //return ProductName2.compareTo(ProductName1);
        }};
    }

