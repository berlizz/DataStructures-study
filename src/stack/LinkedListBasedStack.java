/* 
 * Linked list based stack
 * ���� ����Ʈ ��� ����
 * ��� ������ ��� �߰�(push)
 * ��� �ʿ��� ��� ����(pop)
 *  
 */

package stack;

import list.SimpleLinkedList;

public class LinkedListBasedStack<E> implements Stack<E> {
	
	//private Node<E> head;
	private SimpleLinkedList<E> list;
	
	public LinkedListBasedStack() {
		//head = new Node<>();
		list = new SimpleLinkedList<>();
		
	}
	/*
	@Override
	public boolean isEmpty() {
		return head.getNext() == null;
	}
	
	@Override
	public E push(E data) {
		Node<E> newNode = new Node<>(data);
		newNode.setNext(head.getNext());
		head.setNext(newNode);
		
		return data;
	}
	
	@Override
	public E pop() {
		if(isEmpty()) {
			return null;
		}
		
		E data = head.getNext().getData();
		head.setNext(head.getNext().getNext());
		
		return data;
	}
	
	@Override
	public E peek() {
		return (isEmpty())? null : head.getNext().getData();
	}
	*/
	
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	@Override
	public E push(E data) {
		list.add(data);
		return data;
	}
	
	@Override
	public E pop() {
		if(isEmpty()) {
			return null;
		}
		
		E data = list.getFirstData();
		list.remove(data);
		return data;
	}
	
	@Override
	public E peek() {
		return list.getFirstData();
	}
}
