package com.semanticsquare.functional.streams;

import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toList;


/**
 * Demonstrates:
 *
 * Demo : Slicing a stream
 *
 * Demo : Stream matching Functions
 *
 * Demo : Stream Finding operation and Optional Class
 *
 * Demo : Stream Reduction Operations
 *
 * Demo : Mutable Reduction with Collection Operations
 *
 * @author Jatin Singh
 * */

public class StreamOperations {

	static class Book {
		private long isbn;
		private String title;
		private double rating;
		private double price;
		private String source;

		public Book(long isbn, String title, double rating, double price, String source) {
			this.isbn = isbn;
			this.title = title;
			this.rating = rating;
			this.price = price;
			this.source = source;
		}

		public long getIsbn() {
			return isbn;
		}

		public String getTitle() {
			return title;
		}

		public double getRating() {
			return rating;
		}

		public double getPrice() {
			return price;
		}

		public String getSource() {
			return source;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (int) (isbn ^ (isbn >>> 32));
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Book other = (Book) obj;
			if (isbn != other.isbn)
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Book [isbn=" + isbn + ", title=" + title + ", rating=" + rating + ", price=" + price + ", source="
					+ source + "]";
		}


//		@Override
//		public int compareTo(Object o) {
//			return Long.valueOf(isbn).compareTo(((Book)o).getIsbn());
//		}

	}

	private static void collectToMap(List<Book> books) {
		System.out.println("\nIn collectToMap ...");
		Map<Long, Book> map = books.stream()
				//.collect(Collectors.toMap(b -> b.getIsbn(), b -> b));
				.collect(Collectors.toMap(b -> b.getIsbn(), b -> b, (b1, b2) -> b1.getPrice() <= b2.getPrice() ? b1 : b2));  // 3rd parameter is passed when there is a collision
		for (Map.Entry<Long, Book> entry : map.entrySet()) {
			System.out.println("isbn: " + entry.getKey() + ", book: " + entry.getValue());
		}

		System.out.println(map instanceof HashMap); // will return true

		// the code is not working i dont know
		Map<Long, Book> treeMap = books.stream()
				.collect(Collectors.toMap(Book::getIsbn, Function.identity(), (b1, b2) -> b1.getPrice() <= b2.getPrice() ? b1 : b2 /*, () -> new TreeMap()*/));  // we are adding 4th argument and supplying treemap
		for (Map.Entry<Long, Book> entry : treeMap.entrySet()) {
			System.out.println("isbn: " + entry.getKey() + ", book: " + entry.getValue());
		}


		/*
		Map<Double,List<Book>> ratingsMap = treeMap.values().stream()
				.collect(Collectors.toMap(Book::getRating,b->Collections.singletonList(b),(l1,l2) -> {ArrayList<Book> l = new ArrayList<>();
					l.addAll(l2);
					return l;}));

		for (Entry<Double,List<Book>> entry : ratingsMap.entrySet()) {
			System.out.println("\nRating: " + entry.getKey() + ", book: " + entry.getValue());
			for(Book b : entry.getValue())
			{
				System.out.println(b);
			}
		}*/

		System.out.println("\ngroupingBy with values as List ... ");
		Map<Double,List<Book>> ratingsMap1 = treeMap.values().stream()
				.collect(Collectors.groupingBy(Book::getRating)); // when we use groupingBy , we do not have to use any collision avoiding strategy as done in upper code

		for (Entry<Double,List<Book>> entry : ratingsMap1.entrySet()) {
			System.out.println("\nRating: " + entry.getKey() + ", book: " + entry.getValue());
			for (Book b : entry.getValue()) {
				System.out.println(b);
			}
		}


		System.out.println("\ngroupingBy with values as Set ... ");
		Map<Double, Set<Book>> ratingsMap2 = treeMap.values().stream()
				.collect(Collectors.groupingBy(Book::getRating, TreeMap::new, toSet()));
		for (Entry<Double, Set<Book>> entry : ratingsMap2.entrySet()) {
			System.out.println("\nRating: " + entry.getKey());
			for (Book b : entry.getValue()) {
				System.out.println(b);
			}
		}



		// just count the values for each rating
		System.out.println("just count the values for each rating");
		Map<Double, Long> ratingsCountMap = treeMap.values().stream()
				.collect(Collectors.groupingBy(Book::getRating, Collectors.counting()));
		System.out.println("\nratingsCountMap: " + ratingsCountMap);
		System.out.println("\ncount: " + treeMap.values().stream().collect(Collectors.counting()));

		System.out.println("just getting avg price of books for each rating");
		Map<Double, Double> ratingsAvgPriceMap = treeMap.values().stream()
				.collect(Collectors.groupingBy(Book::getRating, Collectors.averagingDouble(Book::getPrice)));
		System.out.println("\nratingsAvgPriceMap: " + ratingsAvgPriceMap);

		System.out.println("just getting min price of books for each rating");
		Map<Double, Optional<Book>> ratingsMinPriceMap = treeMap.values().stream()
				.collect(Collectors.groupingBy(Book::getRating, Collectors.minBy(Comparator.comparingDouble(Book::getPrice))));
		System.out.println("\nratingsMinPriceMap: " + ratingsMinPriceMap);

		System.out.println("just getting Summary statistics of books for each rating");
		Map<Double, DoubleSummaryStatistics> ratingsSummaryMap = treeMap.values().stream()
				.collect(Collectors.groupingBy(Book::getRating, Collectors.summarizingDouble(Book::getPrice)));
		System.out.println("\nDoubleSummaryStatistics: " + ratingsSummaryMap);

		System.out.println("I will partition the books under 4.5 and more than 4.5");
		Map<Boolean, List<Book>> partitionedMap = treeMap.values().stream()
				.collect(Collectors.partitioningBy(b -> b.getRating() >= 4.5));
		System.out.println("\npartitionedMap: " + partitionedMap);

		System.out.println("\ngroupingBy with values as List ... ");
		Map<Double, List<String>> ratingsTitleMap = treeMap.values().stream()
				.collect(Collectors.groupingBy(Book::getRating, Collectors.mapping(Book::getTitle, toList())));
		System.out.println("\nratingsTitleMap: " + ratingsTitleMap);

		System.out.println("\ngroupingBy map in map ... ");
		Map<Double, Map<String, List<Book>>> multiLevelMap = treeMap.values().stream()
				.collect(Collectors.groupingBy(Book::getRating, Collectors.groupingBy(Book::getSource, toList())));
		System.out.println("\nmultiLevelMap: " + multiLevelMap);


	}
	private static void collectToCollection(List<Book> books) {
		System.out.println("\nIn collectToCollection ...");
		List<Book> list1 = books.stream()
				.filter(b -> b.getRating() >= 4.5)
				.distinct()
				.collect(Collectors.toList());
		System.out.println("list1.size: " + list1.size());

//		List<Book> list1 = books.stream()
//				.filter(b -> b.getRating() >= 4.5)
//				.distinct()
//				.collect(ArrayList::new,
//						ArrayList::add,
//						ArrayList::addAll);
//		//.collect(Collectors.toList());
//		System.out.println("list1.size: " + list1.size());

		Set<Book> set1 = books.stream()
				.filter(b -> b.getRating() >= 4.5)
				.collect(toSet());
		System.out.println("set1.size: " + set1.size());

		// was giving error .. dont know what it is .. when see video .. correct it
//		TreeSet<Book> set2 = books.stream()
//				.filter(b -> b.getRating() >= 4.5)
//				.collect(toCollection(() -> new TreeSet()));
//		System.out.println("set2.size: " + set2.size());



	}

	// Demo : Mutable Reduction with Collection Operations
	// if accumulator mutates, use collect(). Otherwise, use reduce()
	// reduce () do immutable reduction , where -> collect() do mutable reduction
	// collect() : it need 3 things ->  supplier , accumulator and combiner
	private static void mutableReduction() {
		System.out.println("\nmutableReduction ... ");
		String[] grades = {"A", "A", "B"};

		StringBuilder concat2 = Arrays.stream(grades).parallel()
				.collect(() -> new StringBuilder(),
						(sb, s) -> sb.append(s),
						(sb1, sb2) -> sb1.append(sb2));
		System.out.println("concat2: " + concat2);

		String concatWithJoining = Arrays.stream(grades).parallel()
				.collect(Collectors.joining());   // Collectors.joining() --> collector { helper class}
		System.out.println("concatWithJoining: " + concatWithJoining);
	}

	static void overloadedReductions() {
		System.out.println("\noverloadedReductions ... ");

		String[] grades = {"A", "A", "B"};

		// Stream API : Design Principle
		// Api does not differentiate b/w sequential & parallel streams
		String concat1 = Arrays.stream(grades)//.parallel() //for parallel() it will give the same output everytime
				.reduce("", (s1, s2) -> s1 + s2);
		System.out.println("concat1: " + concat1);

		// single instance of container is used +
		//				  SB is not thread-safe +
		//                Combiner redundantly combines
		StringBuilder concat2 = Arrays.stream(grades)//.parallel() // for parallel() it will not give the same output everytime
				.reduce(new StringBuilder(), (sb, s) -> sb.append(s) /* Accumulator */,
						(sb1, sb2) -> sb1.append(sb2)/* Combiner */);
						//null);// combiner cannot be NULL
		System.out.println("concat2: " + concat2);

		// Not efficient: Each accumulation step creates a new StringBuilder
		StringBuilder concat3 = Arrays.stream(grades).parallel()
				.reduce(new StringBuilder(),
						(sb, s) -> new StringBuilder().append(sb).append(s),//sb-> string builder , s -> string
						(sb1, sb2) -> sb1.append(sb2));
		System.out.println("concat3: " + concat3);

	}

	//Demo : Stream Reduction Operations
	// Find the lowest priced book with a rating >= 4.5
	private static void reduce(List<Book> books) {   // reduce() --> reduce to single value
		System.out.println("\nReduce ...");
		books.stream()
				.filter(b -> b.getRating() >= 4.5)
				.reduce((b1, b2) -> b1.getPrice() <= b2.getPrice() ? b1 : b2)
				.ifPresent(b -> System.out.println("Lowest priced book: " + b));
	}

	// Limitations:
	//   1. Cumbersome
	//   2. Parallelizing is painful
	//   3. Synchronizing shared mutable variable is expensive
	private static void reduceImperatively(List<Book> books) {
		System.out.println("\nReducing imperatively ...");
		Book result = null;

		for (Book book : books) {
			// Initialize result with first book having rating >= 4.5
			if (result == null) {
				if (book.getRating() >= 4.5) {
					result = book;
				}
				continue;
			}

			if (book.getRating() >= 4.5 && result.getPrice() > book.getPrice()) {
				result = book;
			}
		}

		System.out.println("(Imperative) Lowest priced book with rating >= 4.5: " + result);

	}


	// findFirst needs more work in parallel env. Use findAny if it does the job.
	// java.util.Optional ~
	//     (a) to avoid dealing with null -- in case of find,
	//     (b) to know if stream is empty -- in case of reduction operation
	private static void find(List<Book> books) {	//Demo : Stream Finding operation and Optional Class
		System.out.println("\nFinding ...");
		// optional means value may be present or may be empty
		/*Optional<Book> result = */ books.stream()
				.filter(d -> d.getRating() >= 4.8 && d.getPrice() <= 50.0)
				.findAny().orElseGet(StreamOperations::getDefault);

		Optional<Book> opt = Optional.ofNullable(books.get(0));
		System.out.println(opt.isPresent());

		/*if (result.isPresent()) {
			System.out.println(result.get());
		} else {
			System.out.println("not found!!!");
		}*/

	}

	private static void print(Book b) {
		System.out.println(b);
	}

	private static Book getDefault() {
		System.out.println("default ...");
		return new Book(0, "", 4.0, 25.0, "Amazon");
	}


	// Queries:
	//  (a) Is there at least one highly rated book (>= 4.8) that is inexpensive (<= $50)?
	//  (b) Do all the books have a rating >= 4.8
	//  (c) Check if none of books have bad rating (2.0)?
	private static void match(List<Book> books) {	// Demo : Stream matching Functions
		System.out.println("\nMatching ... ");

		// part (a)
		boolean anyMatch = books.stream()
				.anyMatch(d -> d.getRating() >= 4.8 && d.getPrice() <= 50.0);
		System.out.println("anyMatch? " + anyMatch);

		// part (b)
		boolean allMatch = books.stream()
				.allMatch(d -> d.getRating() >= 4.8);
		//.noneMatch(d -> d.getRating() < 4.8);  // can also use this
		System.out.println("allMatch? " + allMatch);

		// part (c)
		boolean noneMatch = books.stream()
				.noneMatch(d -> d.getRating() <= 2.0);
		// .allMatch(d -> d.getRating() > 2.0);	// can also use this
		//.anyMatch(d -> d.getRating() <= 2.0);	// can also use this
		System.out.println("noneMatch? " + noneMatch);

	}

	// Print at most 5 DISTINCT books with rating >= 4.5
	// DB world: select distinct (ISBN) from book where rating >= 4.5 limit 0, 5;
	private static void slice(List<Book> books) {  // demo of slicing a stream
		System.out.println("\nSlice ... ");
		/*List<String> result = books.stream()
			.filter(d -> d.getRating() >= 4.5)
			.distinct()
			//.peek(d -> System.out.println(d.getTitle() + " " + d.getRating()))
			.limit(5)     // will only print first 5 elements
			//.skip(5)   // will skip first 5 elements
			.map(d -> d.getTitle())
			//.forEach(System.out::println);
			.collect(Collectors.toList());  // if we use collect then we need to store it in List

		for(String title : result)
			System.out.println("title: " + title);*/

		// the below code gives same output as above
		// we just made it more clear by dividing the steps
		Stream<Book> booksStream = books.stream()
		.filter(d -> d.getRating() >= 4.5)
		.distinct()
		//.peek(d -> System.out.println(d.getTitle() + " " + d.getRating()))
		.limit(5);

		Stream<String> titleStream = booksStream.map(d -> d.getTitle());
		titleStream.forEach(System.out::println);
	}

	public static void main(String[] args) {

		List<Book> books = new ArrayList<>();

		books.addAll(DataExtractor.getFromAmazon("java"));
		books.addAll(DataExtractor.getFromBarnesAndNoble("java"));

		// intermediate operations (return stream objects)
		//slice(books);

		// terminal operations (return non-stream objects)
		//match(books);  // matching stream elements to some criteria

		// All matching & finding operations + limit
		//    are short-circuit operations (recall &&, ||)

		//find(books);

		//reduce(books);
		//reduceImperatively(books);
		//overloadedReductions();
		//mutableReduction();

		//collectToCollection(books);

		collectToMap(books);
	}

}
