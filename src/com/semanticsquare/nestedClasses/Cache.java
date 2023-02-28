package com.semanticsquare.nestedClasses;

import java.util.Arrays;
import java.util.Comparator;

interface CacheIterator {
    boolean hasNext();
    Bookmark next();
}

class FooBar {
	protected int y=0;
	FooBar(int x){
		y=x;
	}
}

public class Cache {		
	private Bookmark[] items;	
	private int next = 0;

	private static final Comparator<Bookmark> RATING_COMPARATOR = new Comparator<Bookmark>() {
		{
			System.out.println("instance initializer");
		}
		@Override
		public int compare(Bookmark o1, Bookmark o2) {
			return o1.getRating() < o2.getRating() ? 1:-1;
		}
	};
	
	public Cache(int size) { 
    	items = new Bookmark[size]; 
    }
    
    public void add(Bookmark item) {
	    if(next < items.length)
            items[next++] = item;
    }
    
    public CacheIterator iterator() {
    	// return new MyCacheIterator();  // can also only use this
		int count =0;  // by default it will be made final
		//count++;

		// Instance variable "items" will be accesses via hidden reference
		// But, local variable "count" will be copied as Inner class's instance variable
		CacheIterator iterator = new CacheIterator() {
			private int i = 0;

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				//System.out.println(count);
				return i < items.length;
			}

			@Override
			public Bookmark next() {
				// TODO Auto-generated method stub
				return items[i++];
			}
		};

		return iterator;
    }

	//the above method initially do this
	/*
	public CacheIterator iterator() {
		return new Cache$MyCacheIterator(this);
	}
	 */

	// as below is private, so we need a function to make a constructor of it
	// below is a nested class Cache$MyCacheIterator  --> represent nested class
    private class MyCacheIterator implements CacheIterator {
    	private int i = 0;
    	
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return i < items.length;
		}

		@Override
		public Bookmark next() {
			// TODO Auto-generated method stub
			return items[i++];
		}
    	
    }
    
    public static void main(String[] args) {
		Cache recommendedItems = new Cache(5);

		Bookmark item1 = new Bookmark();
		item1.setId(2000);
		item1.setTitle("Use Final Liberally");
		item1.setRating(1.0);

		Bookmark item2 = new Bookmark();
		item2.setId(2001);
		item2.setTitle("How do I import a pre-existing Java project into Eclipse and get up and running?");
		item2.setRating(2.0);

		Bookmark item3 = new Bookmark();
		item3.setId(2002);
		item3.setTitle("Interface vs Abstract Class");
		item3.setRating(3.0);

		Bookmark item4 = new Bookmark();
		item4.setId(2003);
		item4.setTitle("NIO tutorial by Greg Travis");
		item4.setRating(4.0);

		Bookmark item5 = new Bookmark();
		item5.setId(2004);
		item5.setTitle("Virtual Hosting and Tomcat");
		item5.setRating(5.0);

		recommendedItems.add(item1);
		recommendedItems.add(item2);
		recommendedItems.add(item3);
		recommendedItems.add(item4);
		recommendedItems.add(item5);
		
		CacheIterator iterator = recommendedItems.iterator();
		//CacheIterator iterator = recommendedItems.new MyCacheIterator();  // another way to make an iterator
		
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getTitle());
		}

		/*
		Arrays.sort(recommendedItems.items, new Comparator<Bookmark>() {

			@Override
			public int compare(Bookmark o1, Bookmark o2) {
				// doing in descending order
				// TODO Auto-generated method stub
				return o1.getRating() < o2.getRating() ? 1 : -1;
			}

		});

		 */

		Arrays.sort(recommendedItems.items,RATING_COMPARATOR);

		System.out.println("\nSorted by rating ( anonymous class) ...");
		iterator = recommendedItems.iterator();

		while (iterator.hasNext()) {
			System.out.println(iterator.next().getTitle());
		}

		new FooBar(5){
			void go(){
				System.out.println("\n Printing y in FooBar.go: " + y);
			}
		}.go();

		// Static Class
		Arrays.sort(recommendedItems.items,Bookmark.RATING_COMPARATOR);
		System.out.println("\nSorted by rating (static Member class) ...");
		iterator = recommendedItems.iterator();

		while (iterator.hasNext()) {
			System.out.println(iterator.next().getTitle());
		}


		Arrays.sort(recommendedItems.items,new Bookmark.ComparatorList.StringLengthComparator());
		System.out.println("\nSorted by String Length (static Member class) ...");
		iterator = recommendedItems.iterator();

		while (iterator.hasNext()) {
			System.out.println(iterator.next().getTitle());
		}


	}
    
}
