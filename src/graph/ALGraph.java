/* 
 *  ALGraph
 *  인접 리스트(adjacent list) 기반 그래프, 연결 리스트를 활용
 *  linked list 기반 무방향 graph
 *  각각의 정점은 자신과 연결된 정점의 정보를 담기 위해 하나의 연결 리스트를 가짐
 *  각각의 정점에 연결된 간선의 정보는 각각의 연결 리스트에 저장
 *  
 */

package graph;

public class ALGraph {

	public static void main(String[] args) {
		ALGraph.Graph graph = new ALGraph.Graph(5);
		graph.addEdge(Vertex.A, Vertex.B);
		graph.addEdge(Vertex.B, Vertex.C);
		graph.addEdge(Vertex.C, Vertex.D);
		graph.addEdge(Vertex.D, Vertex.E);
		graph.addEdge(Vertex.E, Vertex.A);
		graph.addEdge(Vertex.A, Vertex.D);
		
		graph.showGraphEdgeInfo();
	}
	
	static class Graph {
		private int numOfVertex;
		private List []list;
		
		Graph(int numOfVertex) {
			this.numOfVertex = numOfVertex;
			this.list = new List[numOfVertex];
			
			for(int i=0; i<list.length; i++) {
				list[i] = new List();
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

}
