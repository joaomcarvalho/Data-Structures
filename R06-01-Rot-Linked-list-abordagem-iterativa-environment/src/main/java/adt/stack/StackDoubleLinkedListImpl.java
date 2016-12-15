package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull())
			throw new StackOverflowException();
		
		if (element != null){
			list.insert(element);
			size--;
		}

	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) 
			throw new StackUnderflowException();
		
		T element = ((DoubleLinkedListImpl<T>) list).getLast().getData();
		list.removeLast();
		size++;
		
		return element;
	}

	@Override
	public T top() {
		return ((DoubleLinkedListImpl<T>) list).getLast().getData();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean isFull() {
		return size == 0;
	}

}
