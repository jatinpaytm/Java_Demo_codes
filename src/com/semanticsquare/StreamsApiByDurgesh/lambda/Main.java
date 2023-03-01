package com.semanticsquare.StreamsApiByDurgesh.lambda;

// NOTE : A functional interface only contains 1 abstract methods
// Comparable , Runnable , Callable are some predefined Functional Interfaces

public class Main {

    public static void main(String[] args) {

        //this method can only be used for functional interface and if there will be more than 2 methods than we have to use anonymous class
        MyInter i = () -> System.out.println("using lambda");
        i.sayHello();

        MyInter i2 = () -> System.out.println("using lambda");
        i2.sayHello();

        sumInter i3 = (a,b) -> a+b;
        System.out.println(i3.findSum(5,5));


    }
}
