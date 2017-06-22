/*
 *	Simple linked list
 *	���� ����� ����Ʈ
 *	��������� ������ �߰�
 */

package list;

public class SimpleLinkedList<E> implements LinkedList<E> {

	private Node<E> head;
	private Node<E> position;
	private Node<E> before;
	private int size;
	
	public SimpleLinkedList() {
		this.head = new Node<E>();  // ���̳�� ����
		this.position = head;
		this.before = null;
		this.size = 0;
		
		head.setNext(null);
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	// ��� �߰�
	@Override
	public void add(E data) {
		Node<E> newNode = new Node<>(data);
		newNode.setNext(head.getNext());
		head.setNext(newNode);
		size++;
	}
	
	// ù��° ��� ��������
	@Override
	public E getFirstData() {
		if(isEmpty()) {
			return null;
		}
		
		before = head;
		position = head.getNext();
		
		return position.getData();
	}
	
	// ���� ��� ��������
	@Override
	public E getNextData() {
		if(position.getNext() == null) {
			return null;
		}
		
		before = position;
		position = position.getNext();
		
		return position.getData();
	}
	
	// ��� ��ȸ
	@Override
	public boolean contains(E data) {
		E check = getFirstData();
		
		if(check != null && check.equals(data)) {
			return true;
		}
		
		while(true) {
			check = getNextData();
			
			if(check == null) {
				break;
			}
			
			if(check.equals(data)) {
				return true;
			}			
		}
		
		return false;
	}
	
	// ��� ����
	@Override
	public E remove(E data) {
		E check = getFirstData();
		if(check.equals(data)) {
			before.setNext(position.getNext());
			position = before;
			size--;
			
			return check;
		}
		
		while(true) {
			check = getNextData();
			if(check == null) {
				break;
			}
			
			if(check.equals(data)) {
				before.setNext(position.getNext());
				position = before;
				size--;
				
				return check;
			}
		}
		
		return null;
	}
	
	@Override
	public int size() {
		return size;
	}
}
