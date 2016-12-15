package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) throw new QueueOverflowException();
		if (element != null){
			list.insert(element);
			size--;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) throw new QueueUnderflowException();
		
		T element = ((DoubleLinkedListImpl<T>) list).getHead().getData();
		size++;
		list.removeFirst();
		return element;
	}

	@Override
	public T head() {
		return ((DoubleLinkedListImpl<T>) list).getHead().getData();
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
