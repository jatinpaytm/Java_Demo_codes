package com.semanticsquare.io;
// Note : To serialize go to prog arguments and type true : it will serialize
// Note : if wanted to do deserialize do nothing just run it without true in prog arguments
// refer this to add serialVersionUID : https://mkyong.com/intellij/how-to-generate-serialversionuid-in-intellij-idea/
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class IODemo {
	static String inFileStr = "walden.jpeg";
	static String outFileStr = "walden-out.jpeg";

	// By convention, static nested classes should be placed before static methods
	public static class SerializableDemo implements Serializable {
		@Serial
		private static final long serialVersionUID = 8781997612202241627L;  // if we comment in the implements Serializable then it will give error
		/* Changes that do not affect Deserialization:
		* 1. add/delete instance variables
		* 2. make instance variable static
		* 3. change instance variable from transient to non-transient
		* 4. changes access levels of variables
		* 5. add/ remove classes to/from inheritance tree
		* */

		/* Changes That affect Deserialization:
		* 1. change instance variables types.
		* 2. make serializable classes (anywhere in object graph) non-serializable.
		* */
		private String name;
		public String getName() { return name; }
		public void setName(String name) { this.name = name; }

		private transient int id = 4;  // transient means it will not serialize
		public int getId() { return id; }

		// Note: if we uncomment this after applying serialization
		// then if we deserialize then it will say the object mismatch .. because the class has evolved with time
		// to take care of this : we use serialVersionUID , which will be given a constant value , so apply it
		// now if we uncomment it then while deserialzation it will  not give error and the old version will execute
		private String gender;
	}


	public static void fileCopyNoBuffer() {
		System.out.println("\nInside fileCopyNoBuffer ...");

		long startTime, elapsedTime; // for speed benchmarking

		// Print file length
		File fileIn = new File(inFileStr);
		System.out.println("File size is " + fileIn.length() + " bytes");

		try (FileInputStream in = new FileInputStream(inFileStr);
			 FileOutputStream out = new FileOutputStream(outFileStr)) {
			startTime = System.nanoTime();
			int byteRead;
			// Read a raw byte, returns an int of 0 to 255.
			while ((byteRead = in.read()) != -1) {
				// Write the least-significant byte of int, drop the upper 3
				// bytes
				out.write(byteRead);
			}
			elapsedTime = System.nanoTime() - startTime;
			System.out.println("Elapsed Time is " + (elapsedTime / 1000000.0) + " msec");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Most common way to read byte streams from a file
	public static void fileCopyWithBufferAndArray() {
		System.out.println("\nInside fileCopyWithBufferAndArray ...");

		long startTime, elapsedTime; // for speed benchmarking
		startTime = System.nanoTime();
		try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFileStr));
			 BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFileStr))) {

			byte[] byteBuf = new byte[4000];
			int numBytesRead;
			while ((numBytesRead = in.read(byteBuf)) != -1) {   // understand the flow of reading and writing
				out.write(byteBuf, 0, numBytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		elapsedTime = System.nanoTime() - startTime;
		System.out.println("fileCopyWithBufferAndArray: " + (elapsedTime / 1000000.0) + " msec");
	}

	private static void readFromStandardInput() {
		System.out.println("\nInside readFromStandardInput ...");
		String data;
		/*
		System.out.print("Enter \"start\" to continue (Using BufferedReader): ");

		try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in, "UTF-8"))){
			while ((data = in.readLine()) != null && !data.equals("start")) {
				System.out.print("\nDid not enter \"start\". Try again: ");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Correct!!");
 		*/

		System.out.print("\nEnter \"start\" to continue (Using java.util.Scanner): ");
		Scanner scanner = new Scanner(System.in);

		while(!(data = scanner.nextLine()).equals("start")) {
			System.out.print("\nDid not enter \"start\". Try again: ");
		}
		System.out.println("Correct!!");


		System.out.println("Now, enter the start code: ");
		int code = scanner.nextInt(); // other methods: nextLong, nextDouble, etc
		System.out.println("Thanks. You entered code: " + code);


		/**
		 * Scanner ~ a text scanner for parsing primitives & string
		 *         ~ breaks its input into tokens using a delimited pattern (default: whitespace)
		 *         ~ when System.in is used, internally constructor uses
		 *            an InputStreamReader to read from it
		 *         ~ hasXXX & nextXXX can be used together
		 *         ~ InputMismatchException is thrown
		 *         ~ From Java 5 onwards
		 */

		Scanner s1 = new Scanner("Hello, How are you?");
		while(s1.hasNext()) {
			System.out.println(s1.next());
		}
	}

	public static void fileMethodsDemo() {
		System.out.println("\nInside fileMethodsDemo ...");

		File f = new File("/Users/jatinsingh/IdeaProjects/demo/walden.jpg"); // "movies\\movies.txt" also works
		//File f = new File("walden.jpg");

		System.out.println("getAbsolutePath(): " + f.getAbsolutePath());
		try {
			System.out.println("getCanonicalPath(): " + f.getCanonicalPath());
			System.out.println("createNewFile(): " + f.createNewFile());
		} catch (IOException e) {}
		System.out.println("separator: " + f.separator);
		System.out.println("separatorChar: " + f.separatorChar);
		System.out.println("getParent(): " + f.getParent());
		System.out.println("lastModified(): " + f.lastModified());
		System.out.println("exists(): " + f.exists());
		System.out.println("isFile(): " + f.isFile());
		System.out.println("isDirectory(): " + f.isDirectory());
		System.out.println("length(): " + f.length());

		System.out.println("My working or user directory: " + System.getProperty("user.dir"));
		System.out.println("new File(\"/Users/jatinsingh/IdeaProjects/demo/testdir\").mkdir(): " + new File("/Users/jatinsingh/IdeaProjects/demo/testdir").mkdir());
		System.out.println("new File(\"/Users/jatinsingh/IdeaProjects/demo/testdir/test\").mkdir(): " + new File("/Users/jatinsingh/IdeaProjects/demo/testdir/test").mkdir());
		System.out.println("new File(\"/Users/jatinsingh/IdeaProjects/demo/testdir\").delete(): " + new File("/Users/jatinsingh/IdeaProjects/demo/testdir").delete());
		System.out.println("new File(\"/Users/jatinsingh/IdeaProjects/demo/testdir/test1/test2\").mkdir(): " + new File("/Users/jatinsingh/IdeaProjects/demo/testdir/test1/test2").mkdir());
		System.out.println("new File(\"/Users/jatinsingh/IdeaProjects/demo/testdir/test1/test2\").mkdirs(): " + new File("/Users/jatinsingh/IdeaProjects/demo/testdir/test1/test2").mkdirs());

		try {
			File f2 = new File("/Users/jatinsingh/IdeaProjects/demo/testdir/temp.txt");
			System.out.println("f2.createNewFile(): " + f2.createNewFile());
			// shifted the file and also changed the name
			System.out.println("f2.renameTo(...): " + f2.renameTo(new File("testdir/test/temp1.txt"))); // move!!
		} catch (IOException e) {}

	}

	public static void dirFilter(boolean applyFilter) {
		System.out.println("\nInside dirFilter ...");
		// this tells the current director
		// current directory here is demo
		File path = new File(".");
		String[] list;

		if(!applyFilter)
			list = path.list();
		else
			list = path.list(new DirFilter());

		//Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		for(String dirItem : list)
			System.out.println(dirItem);
	}

	public static void applyEncoding() {
		System.out.println("\nInside applyEncoding ...");
		//System.out.println("Default Character Encoding: " + System.getProperty("file.encoding"));

		// Ensure Eclipse property is set as UTF8
		printEncodingDetails("luke");
		printEncodingDetails("?"); // Euro (Reference: http://stackoverflow.com/questions/34922333/how-does-java-fit-a-3-byte-unicode-character-into-a-char-type)
		printEncodingDetails("\u1F602"); // Non-BMP Unicode Code Point ~ Tears of Joy Emoji (one of Smiley graphic symbol)
	}
	private static void printEncodingDetails(String symbol) {
		System.out.println("\nSymbol: " + symbol);
		try {
			System.out.println("ASCII: " + Arrays.toString(symbol.getBytes("US-ASCII")));
			System.out.println("ISO-8859-1: " + Arrays.toString(symbol.getBytes("ISO-8859-1")));
			System.out.println("UTF-8: " + Arrays.toString(symbol.getBytes("UTF-8")));
			System.out.println("UTF-16: " + Arrays.toString(symbol.getBytes("UTF-16")));
			System.out.println("UTF-16 BE: " + Arrays.toString(symbol.getBytes("UTF-16BE")));
			System.out.println("UTF-16 LE: " + Arrays.toString(symbol.getBytes("UTF-16LE")));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}

	public void doSerialization() {
		System.out.println("\nInside doSerialization ...");

		SerializableDemo serializableDemo = new SerializableDemo();
		serializableDemo.setName("Java");
		System.out.println("name (before serialization): " + serializableDemo.getName());
		System.out.println("id (before serialization): " + serializableDemo.getId());

		try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("serial.ser")))) {
			out.writeObject(serializableDemo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDeserialization() {
		System.out.println("\nInside doDeserialization ...");

		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("serial.ser")))) {
			SerializableDemo serializedObj = (SerializableDemo) in.readObject();
			System.out.println("name (after deserialization): " + serializedObj.getName());
			System.out.println("id (after deserialization): " + serializedObj.getId());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void encodingSync() {
		/*
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("encoding.txt"), "UTF-16BE"))){
			System.out.println(br.readLine());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		try {
			//System.out.println(new String("?".getBytes("UTF-8"), "UTF-16BE"));
			// Utf-8 is backward compatible with US-ASCII
			// Utf-16BE is not backward compatible with US-ASCII
			System.out.println(new String("a".getBytes("US-ASCII"), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		//applyEncoding();
		//fileCopyNoBuffer();
		//fileCopyWithBufferAndArray();   // it has high speed
		//readFromStandardInput();
		// System.out.println(System.getProperty("file.encoding"));

		// fileMethodsDemo();
		// dirFilter(true); // if true filters it ,, if not just print all file names


		 // Uncomment all at once
		// Serialization
		if (args.length > 0 && args[0].equals("true")) {   // that program argument is passed here
			new IODemo().doSerialization();
		}
		new IODemo().doDeserialization();


		//encodingSync();
	}
}

// used to filter files of current directory
class DirFilter implements FilenameFilter {
	// Holds filtering criteria
	public boolean accept(File file, String name) {
		return name.endsWith(".jpg") || name.endsWith(".JPG");
	}
}