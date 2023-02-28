package com.semanticsquare.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.*;

import org.htmlcleaner.HtmlCleaner;

/**
 * Demonstrates:
 *
 *  Function & Predicate (including Chaining & Compose)
 *  Bi-Function as an alternate to Predicate
 *
 *  Consumer & Supplier
 *  Show API too (including primitive functional interfaces) !!
 *
 *  Method References
 *  TODO: Constructor References
 *
 * @author Jatin Singh
 *
 */
public class FunctionalInterfaceDemo {

	public static void main(String[] args) {

		String doc1 = "<html><body>One of the most common uses of <i>streams</i> is to represent queries over data in collections</body></html>";
		String doc2 = "<html><body>Information integration systems provide valuable services to users by integrating information from a number of autonomous, heterogeneous and distributed Web sources</body></html>";
		String doc3 = "<html><body>Solr is the popular, blazing fast open source enterprise search platform from the Apache Lucene</body></html>";
		String doc4 = "<html><body>Java 8 goes one more step ahead and has developed a streams API which lets us think about parallelism</body></html>";

		List<String> documents = new ArrayList<>(Arrays.asList(doc1, doc2, doc3, doc4));

		List<String> targetDocuments = new ArrayList<>();
		// we are filtering those strings which have "stream" as string.
		for (String doc : documents) {
				//boolean isTargetDoc = filter(doc, d -> d.contains("stream"));  // it return whether doc contains stream or not  // we are using a lambda function here
				// BiFunction<String, String, Boolean> biFunction = (d, c) -> d.contains(c);

				// (iii) Method References (ClassName::instanceMethod)
				Function<String, Boolean> function = doc::contains;

				BiFunction<String, String, Boolean> biFunction = String::contains;
				//if (biFunction.apply(doc, "stream")) {   // using biFunction --> just a way of doing things
				if (function.apply("stream")) {
				//if(isTargetDoc){
					//doc = transform(doc,d -> Indexer.stripHtmlTags(d));  // lambda
					//doc = transform(doc,d -> Indexer.removeStopwords(d)); // lambda


					Function<String, String> htmlCleaner = d -> Indexer.stripHtmlTags(d);  // storing it and then using it
					//doc = transform(doc, htmlCleaner);

					//Function<String, String> stopwordRemover = d -> Indexer.removeStopwords(d);
					//stopwordRemover.apply(doc);  // we can invoke like this also

					// (i) Method References (ClassName::staticMethod)
					Function<String, String> stopwordRemover = Indexer::removeStopwords; // we dont need to use parameters because they are implicit
					doc = stopwordRemover.apply(doc);
					// Note: Type of Method reference should be appropriate for the abstract method in fn'al interface

					Function<String, String> docProcessor = htmlCleaner.andThen(stopwordRemover);  // other way of applying the calls in a serialized way
					doc = transform(doc, docProcessor);


					// System.out.println(doc);

					targetDocuments.add(doc);
				}

		}

		//targetDocuments.forEach(d-> System.out.println(d));   // consumer method --> it has only single method which is Accept()

		// (ii) Method References (objectRef::instanceMethod)
		targetDocuments.forEach(System.out::println);  // consumer type --> accept method

		// Constructor References

		// Typical scenario
		Supplier<String> supplier = String::new; //() -> new String();
		System.out.println("\nsupplier.get: " + supplier.get());

		Function<String, String> function = String::new; //s -> new String(s);
		System.out.println("\nfunction.apply: " + function.apply("Java"));

		BiFunction<Integer, Float, HashMap> biFunction = HashMap::new; //(c, lf) -> new HashMap(c, lf);  // c-> capacity , lf-> load factor
		System.out.println("\nbiFunction.apply: " + biFunction.apply(100, 0.75f)); // empty hashmap

		Consumer<String> consumer = String::new;
		consumer.accept("Java");
		// Create own interface if existing functional interfaces are not useful!
		// Later we will see them in Stream Api demos

		// we are going to throw an exception if the letter count is more than 80
		for (String doc : targetDocuments) {
			try {
				if (doc.length() > 80) {
					throw new Exception("Oversized document!!!");
				}
			} catch (Exception e) {
				print(() -> e.getMessage() + " ~ " + doc);  // using Lambda function
			}
		}



	}

	private static boolean errorFlag = true;  // if I set it to false .. definately below function will not work .. this is called differed the situation
	private static void print(Supplier<String> supplier) {  // used Supplier method
		if (errorFlag) {
			System.out.println(supplier.get());
		}
	}

	static boolean filter(String doc, Predicate<String> filter) {   // the logic is passed when the function is called // Predicate
		return filter.test(doc);
	}

	static String transform(String doc, UnaryOperator<String> transformer) {  // we use unary operator when we know the arguments are of same type
																				// we can also use Function<String,String> like the below function
		return transformer.apply(doc);
	}
	static String transform(String doc, Function<String, String> transformer) { //Function
		return transformer.apply(doc);
	}

}

class Indexer {

	private static List<String> stopWords = Arrays.asList("of", "the", "a", "is", "to", "in", "and");

	static String stripHtmlTags(String doc) {  // removing html tags .. for this to happen we need to download a external libraray of htmlcleaner
		return new HtmlCleaner().clean(doc).getText().toString();
	}

	static String removeStopwords(String doc) {

		StringBuilder sb = new StringBuilder();
		for (String word : doc.split(" ")) {
			if (!stopWords.contains(word))
				sb.append(word).append(" ");
		}

		return sb.toString();
	}

}

