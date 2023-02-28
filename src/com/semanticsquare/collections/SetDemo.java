package com.semanticsquare.collections;

import java.util.*;

public class SetDemo {
	
	private static void hashSetDemo() {

		// order is not preserved
		Set<String> set1 = new HashSet<>();
		set1.add("a");
		set1.add("b");
		set1.add("a");
						
		System.out.println("set1: " +  set1);

		Book book1 = new Book("Walden", "Henry Thoreau", 1854);
		Book book2 = new Book("Walden", "Henry Thoreau", 1854);

		Set<Book> set2 = new HashSet<>();
		set2.add(book1);
		set2.add(book2);

		System.out.println("set2: " +  set2);
	}

	public static void linkedHashSetDemo() {
		// order is not  preserved
		Set<String> hashSet = new HashSet<>();
		hashSet.add("Raj");
		hashSet.add("John");
		hashSet.add("Anita");
		System.out.println("hashSet: " + hashSet);
		// order is preserved
		Set<String> linkedHashSet = new LinkedHashSet<>();
		linkedHashSet.add("Raj");
		linkedHashSet.add("John");
		linkedHashSet.add("Anita");
		System.out.println("linkedHashSet: " + linkedHashSet);
	}

	private static void treeSetDemo() {
		Book book1 = new Book("Harry Potter", "J.K.Rowling", 1997);
		Book book2 = new Book("Harry Potter", "J.K.Rowling", 1997);
		Book book3 = new Book("Walden", "Henry David Thoreau", 1854);
		Book book4 = new Book("Effective Java", "Joshua Bloch", 2008);

		Set<Book> books = new TreeSet<>(new TitleComparator());   // passed the custom comparator
		books.add(book1);
		books.add(book2);
		books.add(book3);
		books.add(book4);

		for (Book book : books) {
			System.out.println(book);
		}
	}

	private static void treeSetDemo2() {
		NavigableSet<Integer> set = new TreeSet<>();
		set.add(5);
		set.add(23);
		set.add(74);
		set.add(89);
		System.out.println("set : " + set);
		System.out.println("lower: " + set.lower(74));
		System.out.println("floor: " + set.floor(74));
		System.out.println("ceiling: " + set.ceiling(74));
		System.out.println("higher: " + set.higher(74));

		System.out.println("first: " + set.first());
		System.out.println("last: " + set.last());

		System.out.println("set: " + set);

		NavigableSet<Integer> descendingSet = set.descendingSet();
		System.out.println("descendingSet: " + descendingSet);

		NavigableSet<Integer> headSet = set.headSet(74, true);
		System.out.println("headSet: " + headSet);

		headSet.add(6);
		System.out.println("headSet: " + headSet);
		System.out.println("set: " + set);
		headSet.add(4);

		//headSet.add(75); // throws IllegalArgumentException // should be less than or equal to 74
		System.out.println("set: " + set);

		SortedSet<Integer> subSet = set.subSet(5, 74);
		//subSet.add(2); // throws IllegalArgumentException // because not in range of 5 and 74
		System.out.println("subSet: " + subSet);
		// Adding element in backed set (original set) and
		// see it getting reflected in subSet
		set.add(25);
		System.out.println("subSet: " + subSet);
	}
	
	public static void main(String[] args) {
		//hashSetDemo();
		//linkedHashSetDemo();
		//treeSetDemo();
		treeSetDemo2();
	}	
	
}


class Book /*implements Comparable*/ {
	private String title;
	private String author;
	private int year;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Book(String title, String author, int year) {
		super();
		this.title = title;
		this.author = author;
		this.year = year;
	}


	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", year=" + year + "]";
	}

	// below are auto Generated
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Book book)) return false;
		return getYear() == book.getYear() && getTitle().equals(book.getTitle()) && getAuthor().equals(book.getAuthor());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getTitle(), getAuthor(), getYear());
	}

	/* Both the below methods need to be overridden
	// we need to overide this because .. hash code is generate wit the help of address
	// so when we generate 2 objects with same value then there address is different .. so need to change the hashcode
	public int hashCode() {
		return title.hashCode();
	}

	// need this to compare the objects to remove duplicates
	public boolean equals(Object o) {
		return (year == (((Book)o).getYear())) && (author.equals((((Book)o).getAuthor())));
	}*/



	public int compareTo(Object book) {
		return getTitle().compareTo(((Book) book).getTitle()); // utilizing Stringís compareTo
	}
}


// created a custom comparator which compare on title
class TitleComparator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		// TODO Auto-generated method stub
		 return ((Book)o1).getTitle().compareTo(((Book)o2).getTitle());

	}

}

// sample
class AuthorComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) {
		return ((Book)o1).getAuthor().compareTo(((Book)o2).getAuthor());

	}
}
