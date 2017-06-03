/* 
 *  ALGraphDFS
 *  Depth First Search ���� �켱 Ž��
 *  ������ �������� �濡�� �ѱ��� �����Ͽ� Ž���ϴ� ���
 *  �湮�� ������ ������ ������ �������� ���ư�
 *  ó�� ������ ������ ��ġ���� Ž���� ����
 *  ���� ���� �ǵ��� ���� ��Ȳ�� ���� ���
 *  �ϳ��� �������� �ٸ� �������� �� �� ������ ������ ���ÿ� push
 *  ���̻� �̵��� ������ ���� ��� �ǵ��� �� �� ���ÿ��� pop, ���ÿ��� ��ȯ�� ��ġ�� �̵�
 *  
 */

package graph;

import java.util.Arrays;

public class ALGraphDFS {
	
	private static final int ARRAY_LENGTH = 100;    // ������ �迭 ����

	public static void main(String[] args) {
		ALGraphDFS.Graph graph = new ALGraphDFS.Graph(7);
		graph.addEdge(Vertex.A, Vertex.B);
		graph.addEdge(Vertex.B, Vertex.C);
		graph.addEdge(Vertex.C, Vertex.D);
		graph.addEdge(Vertex.D, Vertex.E);
		graph.addEdge(Vertex.E, Vertex.F);
		graph.addEdge(Vertex.E, Vertex.G);
		graph.addEdge(Vertex.A, Vertex.D);
		
		
		graph.showGraphEdgeInfo();
		
		
		graph.DFSshowGraphVertex(Vertex.A);
		System.out.println();
		graph.DFSshowGraphVertex(Vertex.B);
		System.out.println();
		graph.DFSshowGraphVertex(Vertex.C);
		System.out.println();
		graph.DFSshowGraphVertex(Vertex.D);
		System.out.println();
		graph.DFSshowGraphVertex(Vertex.E);
		System.out.println();
		graph.DFSshowGraphVertex(Vertex.F);
		System.out.println();
		graph.DFSshowGraphVertex(Vertex.G);
		System.out.println();
	}
	
	static class Graph {
		private int numOfVertex;
		private List [] list;
		private int [] visitInfo;    // Ž�� �������� Ž���� ����� ������ ����
		
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
		
		// DFS��� Ž��
		public void DFSshowGraphVertex(Vertex startVertex) {
			Stack stack = new Stack();
			int visit = startVertex.ordinal();
			int next = 0;
			boolean visitFlag = false;

			visitVertex(visit);
			//stack.push(visit);
				
			while(true) {
				
				Vertex data = (Vertex) list[visit].getFirstData();
				if(data != null) {
					next = data.ordinal();
					visitFlag = false;
				} else {
					break;
				}
				
				if(visitVertex(next)) {  // �湮�� ������ ���
					stack.push(visit);
					visit = next;
					visitFlag = true;
				} else {                 // �湮�� ������ ���, ����� �ٸ� ��������
					while(true) {
						data = (Vertex) list[visit].getNextData();
						
						if(data == null) {
							break;
						}
						next = data.ordinal();
						
						if(visitVertex(next)) {
							stack.push(visit);
							visit = next;
							visitFlag = true;
						}
					}
				}
				
				if(!visitFlag) {
					if(stack.isEmpty()) {
						break;        // ������ ����ִ� ��� ���������� �ǵ��ƿ���
					} else {
						visit = (int) stack.pop();  // �Դ� ������ �ǵ��ư�
					}
				}
			}
			
			// ������ Ž���� ���� Ž�� ���� �ʱ�ȭ
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
	
	// �迭 ��� ����
	static class Stack {
		private Object[] elements;
		private int topIndex;
		
		Stack() {
			elements = new Object[ARRAY_LENGTH];
			topIndex = -1;
		}
		
		public boolean isEmpty() {
			return topIndex == -1;
		}
		
		public boolean push(Object object) {
			if(topIndex == (ARRAY_LENGTH - 1) || object == null) {
				return false;
			}
			
			topIndex++;
			elements[topIndex] = object;
			
			return true;
		}
		
		public Object pop() {
			if(isEmpty()) {
				return null;
			}
			
			Object data = elements[topIndex];
			topIndex--;
			
			return data;
		}
		
		public Object peek() {
			if(isEmpty()) {
				return null;
			}
			
			return elements[topIndex];
		}
	}

}
