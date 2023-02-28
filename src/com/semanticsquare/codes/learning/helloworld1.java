package com.semanticsquare.codes.learning;

import java.util.*;

public class helloworld1 {
    public static void main(String[] args) {
        System.out.println("HelloWorld");
        ArrayList<Double> arr=new ArrayList();
        arr.add(8.0);arr.add(10.0);arr.add(7.0);arr.add(1.0);
        Iterator itr=arr.iterator(); 
  while(itr.hasNext()){
   System.out.println(itr.next()); 
  }
       
        

    }
}


