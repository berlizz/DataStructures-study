/* 
 *  ALGraphBFS
 *  Breadth first search 너비 우선 탐색
 *  하나의 정점에 연결된 모든 정점을 방문
 *  방문한 정점에서 부터 다시 방문하지 않은 연결된 모든 정점 방문
 *  모든 정점이 다른 정점으로 방문할 기회를 가지진 후 종료
 *  방문한 정점은 queue에 저장
 *  저장된 순서대로 queue에서 나온 정점으로 방문 기회를 가짐
 *  
 */

package graph;

import java.util.Arrays;

public class ALGraphBFS {
	
	private static final int ARRAY_LENGTH = 100;    // 큐의 배열 길이

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
		
		// 간선 추가하여 정점연결, 무방향 그래프라서 양쪽 다 연결
		public void addEdge(Vertex fromVertex, Vertex toVertex) {
			list[fromVertex.ordinal()].add(toVertex);
			list[toVertex.ordinal()].add(fromVertex);
		}
		
		// 그래프에서 정점의 연결 상황 출력 
		public void showGraphEdgeInfo() {
			Object data;
			
			for(int i=0; i<numOfVertex; i++) {
				System.out.print((char) (i + 65) + "에 연결된 정점 : ");
				
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
		
		// 정점 방문 후 콘솔창에 출력
		public boolean visitVertex(int index) {
			// 처음 방문인 경우
			if(visitInfo[index] == 0) {
				visitInfo[index] = 1;    // 방문 기록
				
				System.out.print((char)(index + 65) + " ");
				
				return true;    // 방문 성공
			}
			
			return false;    // 방문 실패
		}
		
		// BFS기반 탐색
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
					queue.enqueue(next);    // 방문하면 queue에 push
				}
				
				while(true) {    // 연결된 노드(정점)가 있다면 모두 방문
					data = (Vertex) list[visit].getNextData();
					if(data == null) {
						break;
					}
					next = data.ordinal();
					
					if(visitVertex(next)) {
						queue.enqueue(next);
					}
					
				}
				
				if(queue.isEmpty()) {    // 큐가 비면 탐색 종료
					break;
				} else {
					visit = (int) queue.dequeue();  // 큐에서 하나 꺼내진 정점으로 다시 탐색
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
		
		// 노드 추가
		public void add(Object object) {
			Node newNode = new Node(object);
			newNode.setNext(head.getNext());
			head.setNext(newNode);
			size++;
		}
		
		// 첫번째 노드 가져오기
		public Object getFirstData() {
			before = head;
			position = head.getNext();
			
			if(position == null) {
				return null;
			}
			
			return position.getData();
		}
		
		// 다음 노드 가져오기
		public Object getNextData() {
			if(position == null || position.getNext() == null) {
				return null;
			}
			
			before = position;
			position = position.getNext();
			
			return position.getData();
		}
		
		// 노드 조회
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
		
		// 노드 삭제
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
	
	// 원형 큐
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
		
		// front와 rear를 증가, 한바퀴 회전 시 다시 0
		public int nextIndex(int index) {
			return (index == (ARRAY_LENGTH - 1))? 0 : index + 1;
		}
		
		// rear + 1의 위치에 데이터 추가
		public boolean enqueue(Object object) {
			
			// 큐가 가득 찬 경우
			if(front == nextIndex(rear) || object == null) {
				return false;
			}
			
			rear = nextIndex(rear);
			queue[rear] = object;
			
			return true;
		}
		
		// rear + 1의 위치의 데이터 반환 및 삭제
		public Object dequeue() {
			if(isEmpty()) {
				return null;
			}
			
			front = nextIndex(front);
			Object data = queue[front];
			queue[front] = null;
			
			return data;
		}
		
		// front + 1 값 반환(데이터 삭제x) 
		public Object peek() {
			if(isEmpty()) {
				return null;
			}
			
			return queue[nextIndex(front)];
		}
		
	}

}
