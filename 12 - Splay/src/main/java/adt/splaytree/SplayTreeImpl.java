package adt.splaytree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

public class SplayTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
		implements SplayTree<T> {

	private void splay(BSTNode<T> node) {
		if (node == null || node.isEmpty()) return;
		
		while (!node.getData().equals(root.getData())){
			if (node.getParent().getData().equals(root.getData())){
				if(isRightChild(node)){
					leftRotation((BSTNode<T>) node.getParent());
				}else{
					rightRotation((BSTNode<T>) node.getParent());
				}
			}else if (isRightChild(node) && isRightChild((BSTNode<T>) node.getParent())){
				leftRotation((BSTNode<T>) node.getParent().getParent());
				leftRotation((BSTNode<T>) node.getParent());
			}else if (!isRightChild(node) && !isRightChild((BSTNode<T>) node.getParent())){
				rightRotation((BSTNode<T>) node.getParent().getParent());
				rightRotation((BSTNode<T>) node.getParent());
			}else if(!isRightChild(node) && isRightChild((BSTNode<T>) node.getParent())){
				rightRotation((BSTNode<T>) node.getParent());
				leftRotation((BSTNode<T>) node.getParent());
			}else{
				leftRotation((BSTNode<T>) node.getParent());
				rightRotation((BSTNode<T>) node.getParent());
			}
		}
	}
	
	public boolean isRightChild(BSTNode<T> node){
		return node.getParent().getRight().equals(node);
	}
	
	public void insert(T element){
		if (element == null) return;
		
		super.insert(element);
		splay(super.search(element));	
	}
	
	public BSTNode<T> search(T element){
		if (element == null) return null;
		
		BSTNode<T> buscado = super.search(element);
		if (buscado.isEmpty()){
			splay((BSTNode<T>) buscado.getParent());
		}else{
			splay(buscado);
		}
		return buscado;
	}
	
	public void remove(T element){
		if (element == null) return;
		if	(root.isEmpty()) return;
		if (super.search(element).isEmpty()){
			if(!this.root.getData().equals(element))
				splay((BSTNode<T>) super.search(element).getParent());
		}else{
			T parent = null;
			if(!this.root.getData().equals(element))
				parent = super.search(element).getParent().getData();
			super.remove(element);
			
			if(parent != null)
				splay(super.search(parent));
			}
	}
	
	public void rightRotation(BSTNode<T> node){
  		BSTNode<T> newNode = Util.rightRotation(node);
  		if (newNode.getParent() == null){
  			this.root = newNode;
  		}
  	}
 
  	public void leftRotation(BSTNode<T> node){
  		BSTNode<T> newNode = Util.leftRotation(node);
  		if (newNode.getParent() == null){
  			this.root = newNode;
  		}
  	}
}
