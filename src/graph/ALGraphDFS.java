/* 
 *  ALGraphDFS
 *  Depth First Search 깊이 우선 탐색
 *  정점의 여러갈래 길에서 한길을 선택하여 탐색하는 방법
 *  방문할 정점이 없으면 이전의 정점으로 돌아감
 *  처음 시작한 정점의 위치에서 탐색이 종료
 *  갔던 길을 되돌아 오는 상황에 스택 사용
 *  하나의 정점에서 다른 정점으로 갈 때 떠나는 정점을 스택에 push
 *  더이상 이동할 정점이 없는 경우 되돌아 갈 때 스택에서 pop, 스택에서 반환된 위치로 이동
 *  
 */

package graph;

import java.util.Arrays;

public class ALGraphDFS {
	
	private static final int ARRAY_LENGTH = 100;    // 스택의 배열 길이

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
		private int [] visitInfo;    // 탐색 과정에서 탐색이 진행된 정점의 정보
		
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
		
		// DFS기반 탐색
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
				
				if(visitVertex(next)) {  // 방문에 성공한 경우
					stack.push(visit);
					visit = next;
					visitFlag = true;
				} else {                 // 방문에 길패한 경우, 연결된 다른 정점으로
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
						break;        // 스택이 비어있는 경우 시작점으로 되돌아왔음
					} else {
						visit = (int) stack.pop();  // 왔던 곳으로 되돌아감
					}
				}
			}
			
			// 이후의 탐색을 위해 탐색 정보 초기화
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
	
	// 배열 기반 스택
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
