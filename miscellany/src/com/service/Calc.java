package com.service;

import java.util.ArrayList;

import com.glecom.products.models.Product;
/**
 * @author Sanyam tyagi
 * 
 */
public class Calc {
public static String sum(ArrayList<Product> list){
	int sum=0;
	for(Product product:list) {
		sum+=product.getDiscountedPrice();
	}
	return String.valueOf(sum);
}
public static String coutn(String str){
	 String[] s=str.split(",");
			 	int i=0;
			    int largest = Integer.parseInt(s[0]);
			    for(i = 0; i < s.length; i++){
			    if((Integer.parseInt(s[i]) > largest)){
			    largest =  Integer.parseInt(s[i]);
	    }
	}
			    int k=largest;
			    int n = s.length/s[0].length();
			    
			    for (i = 0; i< n; i++)
			        s[(Integer.parseInt(s[i]))%k] += k; 
			    int max = Integer.parseInt(s[0]), result = 0;
			    for ( i = 1; i < n; i++)
			    {
			        if (Integer.parseInt(s[i]) > max)
			        {
			            max = Integer.parseInt(s[i]);
			            result = i;
			        }
			    }
			  
return String.valueOf(result);
}
}
