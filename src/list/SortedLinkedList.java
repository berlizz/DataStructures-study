/*
 *	Sorted linked list
 *	���� ����� ����Ʈ
 *	����� data���� ���Ͽ� ������ �߰�
 *	
 */

package list;

public class SortedLinkedList<E> implements LinkedList<E> {

	private Node<E> head;
	private Node<E> position;
	private Node<E> before;
	private Node<E> comparator;
	private int size;
	
	public SortedLinkedList() {
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
		comparator = head;
		
		// ���� ��尡 null�� �ƴϰ�, ������ ��庸�� �켱������ �������(����ʿ� ����� ���)
		while(comparator.getNext() != null && compare(comparator.getNext().getData(), data) > 0) {
			comparator = comparator.getNext();
		}
		
		newNode.setNext(comparator.getNext());
		comparator.setNext(newNode);
		size++;
	}
	
	// ù��° ������ ��������
	@Override
	public E getFirstData() {
		if(isEmpty() ) {
			return null;
		}
		
		position = head.getNext();
		before = head;
		
		return position.getData();
	}
	
	// ���� ������ ��������
	@Override
	public E getNextData() {
		if(position.getNext() == null) {
			return null;
		}
		
		before = position;
		position = position.getNext();
		
		return position.getData();
	}
	
	// ������ ��ȸ
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
	
	// ������ ����
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
	
	// data�� ��
	private int compare(E data1, E data2) {
		String s1 = String.valueOf(data1);
		String s2 = String.valueOf(data2);
		
		//return s1.toLowerCase().compareTo(s2.toLowerCase());  // ��������
		return s2.toLowerCase().compareTo(s1.toLowerCase());  // ��������
	}
}
