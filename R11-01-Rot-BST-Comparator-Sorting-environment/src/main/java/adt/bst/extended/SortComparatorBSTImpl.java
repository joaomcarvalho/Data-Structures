package adt.bst.extended;

import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public T[] sort(T[] array) {
		while (!this.root.isEmpty()){
			this.remove(this.root.getData());
		}
		
		for (int i = 0; i < array.length; i++){
			this.insert(array[i]);
		}
		
		return this.order();
	}

	@Override
	public T[] reverseOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		reverseOrder(array, this.root, 0);
		return array;
	}
	
	private int reverseOrder(T[] array, BSTNode<T> node, int index){
		if (!node.isEmpty()) {
			index = reverseOrder(array, (BSTNode<T>) node.getRight(), index);
			array[index] = node.getData();
			index += 1;
			index = reverseOrder(array, (BSTNode<T>) node.getLeft(), index);
		}
		return index;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
	@Override
	public BSTNode<T> search(T element) {
		return search(element, getRoot());
	}
 
	public BSTNode<T> search(T element, BSTNode<T> node) {
		if (element == null || node.isEmpty()){
			return new BSTNode<T>();
		}
 
		if (node.isEmpty() || getComparator().compare(node.getData(), element) == 0){
			return node;
		}else if (getComparator().compare(node.getData(), element) > 0){
			return search(element, (BSTNode<T>) node.getLeft());
		}else{
			return search(element, (BSTNode<T>) node.getRight());
		}
	}
	
	@Override
	public void insert(T element) {
		if (element == null) return;
		insert (element, getRoot());
	}
 
	private void insert(T element, BSTNode<T> node) {
		if (node.isEmpty()){
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);
		}
		else{
			if (getComparator().compare(node.getData(), element) < 0){
				insert(element, (BSTNode<T>) node.getRight()); 
			}else if (getComparator().compare(node.getData(), element) > 0){
				insert(element, (BSTNode<T>) node.getLeft());
			}
		}
	}
}
