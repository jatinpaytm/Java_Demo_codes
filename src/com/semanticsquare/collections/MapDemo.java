package com.semanticsquare.collections;

import java.util.*;


public class MapDemo {
	
	private static void hashMapDemo() {

		System.out.println("\n\nInside hashMapDemo ...");
		System.out.println();
		Map<String, Integer> map1 = new HashMap<>();
		map1.put("John", 25);
		map1.put("Raj", 29);
		map1.put("Anita", null);
		
		System.out.println(map1);
		
		map1.put("Anita", 23);
		System.out.println(map1);
		
		System.out.println("Contains John? " + map1.containsKey("John"));
		System.out.println("John's age: " + map1.get("John"));
		
		System.out.println("Iterating using keySet ...");
		Set<String> names = map1.keySet();
		for (String name : names) {
			System.out.println("Name: " + name + ", Age: " + map1.get(name));
		}
		
		System.out.println("Iterating using entrySet ...");
		Set<Map.Entry<String, Integer>> mappings = map1.entrySet();
		for (Map.Entry<String, Integer> mapping : mappings) {
			System.out.println("Name: " + mapping.getKey() + ", Age: " + mapping.getValue());
		}
		
		names.remove("Anita");
		System.out.println(map1);
		// used object because the value can be of any type,, so while accessing it we will downcast it
		Map<String, Map<String, Object>> userProfile = new HashMap<>();
		
		Map<String, Object> profile = new HashMap<>();
		profile.put("age", 25.7);
		profile.put("dept", "CS");
		profile.put("city", "New York");
		
		userProfile.put("John", profile);
		
		profile = new HashMap<>();
		profile.put("age", 29.8);
		profile.put("dept", "CS");
		profile.put("city", "New York");
		
		userProfile.put("Raj", profile);
		
		System.out.println("userProfile: " + userProfile);
		
		Map<String, Object> profile1 = userProfile.get("John");
		// need to downcast it
		double age = (double) profile1.get("age");
		System.out.println("Age: " + age);
		System.out.println("dept: " + profile1.get("dept"));
		// Exercise: Try using second constructor, putAll, clear, values, and other methods
	}

	private static void immutableKeysDemo() {
		System.out.println("\n\nInside immutableKeysDemo ...");
		List<Integer> list = new ArrayList<>();
		list.add(1);
		// dont make mutable object as keys
		// Like in below eg : the list is made key and we know the list can we expanded
		// and internally Arraylist implements a hashcode which changes when a new value is inserted
		// so hashcode is not always same so it creates a problem in mapping and return the value as null for then same list
		Map<List<Integer>, Integer> map = new HashMap<>();
		map.put(list, 1);

		list.add(2);
		System.out.println(map.get(list));
		// and object is immutable so do not create problem
		Student s = new Student(1, null);
		Map<Student, Integer> map2 = new HashMap<>();
		map2.put(s, 1);

		s.setName("John");
		System.out.println(map2.get(s));
	}

	/*
	 * Demo:
	 * 	1. See output with and without commenting get() calls
	 *    2. See output when accessOrder=false with get() calls. get calls do not have any influence
	 *    3. Finally, change object type from LRUCache to LinkedHashMap and see output.
	 *       All 5 mappings will be printed as removeEldestEntry would return false by default
	 */
	private static void lruCacheTest() {
		System.out.println("\n\nInside lruCacheTest ...");
		Map<String, String> lruCache = new LRUCache<>(16, 0.75f, true);
		// if we used the below code then the  size will not be fixed and least recently used element will not be removed .. so we are using the upper one
		// Map<String, String> lruCache = new LinkedHashMap<>(16, 0.75f, true);
		lruCache.put("a", "A");
		lruCache.put("b", "B");
		lruCache.put("c", "C");
		System.out.println(lruCache);
		// the recently used element comes on to the right .. see in output
		lruCache.get("a"); // multiple gets to "a" will not make a difference
		lruCache.get("a");
		lruCache.get("a");
		System.out.println(lruCache);
		lruCache.get("b");
		System.out.println(lruCache);

		lruCache.put("d", "D");
		System.out.println(lruCache);
		lruCache.put("e", "E");
		System.out.println(lruCache);
	}

	private static void treeMapDemo() {
		System.out.println("\n\nInside treeMapDemo ...");
		TreeMap<String, Integer> map1 = new TreeMap<>();
		map1.put("John", 25);
		map1.put("Raj", 29);
		map1.put("Anita", 23);

		System.out.println(map1);

		System.out.println("Iterating using entrySet ...");
		// just used to iterate the map
		Set<Map.Entry<String, Integer>> mappings = map1.entrySet();
		for (Map.Entry<String, Integer> mapping : mappings) {
			System.out.println("Name: " + mapping.getKey() + ", Age: " + mapping.getValue());
			// setting the new value
			if (mapping.getKey().equals("John"))
				mapping.setValue(26);
		}
		System.out.println(map1);
		//map1.floorEntry("Raj").setValue(22);// something related to snapshot .. not able to understand
	}
		
	public static void main(String[] args) {
		//hashMapDemo();
		//immutableKeysDemo();
		//lruCacheTest();
		treeMapDemo();
	}	
}

// this is done to make size fixed and apply , removing the least recently used ele when it is full
class LRUCache<K,V> extends LinkedHashMap<K,V> {
	//private static final long serialVersionUID = 6464155743798737431L;
	private static final int MAX_ENTRIES = 3;

	public LRUCache(int initialCapacity,
					float loadFactor,
					boolean accessOrder) {
		super(initialCapacity, loadFactor, accessOrder);
	}

	// Invoked by put and putAll after inserting a new entry into the map
	@Override
	public boolean removeEldestEntry(Map.Entry eldest) {
		return size() > MAX_ENTRIES;
		// return false; // same as normal linked hash map
	}
}

class Student {
	private int id;
	private String name;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Student(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}

