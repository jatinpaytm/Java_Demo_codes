package com.semanticsquare.codes;

public class code1 
{
    // making this  as static
    // if we dont make this static we need to first
    // make an object
    static void print()
    {
        System.out.println("Helo");
    }
    /*static*/ int a=10;
    public static void main(String[] args) {
        print();
        int a = 10;  // we can do ths
        int b=1;
        while(a!=0)
        {
            //int b=0;  // we cannot declare this again in JAVA , but like in c++ we can do
            b++;
            a--;
        }
        System.out.println(a + " " + b);
        for(int i=0;i<10;i++)
        {


        }
    }
}
