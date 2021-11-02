package bst;

import java.util.ArrayList;
import java.util.Comparator;


public class BinarySearchTree<E extends Comparable<E>> {
  BinaryNode<E> root;  // Används också i BSTVisaulizer
  int size;            // Används också i BSTVisaulizer
  private Comparator<E> comparator;



  	public static void main(String[] args) {
  		BSTVisualizer visualizer = new BSTVisualizer("Tree", 500, 500);
  		BinarySearchTree<Integer> tree = new BinarySearchTree<>();

  		tree.add(1);
  		tree.add(2);
  		tree.add(6);
  		tree.add(4);
		tree.add(-4);
		tree.add(25);

//  		tree.rebuild();
//  		tree.printTree();

  		visualizer.drawTree(tree);
	}


	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		this.comparator = new Comparable();
	}

	class Comparable implements Comparator<E> {
		@Override
		public int compare(E e1, E e2) {
			return e1.compareTo(e2);
		}
	}

	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if(this.root == null) { //add new root
			this.root = new BinaryNode<>(x);
			this.size++;
			return true;
		}
		return this.add(this.root, x); //call private add function
	}

	private boolean add(BinaryNode<E> p, E x) {
		BinaryNode<E> node = new BinaryNode<>(x);
		if (comparator.compare(x, p.element) == 0) return false;
		// Right
		if (comparator.compare(x, p.element) > 0 ) {
			if (p.right == null ) {
				p.right = node;
				this.size++;
			}
			else {
				return this.add(p.right, x);
			}
		}
		// Left
		else {
			if (p.left == null ) {
				p.left = node;
				this.size++;
			}
			else {
				return this.add(p.left, x);
			}
		}
		return true;
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return this.height(root);
	}

	/**
	 * Recursivley calculate height of tree.
	 * @return the height of the three
	 */
	private int height(BinaryNode<E> n) {
		if (n == null) return 0;
		return 1 + Math.max(height(n.left), height(n.right));
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		this.root = null;
		this.size = 0;
	}
	
	/**
	 * Print tree contents in order.
	 */
	public void printTree() {
		//this.printTree(this.root);
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {

	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
	
	}
	
	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		return null;
	}
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
}
