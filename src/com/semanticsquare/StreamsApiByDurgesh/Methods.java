package com.semanticsquare.StreamsApiByDurgesh;

import java.util.List;
import java.util.stream.Collectors;

public class Methods {
    public static void main(String[] args) {

        //filter(predicate)
        //Predicate is a boolean value function
        //eg. e->e>10
        List<String> names = List.of("Aman", "Ankit", "Abhinav", "Durgesh");
        //names.stream().filter(e->e.startsWith("A")).forEach(System.out::println); // can print directly without storing it
        List<String> newList = names.stream().filter(e->e.startsWith("A")).collect(Collectors.toList());
        System.out.println(newList);  // it will print with the help of Tostring method of array


        //map(function)
        //using map we can perform operation on each element
        List<Integer> number = List.of(1,2,3,4,5,0);
        List<Integer> number1 = number.stream().map(i->i*i).collect(Collectors.toList());
        System.out.println(number1);


        //forEach is used to traverse each element
        names.stream().forEach(e->{
            System.out.println(e);
        });
        //or we can also do this
        names.stream().forEach(System.out::println);

        //sort
        List<Integer> number2 = number.stream().sorted().collect(Collectors.toList());
        System.out.println(number2);

        //min
        Integer integer = number.stream().min((x,y)->x.compareTo(y)).get();
        System.out.println(integer);

        //max
        Integer integer1 = number.stream().max((x,y)->x.compareTo(y)).get();
        System.out.println(integer1);

    }
}
