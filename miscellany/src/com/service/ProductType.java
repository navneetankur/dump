package com.service;
/**
 * @author Sanyam tyagi
 * 
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class ProductType {
List catagory = Arrays.asList("men,electronic & computers,women,home & kitchen,books & media");
boolean isCatagory(String string){
	for(int i=0;i< catagory.size();i++){
		if(string.equalsIgnoreCase((String) catagory.get(i)));
		{
			return true;
		}
	}
	return false;
	
}
}
