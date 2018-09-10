package project2;
/**
 * This is a class of Circular Doubly Linked List of generic data type.
 * @author Zhenghan Zhang
 */
public class CircularDoublyLinkedList<E> implements Cloneable{
	private static class Node<E>{
	private E data;
	public Node<E> next;
	public Node<E> prev;
	
	/**
	 * Constructor
	 * @param e Element:the data stored inside
	 * @param n denotes the next node
	 * @param p denotes the previous node
	 */
	public Node(E e, Node<E> n, Node<E> p){
		data = e;
		next = n;
		prev = p;
	}
	
	/**
	 * This method gives the data stored in one node
	 * @return the data stored within the node
	 */
	public E getData() {
		return data;
	}
	
	/**
	 * @param data the data to put inside the node
	 * This method sets the data within the node
	 */
	public void setData(E data) {
		this.data = data;
	}
	
	/**
	 * This method gives the next node
	 * @return the next node within the List
	 */
	public Node<E> getNext(){
		return next;
	}
	
	/**
	 * @param  next the next node
	 * This method sets the next node (connecting two nodes)
	 */
	public void setNext(Node<E> next) {
		this.next = next;
	}
	
	/**
	 * This method gives the previous node
	 * @return the previous node within the List
	 */
	public Node<E> getPrev(){
		return prev;
	}
	
	/**
	 * @param prev the previous node
	 * This method sets the previous node (connecting two nodes)
	 */
	public void setPrev(Node<E> prev) {
		this.prev = prev;
	}
	}
	//initialize the List
	public Node<E> head;
	public Node<E> tail;
	public int size = 0;
	
	 /**
	  * 
	  */
	public void CircularDoublyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	 * This method gets the size of the list 
	 * @return the integer of the size of the list
	 */
	public int size() {
		return size;
	}
	
	/**
	 * This method determines whether the list is an empty list
	 * @return the boolean value of whether the list is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * This method returns the data of the first node
	 * @return the element stored within the first node
	 */
	public E first() {
		if (isEmpty()) {
			return null;
		}
		return head.getData();
	}
	
	/**
	 * This method returns the data of the last node
	 * @return the element stored within the last node
	 */
	public E last() {
		if (isEmpty()) {
			return null;
		}
		return tail.getData();
	}
	
	/**
	 * This method adds a node as the first node
	 * @param e the element to store within the new node
	 */
	public void addFirst(E e) {
		if (size == 0) {
			Node<E> temp = new Node<E>(e, null, null);
			temp.setPrev(temp);
			temp.setNext(temp);
			head = temp;
			tail = temp;
			size++;
		}
		else {
			Node<E> temp = new Node<E>(e, null, null);
			tail.setNext(temp);
			head.setPrev(temp);
			temp.setPrev(tail);
			temp.setNext(head);
			head = temp;
			size++;
		}
	}
	
	/**
	 * This method adds a node as the last node
	 * @param e the element to store within the new node
	 */
	public void addLast(E e) {
		if (size == 0) {
			Node<E> temp = new Node<E>(e, null, null);
			temp.setPrev(temp);
			temp.setNext(temp);
			head = temp;
			tail = temp;
			size++;
		}
		else {
			Node<E> temp = new Node<E>(e, null, null);
			tail.setNext(temp);
			head.setPrev(temp);
			temp.setPrev(tail);
			temp.setNext(head);
			tail = temp;
			size++;
		}
	}
	/**
	 * This methods adds a new node between two given nodes
	 * @param e the element to store within the new node
	 * @param predecessor the node preceding the new node
	 * @param successor the node following the new node
	 */
	private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
		Node<E> newest = new Node<>(e, predecessor, successor);
		predecessor.setNext(newest);
		successor.setPrev(newest);
		size++;
	}

	/**
	 * This method deletes the first node
	 * @return the element stored within the deleted node
	 */
	public E removeFirst() {
		if (size == 1) {
			E dataHead = head.getData();
			head = null;
			tail = null;
			size--;
			return dataHead;
		}
		else if (size >= 2) {
			E dataHead = head.getData();
			tail.setNext(head.next);
			head.next.setPrev(head.prev);
			head = head.next;
			size--;
			return dataHead;
		}
		return null;
	}
	
	/**
	 * This method deletes the last node
	 * @return the element stored within the deleted node
	 */
	public E removeLast() {
		if (size == 1) {
			E dataHead = tail.getData();
			head = null;
			tail = null;
			size--;
			return dataHead;
		}
		else if (size >= 2) {
			E dataHead = tail.getData();
			tail.prev.setNext(head);
			head.setPrev(tail.prev);
			tail = tail.prev;
			size--;
			return dataHead;
		}
		return null;
	}
	
	
	/**
	 * This method removes a given node
	 * @param node the node to be removed
	 * @return the element stored within the node
	 */
	private E remove(Node<E> node) {
		if (size == 1) {
			head = null;
			return node.getData();
		}
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		return node.getData();		
	}
	
	//Overrides the toString() method;
	@Override
	public String toString() {
		String newString = "[";
		Node<E> n = head;
		int pointer = 0;
		while (pointer < this.size()) {
			newString = newString + n.getData().toString() + ", ";
			n = n.getNext();
			pointer++;
		}
		if (newString.length() > 2) {
			newString = newString.substring(0,newString.length()- 2);
		}
		newString = newString + "]";
		return newString;
	}
	
	//Override the equals method
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof CircularDoublyLinkedList)) {
			return false;
		}
		CircularDoublyLinkedList<E> p = (CircularDoublyLinkedList<E>) o;
		if (this.size() != p.size()) {
			return false;
		}
		if (this.size() == 0) {
			return true;
		}
			int pointer = 0;
			while (pointer < this.size()) {
				E element1 = this.get(pointer);
				E element2 = p.get(pointer);
				pointer++;
				if (!element1.equals(element2)) {
					return false;
				}
			}
			return true;
		}
	
	
	//Override the clone method
	@Override
	public CircularDoublyLinkedList<E> clone() throws CloneNotSupportedException{
		CircularDoublyLinkedList<E> other = (CircularDoublyLinkedList<E>) super.clone();
		if (size > 1) {
			other.head = new Node<>(head.getData(),null,null);
			Node<E> walk = head.getNext();
			Node<E> otherTail = other.head;
			while (walk != head) {
				Node<E> newest = new Node<>(walk.getData(),null,null);
				otherTail.setNext(newest);
				newest.setPrev(otherTail);
				newest.setNext(head);
				otherTail = newest;
				walk = walk.getNext();
			}
			head.setPrev(otherTail);
		}
		if (size == 1) {
			other.head = new Node<>(head.getData(),null,null);
			Node<E> otherTail = other.head;
			otherTail.setNext(otherTail);
			otherTail.setPrev(otherTail);
					}
		if (size == 0) {
			return null;
		}
		return other;
	}
	
	

	/**
	 * This method gets the element stored within a given position
	 * @param index the position of the node
	 * @return the element stored within the specified node
	 */
	public E get(int index){
		 if (index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException("The input index is out of range");
		}
		Node<E> n = head;
		int pointer = 0;
		while (pointer < index) {
			n = n.getNext();
			pointer++;
		}
		E info = n.getData();
		return info;
	}

	
	/**
	 * This method is to insert a new node to replace an old one at a given index
	 * @param index the position of the node to be replaced
	 * @param element the element to be put into the new node
	 * @return the element previously stored within the replaced node
	 */
	public E set(int index, E element){
		if (index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException("The input index is out of range");
		}
		Node<E> n = head;
		int pointer = 0;
		while (pointer < index) {
			n = n.getNext();
			pointer++;
		}
		E info = n.getData();
		Node<E> newNode = new Node<>(element, null,null);
		newNode.setNext(n.getNext());
		newNode.setPrev(n.getPrev());
		n.getPrev().setNext(newNode);
		n.getNext().setPrev(newNode);
		return info;
	}

	/**
	 * This method puts the first node to follow the last node
	 */
	public void rotate() {
			if (this.size() >= 2) {
				E element = removeFirst();
				addLast(element);
			}
		
	}
	
	/**
	 * This method puts the last node to precede the first node
	 */
	public void rotateBackward() {
		if (this.size() >= 2) {
			E element = removeLast();
			addFirst(element);
		}
	}
} 