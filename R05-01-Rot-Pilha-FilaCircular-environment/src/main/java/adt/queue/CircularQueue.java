package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private T[] array;
	private int tail;
	private int head;
	private int elements;

	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		head = -1;
		tail = -1;
		elements = 0;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()){
			throw new QueueOverflowException();
		}else if (isEmpty()){
			head = 0;
			tail = 0;
			array[tail] = element;
			elements++;
		}else{
			tail = (tail + 1) % array.length;
			array[tail] = element;
			elements++;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()){
			throw new QueueUnderflowException();
		}else{
			elements--;
			T element = array[head];
			if (elements == 0){
				tail = head = -1;
			}else{
				head = (head + 1) % array.length;
			}
			
			return element;
		}
	}

	@Override
	public T head() {
		if (head == -1){
			return null;
		}else{
			return array[head];
		}
	}

	@Override
	public boolean isEmpty() {
		return elements == 0;
	}

	@Override
	public boolean isFull() {
		return elements == array.length;
	}

}
