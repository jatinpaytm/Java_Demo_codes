// Master Java Exceptions with Best Practices
package com.semanticsquare.exceptions;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// in this we are throwing exceptions , to see in what order the things work
// firs the try block code works , then the try (all these resources are closed from right to left)
public class TryWithResourcesDemo {
	static String inFileStr = "walden.jpeg";
	static String outFileStr = "walden-out.jpeg";
		
	public static void fileCopyWithArm() throws IOException {
		System.out.println("\nInside fileCopyWithArm ...");				
		// here t , t2 , in --> all are final variables , once declared cannot changed again within try block
		// once try block is completed the resources will be closed from right to left in try block

		try (Test t = new Test(); Test2 t2 = new Test2();BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFileStr));
				BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFileStr))) {
			byte[] byteBuf = new byte[4000];
			int numBytesRead;
			while ((numBytesRead = in.read(byteBuf)) != -1) {
				out.write(byteBuf, 0, numBytesRead);
			}		
			
			throw new IOException("Important Exception!!");
		}		
	}	
	
	public static void main(String[] args) {
		try {
			fileCopyWithArm();
		} catch (IOException e) {
			//e.printStackTrace();

			// it helps to print all exceptions of IO
			Throwable[] throwables = e.getSuppressed();
			System.out.println(throwables[0].getMessage());
			System.out.println(throwables[1].getMessage());

		}
		
	}
}

class Test implements AutoCloseable {
	@Override
	public void close() throws IOException {
		throw new IOException("Trivial Exception");			
	}		
}

class Test2 implements AutoCloseable {
	@Override
	public void close() throws IOException {
		throw new IOException("Trivial Exception 2");			
	}		
}