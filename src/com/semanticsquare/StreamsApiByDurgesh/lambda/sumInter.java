package com.semanticsquare.StreamsApiByDurgesh.lambda;
// NOTE : A functional interface only contains 1 abstract methods
// Comparable , Runnable , Callable are some predefined Functional Interfaces
@FunctionalInterface
public interface sumInter {

    public abstract int findSum(int a , int b);
}
