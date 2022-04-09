package com.service;
import java.util.Arrays;
import java.util.HashSet;
/**
 * @author Sanyam tyagi
 * 
 */

public class Remove {
public static StringBuffer remove(StringBuffer str){
	String[] s=String.valueOf(str).split(",");
	int i=0;
	StringBuffer st=new StringBuffer();
	s = new HashSet<String>(Arrays.asList(s)).toArray(new String[0]);
	for (i = 0; i < s.length; i++) {
		if(i==0){
			st.append(s[i]);
		}
		else{
			st.append(","+s[i]);
		}
		
	}
	return st;
	
}

public static StringBuffer removeDup(StringBuffer str,String c) {
	
	String[] s=String.valueOf(str).split(",");
	int i=0;
	StringBuffer st=new StringBuffer();
	for (i = 0; i < s.length; i++) {
		if(!s[i].equals(c)){
			if(i==0){
				st.append(s[i]);
			}
			else{
				st.append(","+s[i]);
			}
			
		}
		
	}
	return st;
}
}
