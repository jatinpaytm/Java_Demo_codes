package com.semanticsquare.exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionsDemo {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("\nInside main ...");
		try {
			share();
			System.out.println("After invoking share ...");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("main: filenotfoundexception ...");
		} finally {
			System.out.println("Inside main's finally ...");
		}
		System.out.println("\nEnd of main ...");
	}

	private static void share() throws FileNotFoundException {
		System.out.println("\nInside share ...");

		try {
			String response = HttpConnect.send(1, "hello", "http://www.goodsnips.com");
			System.out.println("After invoking send ...");
			APIParser.parseSendResponseCode(response, "hello", "http://www.goodsnips.com");
		} catch (FileNotFoundException e) {
			System.out.println("Share: filenotfoundexception ...");
			throw e;
		} catch (IOException e) {
			System.out.println("Connecting to a different server ...");
		} catch (APIFormatChangeException e) {
			// Item 65: Do not ignore exceptions
			// it will print both --> higher level and lower level exception
			// e.printStackTrace();

			// Item 63: Include failure-capture information in detail messages
			//System.out.println("e.toString(): " + e);
			//System.out.println("e.getMessage(): " + e.getMessage());

			// Item 63
			// will help us to give higher level exception : created by developer
			System.out.println("e.getElementName(): " + e.getElementName());

			// Item 61: Throw exceptions appropriate to the abstraction
			// will give us lower level exception
			// Remember : getCause() is a predeclared method for throwable
			// e.getCause().printStackTrace();
		}
		finally {
			System.out.println("Inside share's finally ...");
		}

		System.out.println("\nEnd of share ...");
	}

}
