package com.service;
/**
 * @author Sanyam tyagi
 * 
 */
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveDup {
public static String[] remove(String []str){
	List<String> list = Arrays.asList(str);
    Set<String> set = new HashSet<String>(list);
    String[] result = new String[set.size()];
    set.toArray(result);
    return result;
}
}
