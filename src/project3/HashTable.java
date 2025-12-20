package project3;
/**
 * <p>Title: Hash Table class</p>
 * <p>Description: The class that allows for the creation of a hash table.</p>
 * 
 * @author Gaetano Re
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashTable<E> implements Iterable<E>{
	
	private LinkedList<E>[] buckets; // The buckets the data will be placed into
	private int keysStored; // The amount of total items stored
	private float loadFactor; // The load factor that dictates when to increase the size of the table must increase
	
	/**
	 * Iterator method - This allows the hash table to be iterated
	 */
	@Override
	public Iterator<E> iterator() {
		
		return new HashTableIterator();
	}
	
	/**
	 * <p>HashTableIterator class</p>
	 * <p>Description: An iterator class that allows for iteration through the hash table</p>
	 * 
	 * @author Gaetano Re
	 */
	private class HashTableIterator implements Iterator<E>{
		private int bucketIndex = 0; // Keeps track of the bucket index
		private Iterator<E> listIterator = null; // A built in list iterator for each bucket
		
		/**
		 * HashTableIterator method - Iterates through the table by advancing to the next bucket
		 */
		public HashTableIterator() {
			advanceToNextBucket();
		}
		
		/**
		 * hasNext Override method - This is an override method of the hasNext() method from the Iterator class.
		 * It detects if there is an item in the bucket or if said bucket has another element in the list.
		 */
		@Override
		public boolean hasNext() {
			
			return listIterator != null && listIterator.hasNext();
		}

		/**
		 * next Override method - This is an override method of the next() method from the Iterator class.
		 * It detects if there is a value in the bucket before proceeding and returns the element if there is a next value
		 * in the linked list.
		 */
		@Override
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			E value = listIterator.next(); // Get the next bucket value

	        // If end of this bucket’s list is reached, advance to next bucket
	        if (!listIterator.hasNext()) {
	            bucketIndex++; // increase the bucket index
	            advanceToNextBucket(); // go to the next bucket
	        }

	        return value; // return the value
		}
		
		
		private void advanceToNextBucket() {
			while(bucketIndex < buckets.length) { // while the bucket index doesn't reach the maximum value
				if(!buckets[bucketIndex].isEmpty() && buckets[bucketIndex] != null) { // If the bucket is not empty
					listIterator = buckets[bucketIndex].iterator(); // use the list iterator
					if(listIterator.hasNext()) return; // if the list has a next value, then stop
				}
				bucketIndex++; // increase the bucket index
			}
			listIterator = null; // reset the list iterator for the next bucket
		}
		
	}
	
	/**
	 * default constructor - creates a hash table of length 11 initially
	 */
	@SuppressWarnings("unchecked")
	public HashTable() {
		buckets = new LinkedList[11]; 
		keysStored = 0;
		loadFactor = 0.4f;
		
		// Instantiate a linked list in each bucket
		for(int i = 0; i < buckets.length; i++) {
			buckets[i] = new LinkedList<E>();
		}
	}
	
	/**
	 * parameterized private constructor - this is a private parameterized constructor that is only used to resize 
	 * the hash table.
	 * @param length
	 */
	@SuppressWarnings("unchecked")
	private HashTable(int length) {
		buckets = new LinkedList[length];
		keysStored = 0;
		loadFactor = 0.4f;
		for(int i = 0; i < buckets.length; i++) {
			buckets[i] = new LinkedList<E>();
		}
		
	}
	
	/**
	 * 
	 * @param item
	 */
	public void insert(E item) {
		int index = Math.abs(item.hashCode()) % buckets.length;
		buckets[index].insert(item);
		keysStored++;
		if((float)keysStored/buckets.length > loadFactor) {
			resizeTable();
		}
	}
	
	public E get(E item) {
		return buckets[Math.abs(item.hashCode()) % buckets.length].search(item);
	}
	
	public void delete(E item) {
		buckets[Math.abs(item.hashCode()) % buckets.length].delete(item);
		keysStored--;
	}
	
	public int length() {
		return buckets.length;
	}
	
	public String toString() {
		StringBuilder strbld = new StringBuilder();
		for(E item : this) {
			strbld.append(item);
			strbld.append('\n');
		}
		
		return strbld.toString();
	}
	
	
	private void resizeTable() {
		System.out.println("Resizing now...");
		LinkedList<E>[] oldbuckets = buckets;
		int newCapacity = nextPrime(buckets.length * 2);
		System.out.println("Old length was: " + buckets.length + "New length is: " + newCapacity);
		
		HashTable<E> newTable = new HashTable<E>(newCapacity);
		
		for(int i = 0; i < oldbuckets.length; i++) {
			if(!oldbuckets[i].isEmpty()) {
				for(E item : oldbuckets[i]) {
					newTable.insert(item);
				}
			}
		}
		
		buckets = newTable.buckets;
		keysStored = newTable.keysStored;
	}
	
	// I would like to thank the University of Texas for this code. It was found at:
	// https://www.cs.utexas.edu/~mhan/courses/cs314h-csb/fa25/lecture_notes/PrimeFinder.java
	// I rearranged the code a bit to fit the scope of the project
	 // Function to check if a number is prime
    

    // Function to find the next prime number larger than the input
    private int nextPrime(int num) {
        // Start searching from the next number
        int nextNumber = num + 1;

        // Loop until a prime number is found
        while (!isPrime(nextNumber)) {
            nextNumber++;
        }

        return nextNumber;
    }
    
    private boolean isPrime(int num) {
        if (num <= 1) return false;
        if (num == 2 || num == 3) return true;
        if (num % 2 == 0 || num % 3 == 0) return false;

        for (int i = 5; i * i <= num; i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
	

}
