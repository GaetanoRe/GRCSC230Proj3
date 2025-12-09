package project3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class HashTable<E> implements Iterable<E>{
	
	private LinkedList<E>[] buckets;
	private int keysStored;
	private float loadFactor;
	
	@Override
	public Iterator<E> iterator() {
		
		return new HashTableIterator();
	}
	
	private class HashTableIterator implements Iterator<E>{
		private int bucketIndex = 0;
		private Iterator<E> listIterator = null;
		
		public HashTableIterator() {
			advanceToNextBucket();
		}
		
		@Override
		public boolean hasNext() {
			
			return listIterator != null && listIterator.hasNext();
		}

		@Override
		public E next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			// Finish this and the iterator is done.
			
			return null;
		}
		
		private void advanceToNextBucket() {
			while(bucketIndex < buckets.length) {
				if(!buckets[bucketIndex].isEmpty() && buckets[bucketIndex] != null) {
					listIterator = buckets[bucketIndex].iterator();
					if(listIterator.hasNext()) return;
				}
				bucketIndex++;
			}
			listIterator = null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public HashTable() {
		buckets = new LinkedList[11];
		keysStored = 0;
		loadFactor = 0.4f;
		for(int i = 0; i < buckets.length; i++) {
			buckets[i] = new LinkedList<E>();
		}
	}
	
	@SuppressWarnings("unchecked")
	private HashTable(int length) {
		buckets = new LinkedList[length];
		keysStored = 0;
		loadFactor = 0.4f;
		for(int i = 0; i < buckets.length; i++) {
			buckets[i] = new LinkedList<E>();
		}
		
	}
	
	
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
	
	
	private void resizeTable() {
		LinkedList<E>[] oldbuckets = buckets;
		int newCapacity = getNextPrime();
		
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
	
	private int getNextPrime() {
		// This will be the temporary method of finding the next prime number.
		// Will replace with better one later
		return (buckets.length * 2) + 1;
	}

	

}
