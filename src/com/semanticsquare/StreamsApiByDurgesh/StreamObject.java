package com.semanticsquare.StreamsApiByDurgesh;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class StreamObject {
    public static void main(String[] args) {

        //creating empty stream
        Stream<Object> emptyStream=Stream.empty();

        //creating stream containing the array of string
        String names[] = {"Durgesh","Uttam","Ankit","Divya"};
        Stream<String> stream1=Stream.of(names);
        stream1.forEach(e->{
            System.out.println(e);
        });

        //creating stream using list,sets
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
        list1.add(6);
        // list1.stream().forEach(e->{System.out.println(e);});  // we can do in this way also // short code
        Stream<Integer> stream = list1.stream();
        stream.forEach(e->{
            System.out.println(e);
        });

    }
}
