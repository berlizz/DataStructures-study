/* 
 * Binary search Tree
 * 이진 탐색 트리
 * 이진 탐색 트리의 노드에 저장된 키(데이터)는 유일
 * 루트 노드의 키가 왼쪽 서브 트리를 구성하는 어떠한 노드의 키보다 큼
 * 루트 노드의 키가 오른쪽 서브 트리를 구성하는 어떠한 노드의 키보다 작음
 * 왼쪽과 오른쪽 서브 트리도 이진 탐색 트리
 * 데이터를 삽입 할 경우 작으면 왼쪽으로 크면 오른쪽으로의 원칙을 기준으로 데이터를 삽입
 * 탐색의 과정에서도 그대로 따름
 * Tree클래스는 tree.BinaryTree의 Tree클래스에서 removeLeftSubTree, removeRightSubTree메소드 추가 
 * 
 */

package tree;

public class BinarySearchTree {

	public static void main(String[] args) {
		BSTree tree = new BSTree();
		tree.insert(5);  //                     5
		tree.insert(8);  //                   /   \
		tree.insert(1);  //                  1     8
		tree.insert(6);  //                   \   / \
		tree.insert(4);  //                    4 6   9
		tree.insert(9);  //                   /   \   
		tree.insert(3);  //                  3     7    
		tree.insert(2);  //                 /
		tree.insert(7);  //                2
		
		tree.showAllData();
		System.out.println();
		
		tree.remove(3);
		tree.showAllData();
		System.out.println();
		
		tree.remove(1);
		tree.showAllData();
		System.out.println();
		
		tree.remove(6);
		tree.showAllData();
		System.out.println();
		
	}
	
	static class BSTree {
		private Tree tree;
		private Node root;
		
		BSTree() {
			tree = new Tree();
			root = null;
		}
		
		public int getNodeData(Node node) {
			return tree.getNodeData(node);
		}
		
		// 데이터 추가하기
		public void insert(int data) {
			Node parent = null;
			Node child = root;
			Node newNode = tree.makeNode(data);
			
			// 자식 노드가 null일 때까지 
			while(child != null) {
				// 데이터(키)의 중복을 허용하지 않음
				if(data == tree.getNodeData(child)) {
					return;
				}
				
				parent = child;
				
				// 노드의 데이터가 새로 생성된 데이터보다 큰 경우 왼쪽으로, 반대의 경우 오른쪽으로 저장할 위치를 찾음
				if(tree.getNodeData(parent) > data) {
					child = tree.getLeftSubTree(child);
				} else {
					child = tree.getRightSubTree(child);
				}
			}
			
			// parent가 null인 경우는 비어있는 트리에 처음으로 데이터가 삽입된 경우
			if(parent != null) {
				// parent노드의 데이터가 새로 생성된 데이터보다 큰 경우 왼쪽으로 트리생성, 반개의 경우 오른쪽으로
				if(tree.getNodeData(parent) > data) {
					tree.makeLeftSubTree(parent, newNode);
				} else {
					tree.makeRightSubTree(parent, newNode);
				}
			} else {
				root = newNode;
			}
			
		}
		
		// 데이터 탐색
		public Node search(int data) {
			Node node = root;
			int nodeData;
			  
			while(node != null) {
				nodeData = tree.getNodeData(node);
				
				if(nodeData == data) {
					return node;                         // 탐색 성공 시 해당 노드 리턴
				} else if(nodeData > data) {
					node = tree.getLeftSubTree(node);    // 탐색된 노드의 데이터가 찾고자 하는 데이터보다 큰 경우 왼쪽으로 탐색
				} else {
					node = tree.getRightSubTree(node);   // 탐색된 노드의 데이터가 찾고자 하는 데이터보다 작은 경우 오른쪽으로 탐색
				}
			}
			
			// 탐색 실패
			return null;
		}
		
		// 노드 삭제
		public Node remove(int data) {
			Node vRoot = tree.makeNode(0);  // 가상 루트 노드
			Node parentNode = vRoot;
			Node childNode = root;
			Node removeNode = null;
			
			// 루트 노드를 vRoot의 오른쪽 자식 노드로 되게 한다
			tree.makeRightSubTree(vRoot, childNode);
			
			// 삭제할 노드를 탐색
			while(childNode != null && tree.getNodeData(childNode) != data) {
				parentNode = childNode;
				
				if(tree.getNodeData(childNode) > data) {
					childNode = tree.getLeftSubTree(childNode);
				} else {
					childNode = tree.getRightSubTree(childNode);
				}
			}
			
			// 탐색 실패, 삭제할 대상 존재하지 않음
			if(childNode == null) {
				return null;
			}
			
			removeNode = childNode;
			
			// 삭제할 노드가 단말 노드인 경우
			if(tree.getLeftSubTree(removeNode) == null && tree.getRightSubTree(removeNode) == null) {
				if(tree.getLeftSubTree(parentNode) == removeNode) {
					tree.removeLeftSubTree(parentNode);    // 삭제할 노드가 부모노드 왼쪽에 있는 경우, 부모노드의 왼쪽 서브트리 삭제
				} else {
					tree.removeRightSubTree(parentNode);   // 삭제할 노드가 부모노드 오른쪽에 있는 경우, 부모노드의 오른쪽 서브트리 삭제
				}
			} 
			
			// 삭제할 노드가 하나의 자식 노드를 가지는 경우
			else if(tree.getLeftSubTree(removeNode) == null || tree.getRightSubTree(removeNode) == null) {
				Node removeChildNode; // 삭제할 노드의 자식 노드
				
				if(tree.getLeftSubTree(removeNode) != null) {
					removeChildNode = tree.getLeftSubTree(removeNode);    // 삭제할 노드의 자식이 왼쪽에 있는 경우 
				} else {
					removeChildNode = tree.getRightSubTree(removeNode);   // 삭제할 노드의 자식이 오른쪽에 있는 경우
				}
				
				if(tree.getLeftSubTree(parentNode) == removeNode) {
					tree.makeLeftSubTree(parentNode, removeChildNode);    // 삭제할 노드가 부모노드의 왼쪽에 있는 경우
				} else {
					tree.makeRightSubTree(parentNode, removeChildNode);   // 삭제할 노드가 부모노드의 오른쪽에 있는 경우
				}
			}
			
			// 삭제할 노드가 두개의 자식 노드를 가지는 경우
			else {
				Node rNode = tree.getRightSubTree(removeNode);    // 대체 노드
				Node rpNode = null;   // 대체 노드의 부모 노드
				
				// 삭제할 노드를 대체할 노드를 찾는다. 삭제할 노드의 오른쪽 트리 중 가장 작은 값을 찾는 과정
				while(tree.getLeftSubTree(rNode) != null) {
					rpNode = rNode;
					rNode = tree.getLeftSubTree(rNode);
				}
				
				int removeData = tree.getNodeData(removeNode);  // 삭제할 노드의 데이터
				removeNode.setData(tree.getNodeData(rNode));    // 삭제할 노드에 대체노드 데이터 대입
				
				if(tree.getLeftSubTree(rpNode) == rNode) {
					tree.makeLeftSubTree(rpNode, tree.getRightSubTree(rNode));   // 대체한 노드가 부모노드의 왼쪽에 있는 경우
				} else {
					tree.makeRightSubTree(rpNode, tree.getRightSubTree(rNode));  // 대체한 노드가 부모노드의 오른쪽에 있는 경우
				}
				
				removeNode = rNode;
				removeNode.setData(removeData);    // 리턴할 노드 데이터 복원(삭제된 노드 리턴)
			}
			
			// 삭제한 노드가 루트 노드인 경우
			if(tree.getRightSubTree(vRoot) != root) {
				root = tree.getRightSubTree(vRoot);
			}
			
			// 삭제한 노드 리턴
			return removeNode;
		}
		
		// 모든 데이터 중윈 순회방식으로 콘솔창에 출력
		public void showAllData() {
			tree.inorderTraversal(root);
		}
		
	}
	
	static class Tree {
		
		public Node makeNode(int data) {
			Node newNode = new Node(data);
			
			return newNode;
		}
		
		public int getNodeData(Node node) {
			return node.getData();
		}
		
		public Node getLeftSubTree(Node node) {
			return node.getLeft();
		}
		
		public Node getRightSubTree(Node node) {
			return node.getRight();
		}
		
		public void makeLeftSubTree(Node parent, Node child) {
			parent.setLeft(child);
		}
		
		public void makeRightSubTree(Node parent, Node child) {
			parent.setRight(child);
		}
		
		// 전달받은 노드의 왼쪽 자식노드 제거, 제거된 노드 반환
		public Node removeLeftSubTree(Node node) {
			Node removeNode = null;
			if(node != null) {
				removeNode = node.getLeft();
				node.setLeft(null);
			}
			
			return removeNode;
		}
		// 전달받은 노드의 오른쪽 자식노드 제거, 제거된 노드 반환		
		public Node removeRightSubTree(Node node) {
			Node removeNode = null;
			if(node != null) {
				removeNode = node.getRight();
				node.setRight(null);
			}
			
			return removeNode;
		}
		
		// 트리 중위 순회
		public void inorderTraversal(Node node) {
			if(node == null) {
				return;
			}
			
			inorderTraversal(node.getLeft());
			System.out.print(node.getData() + " ");
			inorderTraversal(node.getRight());
		}
		
	}
	
	static class Node {
		private int data;
		private Node left;
		private Node right;
		
		Node(int data) {
			this.data = data;
		}

		public final int getData() {
			return data;
		}

		public final void setData(int data) {
			this.data = data;
		}

		public final Node getLeft() {
			return left;
		}

		public final void setLeft(Node left) {
			this.left = left;
		}

		public final Node getRight() {
			return right;
		}

		public final void setRight(Node right) {
			this.right = right;
		}
		
	}

}
