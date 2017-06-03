/* 
 *  ALGraphBFS
 *  Breadth first search �ʺ� �켱 Ž��
 *  �ϳ��� ������ ����� ��� ������ �湮
 *  �湮�� �������� ���� �ٽ� �湮���� ���� ����� ��� ���� �湮
 *  ��� ������ �ٸ� �������� �湮�� ��ȸ�� ������ �� ����
 *  �湮�� ������ queue�� ����
 *  ����� ������� queue���� ���� �������� �湮 ��ȸ�� ����
 *  
 */

package graph;

import java.util.Arrays;

public class ALGraphBFS {
	
	private static final int ARRAY_LENGTH = 100;    // ť�� �迭 ����

	public static void main(String[] args) {
		ALGraphBFS.Graph graph = new ALGraphBFS.Graph(7);
		graph.addEdge(Vertex.A, Vertex.B);
		graph.addEdge(Vertex.B, Vertex.C);
		graph.addEdge(Vertex.C, Vertex.D);
		graph.addEdge(Vertex.D, Vertex.E);
		graph.addEdge(Vertex.E, Vertex.F);
		graph.addEdge(Vertex.E, Vertex.G);
		graph.addEdge(Vertex.A, Vertex.D);
		
		
		graph.showGraphEdgeInfo();
		
		
		graph.BFSshowGraphVertex(Vertex.A);
		System.out.println();
		graph.BFSshowGraphVertex(Vertex.B);
		System.out.println();
		graph.BFSshowGraphVertex(Vertex.C);
		System.out.println();
		graph.BFSshowGraphVertex(Vertex.D);
		System.out.println();
		graph.BFSshowGraphVertex(Vertex.E);
		System.out.println();
		graph.BFSshowGraphVertex(Vertex.F);
		System.out.println();
		graph.BFSshowGraphVertex(Vertex.G);
		System.out.println();
	}
	
	static class Graph {
		private int numOfVertex;
		private List [] list;
		private int [] visitInfo;
		
		Graph(int numOfVertex) {
			this.numOfVertex = numOfVertex;
			this.list = new List[numOfVertex];
			this.visitInfo = new int[numOfVertex];
			
			for(int i=0; i<numOfVertex; i++) {
				list[i] = new List();
				visitInfo[i] = 0;
			}
		}
		
		// ���� �߰��Ͽ� ��������, ������ �׷����� ���� �� ����
		public void addEdge(Vertex fromVertex, Vertex toVertex) {
			list[fromVertex.ordinal()].add(toVertex);
			list[toVertex.ordinal()].add(fromVertex);
		}
		
		// �׷������� ������ ���� ��Ȳ ��� 
		public void showGraphEdgeInfo() {
			Object data;
			
			for(int i=0; i<numOfVertex; i++) {
				System.out.print((char) (i + 65) + "�� ����� ���� : ");
				
				data = list[i].getFirstData();
				if(data != null) {
					System.out.print(data + " ");
					
					while(true) {
						data = list[i].getNextData();
						if(data == null) {
							break;
						}
						
						System.out.print(data + " ");
					}
					
				}
				System.out.println();
			}
		}
		
		// ���� �湮 �� �ܼ�â�� ���
		public boolean visitVertex(int index) {
			// ó�� �湮�� ���
			if(visitInfo[index] == 0) {
				visitInfo[index] = 1;    // �湮 ���
				
				System.out.print((char)(index + 65) + " ");
				
				return true;    // �湮 ����
			}
			
			return false;    // �湮 ����
		}
		
		// BFS��� Ž��
		public void BFSshowGraphVertex(Vertex startVertex) {
			Queue queue = new Queue();
			int visit = startVertex.ordinal();
			int next = 0;
			
			visitVertex(visit);
			
			while(true) {
				Vertex data = (Vertex) list[visit].getFirstData();
				if(data != null) {
					next = data.ordinal();
				} else {
					break;
				}
				
				if(visitVertex(next)) {
					queue.enqueue(next);    // �湮�ϸ� queue�� push
				}
				
				while(true) {    // ����� ���(����)�� �ִٸ� ��� �湮
					data = (Vertex) list[visit].getNextData();
					if(data == null) {
						break;
					}
					next = data.ordinal();
					
					if(visitVertex(next)) {
						queue.enqueue(next);
					}
					
				}
				
				if(queue.isEmpty()) {    // ť�� ��� Ž�� ����
					break;
				} else {
					visit = (int) queue.dequeue();  // ť���� �ϳ� ������ �������� �ٽ� Ž��
				}
			}
			
			Arrays.fill(visitInfo, 0);
		}
		
	}
	
	static class List {
		private Node head = null;
		private Node position = null;
		private Node before = null;
		private int size;
		
		List() {
			head = new Node("dummy");
			head.setNext(null);
			position = head;
			size = 0;
		}
		
		public boolean isEmpty() {
			if(head.getNext() == null) {
				return true;
			}
			
			return false;
		}
		
		// ��� �߰�
		public void add(Object object) {
			Node newNode = new Node(object);
			newNode.setNext(head.getNext());
			head.setNext(newNode);
			size++;
		}
		
		// ù��° ��� ��������
		public Object getFirstData() {
			before = head;
			position = head.getNext();
			
			if(position == null) {
				return null;
			}
			
			return position.getData();
		}
		
		// ���� ��� ��������
		public Object getNextData() {
			if(position == null || position.getNext() == null) {
				return null;
			}
			
			before = position;
			position = position.getNext();
			
			return position.getData();
		}
		
		// ��� ��ȸ
		public boolean contains(Object object) {
			if(getFirstData() != null && object.equals(getFirstData())) {
				return true;
			}
			
			while(true) {
				Object data = getNextData();
				if(data == null) {
					break;
				}
				if(data.equals(object)) {
					return true;
				}
			}
			
			return false;
		}
		
		// ��� ����
		public Object remove(Object object) {
			Object data = getFirstData();
			if(object.equals(data)) {
				before.setNext(position.getNext());
				position = before;
				size--;
				
				return data;
			}
			
			while(true) {
				data = getNextData();
				if(data == null) {
					break;
				}
				if(object.equals(data)) {
					before.setNext(position.getNext());
					position = before;
					size--;
					
					return data;
				}
			}
			
			return null;
		}
		
		public int size() {
			return size;
		}
		
	}
	
	static class Node {
		private Object data;
		private Node next;
		
		Node(Object data) {
			this.data = data;
			next = null;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
	}
	
	// ���� ť
	static class Queue {
		private Object[] queue;
		private int front;
		private int rear;
		
		Queue() {
			queue = new Object[ARRAY_LENGTH];
			front = 0;
			rear = 0;
		}
		
		public boolean isEmpty() {
			return front == rear;
		}
		
		// front�� rear�� ����, �ѹ��� ȸ�� �� �ٽ� 0
		public int nextIndex(int index) {
			return (index == (ARRAY_LENGTH - 1))? 0 : index + 1;
		}
		
		// rear + 1�� ��ġ�� ������ �߰�
		public boolean enqueue(Object object) {
			
			// ť�� ���� �� ���
			if(front == nextIndex(rear) || object == null) {
				return false;
			}
			
			rear = nextIndex(rear);
			queue[rear] = object;
			
			return true;
		}
		
		// rear + 1�� ��ġ�� ������ ��ȯ �� ����
		public Object dequeue() {
			if(isEmpty()) {
				return null;
			}
			
			front = nextIndex(front);
			Object data = queue[front];
			queue[front] = null;
			
			return data;
		}
		
		// front + 1 �� ��ȯ(������ ����x) 
		public Object peek() {
			if(isEmpty()) {
				return null;
			}
			
			return queue[nextIndex(front)];
		}
		
	}

}
