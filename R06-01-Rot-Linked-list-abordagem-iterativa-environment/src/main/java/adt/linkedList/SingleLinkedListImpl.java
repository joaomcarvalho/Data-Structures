package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return getHead().isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> aux = getHead();
		while (!aux.isNIL()) {
			size++;
			aux = aux.getNext();
		}
		return size;
	}

	@Override
	public T search(T element) {
		if (!isEmpty() && element != null){
			SingleLinkedListNode<T> aux = getHead();
			while (!aux.isNIL()){
				if (aux.getData().equals(element)){
					return aux.getData();
				}
				aux = aux.getNext();
			}
		}
		return null;
	}

	@Override
	public void insert(T element) {
		if (element != null){
			SingleLinkedListNode<T> aux = getHead();
			while (!aux.isNIL()){
				aux = aux.getNext();
			}
			aux.setData(element);
			aux.setNext(new SingleLinkedListNode<>());
		}
	}

	@Override
	public void remove(T element) {
		if (element != null){
			if (element.equals(getHead().getData())){
				setHead(getHead().getNext());
			}else{
				SingleLinkedListNode<T> aux = getHead();
				while (!aux.isNIL()){
					if (aux.getData().equals(element)){
						SingleLinkedListNode<T> front = aux.getNext();
						aux.setData(front.getData());
						aux.setNext(front.getNext());
						break;
					}
					aux = aux.getNext();
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[size()];
		SingleLinkedListNode<T> aux = getHead();
		int i = 0;
		
		while (!aux.isNIL()){
			array[i] = aux.getData();
			aux = aux.getNext();
			i++;
		}
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
