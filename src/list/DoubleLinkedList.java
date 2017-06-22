/* 
 * Double linked list
 * ���� ���� ����Ʈ
 * ���� ��尡 ������ ��带 ����Ŵ�� ���ÿ� ������ ��嵵 ���� ��׸� ����Ű�� ����
 * ���� ���� ����Ʈ�� ��������� ��ȸ�� ����
 * 	
 */

package list;

public class DoubleLinkedList<E> implements LinkedList<E> {
	
	private BidirectionalNode<E> head;
	private BidirectionalNode<E> position;
	private int size;
	
	public DoubleLinkedList() {
		head = new BidirectionalNode<E>();  // ���̳�� ����
		position = head;
		size = 0;
		
		head.setPrev(null);
		head.setNext(null);
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	// ��� �߰�
	@Override
	public void add(E data) {
		BidirectionalNode<E> newNode = new BidirectionalNode<>(data);
		
		if(head.getNext() != null) {
			((BidirectionalNode<E>) head.getNext()).setPrev(newNode);
		}
		
		newNode.setNext(head.getNext());
		head.setNext(newNode);
		size++;
	}
	
	// ù��° ��� ������ �������� 
	@Override
	public E getFirstData() {
		if(isEmpty()) {
			return null;
		}
		
		position = (BidirectionalNode<E>) head.getNext();
		
		return position.getData();
	}
	
	// ������ ��� ������ ��������
	@Override
	public E getNextData() {
		if(position.getNext() == null) {
			return null;
		}
		
		position = (BidirectionalNode<E>) position.getNext();
		
		return position.getData();
	}
	
	// ���� ��� ������ ��������
	public E getPrevData() {
		if(position.getPrev() == null) {
			return null;
		}
		
		position = position.getPrev();
		
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
			head.setNext(position.getNext());
			size--;
			
			return check;
		}
		
		while(true) {
			check = getNextData();
			if(check == null) {
				break;
			}
			
			if(check.equals(data)) {
				if(position.getNext() == null) {    // ������ ��尡 ���� ����� ���
					position.setNext(null);
				} else {                            // �� ��
					((BidirectionalNode<E>) position.getNext()).setPrev(position.getPrev());
					position.getPrev().setNext(position.getNext());
				}
				
				size--;
				
				return check;
			}
		}
		
		return null;  // ���� ����
	}
	
	@Override
	public int size() {
		return size;
	}

}
