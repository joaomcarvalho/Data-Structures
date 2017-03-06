package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (isFull()) throw new HashtableOverflowException();
		if (element == null || search(element) != null) return;
		
		int i = 0;
		
		while (i < table.length){
			int index = getHashFunction().hash(element, i);
			
			if (table[index] == null || table[index].equals(deletedElement)){
				table[index] = element;
				break;
			}
			COLLISIONS++;
			i++;
		}
		
		elements++;
	}

	@Override
	public void remove(T element) {
		if (element == null || isEmpty()) return;
		
		int i = 0;
		
		while (i < table.length){
			int index = getHashFunction().hash(element, i);
			if (table[index] == null) break;
			if(table[index].equals(element)){
				table[index] = deletedElement;
				elements--;
				break;
			}
			i++;
		}
		
	}

	@Override
	public T search(T element) {
		if (indexOf(element) == -1) return null;
		return element;
	}

	@Override
	public int indexOf(T element) {
		if (element == null) return -1;
		int i = 0;
		while (i < table.length){
			int index = getHashFunction().hash(element, i);
			if (table[index] == null) return -1;
			if(table[index].equals(element)) return index;
			
			i++;
		}
		return -1;
	}

	public HashFunctionLinearProbing<T> getHashFunction(){
		return (HashFunctionLinearProbing<T>) this.hashFunction;
	}
}
