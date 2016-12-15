package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;
	
	public DoubleLinkedListImpl() {
		super();
		setHead(new DoubleLinkedListNode<T>());
		this.last = getHead();
	}
		
	@Override
	public void insertFirst(T element) {
		if (element == null)
			return;
		
		if (isEmpty()){
			insert(element);
		}else{
			DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>(element, getHead(), new DoubleLinkedListNode<T>());
			getHead().setPrevious(newHead);
			setHead(newHead);
		}
	}

	@Override
	public void removeFirst() {
		if (isEmpty())
			return;
		((DoubleLinkedListNode<T>) getHead().getNext()).setPrevious(new DoubleLinkedListNode<T>());
		
		if (this.size() == 1){
			setLast( (DoubleLinkedListNode<T>) head.getNext());
		}
		
		setHead(head.getNext());
	}

	@Override
	public void removeLast() {
		if (isEmpty())
			return;
		
		DoubleLinkedListNode<T> newLast = getLast().getPrevious();
		newLast.setNext(new DoubleLinkedListNode<T>());
		if (this.size() == 1){
			setHead(newLast);
		}
		setLast(newLast);
	}
	
	@Override
	public void insert(T element){
		if (element == null)
			return;
		
		if (isEmpty()){
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<T>(), new DoubleLinkedListNode<T>());
			this.setHead(newNode);
			this.setLast(newNode);
		}else{
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<T>(), getLast());
			getLast().setNext(newNode);
			setLast(newNode);
		}
	}
	
	@Override
	public void remove(T element){
		if (element == null)
			return;
		
		if (!isEmpty()){
			if (getHead().getData().equals(element)){
				((DoubleLinkedListNode<T>) head.getNext()).setPrevious(new DoubleLinkedListNode<T>());
				if (this.size() == 1)
					last = (DoubleLinkedListNode<T>) head.getNext();
				
				head = head.getNext();
			}else{
				DoubleLinkedListNode<T> aux = getHead();
				
				while (!aux.isNIL()){
					if (aux.getData().equals(element)){
						aux.getPrevious().setNext(aux.getNext());
						((DoubleLinkedListNode<T>) aux.getNext()).setPrevious(aux.getPrevious());
						if (aux.getNext().isNIL()){
							setLast(aux.getPrevious());
						}
						break;
					}
					aux = (DoubleLinkedListNode<T>) aux.getNext();
				}
			}
		}
	}

	public DoubleLinkedListNode<T> getHead(){
		return (DoubleLinkedListNode) super.getHead();
	}
	
	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setHead(DoubleLinkedListNode<T> head){
		this.head = (SingleLinkedListNode) head;
	}
	
	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
