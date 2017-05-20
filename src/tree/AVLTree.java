/* 
 * AVL tree
 * 이진 탐색 트리의 탐색 연산은 O(log2n)의 시간복잡도를 가지지만, 균형이 맞지 않을 수록 O(n)에 가까운 시간 복잡도를 보임
 * AVL 트리는 이러한 이진 탐색 트리의 단점을 보완한 균형 잡힌 이진 트리 중 하나
 * 노드가 추가될 때 또는 노드가 삭제될 때 트리의 균형 상태를 파악한 후 구조를 변경하여 균형을 잡음
 * 균형 인수 = 왼쪽 서브 트리 높이 - 오른쪽 서브 트리 높이
 * 균형 인수의 절대값이 2 이상인 경우에 트리 재조정 진행
 * 구현은 이진 탐색 트리를 기반으로 하여 트리의 높이를 구하는 메소드, 각각의 회전에 대한 메소드, 재조정 메소드 추가
 * 노드의 삽입과 삭제 메소드 수정
 * 
 */

package tree;

public class AVLTree {

	public static void main(String[] args) {
		AVLT avlt = new AVLT();
		
		avlt.insert(1);
		avlt.insert(2);
		avlt.insert(3);
		avlt.insert(4);
		avlt.insert(5);
		avlt.insert(6);
		avlt.insert(7);
		avlt.insert(8);
		avlt.insert(9);
		avlt.insert(0);
		
		avlt.showAllData();
		System.out.println();
		
		System.out.println("루트 노드 : " + avlt.root.getData());
		
		avlt.remove(5);
		avlt.showAllData();
		System.out.println();
		
		System.out.println("루트 노드 : " + avlt.root.getData());
		
		avlt.remove(4);
		avlt.showAllData();
		System.out.println();
		
		System.out.println("루트 노드 : " + avlt.root.getData());
		
	}
	
	static class AVLT {
		private Tree tree;
		private Node root;
		
		AVLT() {
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
			
			// 노드 추가 후 리밸런싱
			root = rebalance(root);
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
				Node rpNode = removeNode;   // 대체 노드의 부모 노드
				
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
			
			// 노드 제거 후 리벨런싱
			root = rebalance(root);
			// 삭제한 노드 리턴
			return removeNode;
		}
		
		// 모든 데이터 중윈 순회방식으로 콘솔창에 출력
		public void showAllData() {
			tree.inorderTraversal(root);
		}
		
		// 트리의 높이를 계산하여 반환(트리의 모든 경로 중에서 가장 깊이 뻗은 경로의 높이 반환)
		public int getHeight(Node node) {
			int leftHeight;
			int rightHeight;
			
			if(node == null) {
				return 0;
			}
			
			leftHeight = getHeight(tree.getLeftSubTree(node));
			rightHeight = getHeight(tree.getRightSubTree(node));
			
			if(leftHeight > rightHeight) {
				return leftHeight + 1;
			} else {
				return rightHeight + 1;
			}
			
		}
		
		// 두 서브 트리의 높이의 차(균형 인수)를 반환
		public int getHeightDiff(Node node) {
			int leftHeight;
			int rightHeight;
			
			if(node == null) {
				return 0;
			}
			
			leftHeight = getHeight(tree.getLeftSubTree(node));
			rightHeight = getHeight(tree.getRightSubTree(node));
			
			return leftHeight - rightHeight;
		}
		
		// LL 회전
		public Node rotationLL(Node node) {
			Node parent = node;
			Node child = tree.getLeftSubTree(parent);
			
			// LL회전의 경우 부모 노드의 왼쪽으로 자식노드의 오른쪽 노드를 연결하고, 자식노드의 오른쪽에 부모노드를 연결, 결론은 자식노드가 루트노드가 됨
			tree.makeLeftSubTree(parent, tree.getRightSubTree(child));
			tree.makeRightSubTree(child, parent);
			
			return child;  // 변경된 루트노드를 리턴
		}
		
		// RR 회전
		public Node rotationRR(Node node) {
			Node parent = node;
			Node child = tree.getRightSubTree(node);
			
			// RR회전의 경우 부모 노드의 오른쪽으로 자식노드의 왼쪽 노드를 연결하고, 자식노드의 왼쪽에 부모노드를 연결, 결론은 자식노드가 루트노드가 됨
			tree.makeRightSubTree(parent, tree.getLeftSubTree(child));
			tree.makeLeftSubTree(child, parent);
			
			return child;  // 변경된 루트노드를 리턴
		}
		
		// LR 회전 - 부분적 RR회전에 이어서 LL회전을 진행
		public Node rotationLR(Node node) {
			Node parent = node;
			Node child = tree.getLeftSubTree(parent);
			
			tree.makeLeftSubTree(parent, rotationRR(child));  // 부분적 RR회전
			
			return rotationLL(parent);      // LL회전 후 변경된 루트노드 리턴
		}
		
		// RL 회전 - 부분적 LL회전에 이어서 RR회저을 진행
		public Node rotationRL(Node node) {
			Node parent = node;
			Node child = tree.getRightSubTree(node);
			
			tree.makeRightSubTree(parent, rotationLL(child));  // 부분적 LL회전
			
			return rotationRR(parent);      // RR회전 후 변경된 루트노드 리턴
		}
		
		public Node rebalance(Node node) {
			int heightDiff = getHeightDiff(node);
			
			// 균형 인수가 +2 이상이면 LL 또는 LR 상태이다.
			if(heightDiff > 1) {
				if(getHeightDiff(tree.getLeftSubTree(node)) > 0) {
					node = rotationLL(node);    // LL 상태인 경우
				} else {
					node = rotationLR(node);    // LR 상태인 경우
				}
			}
			
			// 균형 인수가 -2 이하이면 RR 또른 RL 상태이다.
			if(heightDiff < -1) {
				if(getHeightDiff(tree.getRightSubTree(node)) < 0) {
					node = rotationRR(node);    // RR 상태인 경우
				} else {
					node = rotationRL(node);    // RL 상태인 경우
				}
			}
			
			return node;    // 변경된 루트노드를 리턴
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
