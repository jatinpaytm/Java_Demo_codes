// // Recursive java program to
// // find sum of digits of a number
// import java.io.*;
// import java.util.Scanner;
// class sum_of_digits
// {
// 	// Function to check sum
// 	// of digit using recursion
// 	static int sum_of_digit(int n)
// 	{
// 		if (n == 0)
// 			return 0;
// 		return (n % 10 + sum_of_digit(n / 10));
// 	}

// 	// Driven Program to check above
// 	public static void main(String args[])
// 	{
// 		Scanner sc=new Scanner(System.in);
// 		int num=sc.nextInt();
// 		//int num = 12345;
// 		int result = sum_of_digit(num);
// 		System.out.println("Sum of digits in " +
// 						num + " is " + result);
// 	}
// }

// // This code is contributed by Anshika Goyal.
