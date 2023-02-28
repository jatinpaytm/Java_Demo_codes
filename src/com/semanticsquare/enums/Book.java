package com.semanticsquare.enums;



public class Book {
	public enum BookGenre { // nested enum
		BIOGRAPHY (12) {
			public boolean isKidFriendly(int age) {
				return age >= minAgeToRead;
			}
		},
		HORROR (15) {
			public boolean isKidFriendly(int age) {
				return false;
			}
		};

		public abstract boolean isKidFriendly(int age);

		private BookGenre(int minAgeToRead) {
			this.minAgeToRead = minAgeToRead;
		}

		protected int minAgeToRead;
		public int getMinAgeToRead() {
			return minAgeToRead;
		}

		// NOTE : switch statement can be of 3 types: integer , String , enum
	    /*public boolean isKidFriendly(int age) {
            switch (this) {
                case BIOGRAPHY : return age >= minAgeToRead;
                case HORROR : return false;
            }
            return false;
            //throw new AssertionError("Unknown operation: " +  this);
        }*/

	}

	public static void main(String[] args) {
		for (BookGenre bookGenre : BookGenre.values()) {
			System.out.println("\nName: " + bookGenre); //toString
			System.out.println(", name(): " + bookGenre.name());
			System.out.println(", Ordinal: " + bookGenre.ordinal());
			System.out.println(", Declaring Class: " + bookGenre.getDeclaringClass());
			System.out.println(", compareTo(HORROR): " + bookGenre.compareTo(BookGenre.HORROR));
			System.out.println(", equals(HORROR): " + bookGenre.equals(BookGenre.HORROR));
			System.out.println(", minAgeToRead: " + bookGenre.getMinAgeToRead());
			System.out.println(", isKidFriendly: " + bookGenre.isKidFriendly(25));
		}
	}

}