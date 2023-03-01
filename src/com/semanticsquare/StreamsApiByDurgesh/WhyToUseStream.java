package com.semanticsquare.StreamsApiByDurgesh;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WhyToUseStream {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();

        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
        list1.add(6);
        System.out.println(list1);
        //making the new list of even numbers from list1 without using STREAM
        List<Integer> EvenList=new ArrayList<>();
        for(Integer i:list1){
            if(i%2==0)
            {
                EvenList.add(i);
            }
        }
        System.out.println(EvenList);

        //making the new list of even numbers from list1 using STREAM
        //method1
        Stream<Integer> stream=list1.stream();
        List<Integer> lists=stream.filter(i->i%2==0).collect(Collectors.toList());
        System.out.println(lists);

        //method2
        List<Integer> lists1=list1.stream().filter(i->i%2==0).collect(Collectors.toList());
        System.out.println(lists1);
    }
}
