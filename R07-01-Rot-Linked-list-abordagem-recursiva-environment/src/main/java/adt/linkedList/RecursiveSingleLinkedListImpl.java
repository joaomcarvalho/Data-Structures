package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data,
			RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return isNil();
	}
	
	public boolean isNil(){
		return data == null;
	}

	@Override
	public int size() {
		if (isNil())
			return 0;
		return getNext().size() + 1;
	}

	@Override
	public T search(T element) {
		if (data == null || isNil()) return null; 
		
		if (this.getData().equals(element))
			return this.getData();
		return this.getNext().search(element);
	}

	@Override
	public void insert(T element) {
		if (element == null) return;
		
		if (isNil()){
			this.setData(element);
			this.setNext(new RecursiveDoubleLinkedListImpl<>());
		}else{
			this.getNext().insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if (element == null || isEmpty()) return;
		
		if (this.getData().equals(element)){
			if (this.getNext().isNil())
				this.setData(null);
			else{
				this.setData(this.getNext().getData());
				this.setNext(this.getNext().getNext());
			}
		}else{
			remove(element, this.getNext(), this);
		}
	}
	
	public void remove(T element, RecursiveSingleLinkedListImpl<T> current, RecursiveSingleLinkedListImpl<T> prev){
		if (current.isNil()) return;
		
		if (current.getData().equals(element)){
			prev.setNext(current.getNext());
		}else{
			remove (element, current.getNext(), current);
		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[size()];
		
		toArray(array, this, 0);
		
		return array;
	}
	
	public void toArray(T[] array, RecursiveSingleLinkedListImpl<T> current, int i){
		if (current.isNil())
			return;
		
		array[i] = current.getData();
		toArray(array, current.getNext(), i + 1);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
