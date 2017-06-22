/* 
 * Circular linked list
 * ���� ���� ����Ʈ
 * �������� ��尡 ù���� ��带 ����Ų��.
 * �ܼ� ���� ����Ʈ�� �޸� head�� �ƴ� tail�� ����ϴ� ������ ���ο� ��带 �Ӹ��� ������ ���ϴ� ������ �߰��ϱ� ���� 
 * ������ �����͸� �߰��ϴ� ���� �ᱹ ������ �̵��ϴ� ���̹Ƿ� ��������δ� ���� ���Ḯ��Ʈ���� �Ӹ��� ������ ������ �ǹ̰� ����.
 * a b c d e ������ ������(add())
 *                    tail
 * a -> b -> c -> d -> e -> a
 * 
 * 
 */

package list;

public class CircularLinkedList<E> implements LinkedList<E> {
	
	private Node<E> tail;
	private Node<E> position;
	private Node<E> before;
	private int size;
	
	public CircularLinkedList() {
		this.tail = null;  // ��ȯ�Ǿ�� �ϱ� ������ ���̳�� �������� ����
		this.position = tail;
		this.before = null;
		this.size = 0;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	// ��� �߰�
	@Override
	public void add(E data) {
		Node<E> newNode = new Node<>(data);
		
		// ���̳�尡 �����Ƿ� ������ ��尡 ù��° ������� Ȯ��
		if(tail == null) {
			tail = newNode;
			newNode.setNext(newNode);  // ��ȯ�ϱ� ���� ó������� next�� �ڽ��� ����Ŵ
		} else {
			newNode.setNext(tail.getNext());
			tail.setNext(newNode);
			tail = newNode;
		}
		
		size++;
	}
	
	// ������ ��ġ�� ��� �߰�
	public void addLast(E data) {
		add(data);
	}
	
	// ���� ���� ��ġ�� ��� �߰�
	public void addFront(E data) {
		Node<E> newNode = new Node<>(data);
		
		// ���̳�尡 �����Ƿ� ������ ��尡 ù��° ������� Ȯ��
		if(tail == null) {
			tail = newNode;
			newNode.setNext(newNode);  // ��ȯ�ϱ� ���� ó������� next�� �ڽ��� ����Ŵ
		} else {
			newNode.setNext(tail.getNext());
			tail.setNext(newNode);
		}
		
		size++;
	}
	
	// ù��° ��� ������ ��������
	@Override
	public E getFirstData() {
		if(isEmpty()) {
			return null;
		}
		
		before = tail;
		position = tail.getNext();
		
		return position.getData();
	}
	
	public E getLastData() {
		if(isEmpty()) {
			return null;
		}
		
		before = tail;
		position = tail.getNext();
		
		return tail.getData();
	}
	
	// ���� ��� ������ ��������
	@Override
	public E getNextData() {
		// �ѹ��� ��ȸ �� ���
		if(position == tail) {
			return null;
		}
		
		before = position;
		position = position.getNext();
		
		return position.getData();
	}
	
	// ��� ������ ��ȸ
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
		
		return false;  // ��ȸ ���� 
	}
	
	// ��� ����
	@Override
	public E remove(E data) {
		E check = getFirstData();
		if(check != null && check.equals(data)) {
			// ������ ��尡 ��Ʈ�� �ϳ� ���� �������� ���
			if(size == 1) {
				tail = null;
				position = null;
				before = null;
				size--;
				
				return check; 
			}
			
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
				// ������ ��尡 tail�� ���
				if(position == tail) {
					tail = before;
				}
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
